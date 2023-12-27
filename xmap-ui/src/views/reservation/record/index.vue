<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="事项名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入事项名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约时间" prop="reservationTime">
        <el-date-picker clearable
          v-model="queryParams.reservationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预约时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约单号" prop="reservationNumber">
        <el-input
          v-model="queryParams.reservationNumber"
          placeholder="请输入预约单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约人" prop="reservationPerson">
        <el-input
          v-model="queryParams.reservationPerson"
          placeholder="请输入预约人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约方式" prop="bookingMethod">
        <el-input
          v-model="queryParams.bookingMethod"
          placeholder="请输入预约方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="reservationInfo">
        <el-input
          v-model="queryParams.reservationInfo"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="应约时间" prop="acceptanceTime">
        <el-date-picker clearable
          v-model="queryParams.acceptanceTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择应约时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约人数" prop="numberOfPeople">
        <el-input
          v-model="queryParams.numberOfPeople"
          placeholder="请输入预约人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约渠道" prop="reservationChannel">
        <el-input
          v-model="queryParams.reservationChannel"
          placeholder="请输入预约渠道"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单号" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['reservation:record:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['reservation:record:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['reservation:record:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:record:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约ID" align="center" prop="reservationId" />
      <el-table-column label="事项名称" align="center" prop="itemName" />
      <el-table-column label="预约类型" align="center" prop="reservationType" />
      <el-table-column label="预约时间" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约单号" align="center" prop="reservationNumber" />
      <el-table-column label="预约人" align="center" prop="reservationPerson" />
      <el-table-column label="预约方式" align="center" prop="bookingMethod" />
      <el-table-column label="联系方式" align="center" prop="reservationInfo" :show-overflow-tooltip='true'/>
      <el-table-column label="应约时间" align="center" prop="acceptanceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.acceptanceTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约人数" align="center" prop="numberOfPeople" />
      <el-table-column label="预约渠道" align="center" prop="reservationChannel" />
      <el-table-column label="预约状态" align="center" prop="reservationStatus" />
      <el-table-column label="订单号" align="center" prop="orderNumber" :show-overflow-tooltip='true'/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:record:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:record:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改预约信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="事项名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入事项名称" />
        </el-form-item>
        <el-form-item label="预约类型" prop="reservationType">
          <el-select v-model="form.reservationType" placeholder="请选择">
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预约时间" prop="reservationTime">
          <el-date-picker clearable
            v-model="form.reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约单号" prop="reservationNumber">
          <el-input v-model="form.reservationNumber" placeholder="请输入预约单号" />
        </el-form-item>
        <el-form-item label="预约人" prop="reservationPerson">
          <el-input v-model="form.reservationPerson" placeholder="请输入预约人" />
        </el-form-item>
        <el-form-item label="预约方式" prop="bookingMethod">
          <el-input v-model="form.bookingMethod" placeholder="请输入预约方式" />
        </el-form-item>
        <el-form-item label="联系方式" prop="reservationInfo">
          <el-input v-model="form.reservationInfo" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="应约时间" prop="acceptanceTime">
          <el-date-picker clearable
            v-model="form.acceptanceTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择应约时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约人数" prop="numberOfPeople">
          <el-input v-model="form.numberOfPeople" placeholder="请输入预约人数" />
        </el-form-item>
        <el-form-item label="预约渠道" prop="reservationChannel">
          <el-input v-model="form.reservationChannel" placeholder="请输入预约渠道" />
        </el-form-item>
        <el-form-item label="预约状态" prop="reservationStatus">
          <el-select v-model="form.reservationStatus" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/reservation/record";

export default {
  name: "Record",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 预约信息表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        itemName: null,
        reservationType: null,
        reservationTime: null,
        reservationNumber: null,
        reservationPerson: null,
        bookingMethod: null,
        reservationInfo: null,
        acceptanceTime: null,
        numberOfPeople: null,
        reservationChannel: null,
        reservationStatus: null,
        orderNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options:[
        {
          value:'预约成功',
          label:'预约成功',
        },
        {
          value:'预约失败',
          label:'预约失败',
        },
        {
          value:'预约中',
          label:'预约中',
        }
      ],options1:[
        {
          value:'门票预约',
          label:'门票预约',
        },
        {
          value:'讲解员预约',
          label:'讲解员预约',
        },
        {
          value:'租车预约',
          label:'租车预约',
        },
        {
          value:'酒店预约',
          label:'酒店预约',
        },
        {
          value:'餐饮预约',
          label:'餐饮预约',
        },
        {
          value:'停车场预约',
          label:'停车场预约',
        }
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询预约信息列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        reservationId: null,
        itemName: null,
        reservationType: null,
        reservationTime: null,
        reservationNumber: null,
        reservationPerson: null,
        bookingMethod: null,
        reservationInfo: null,
        acceptanceTime: null,
        numberOfPeople: null,
        reservationChannel: null,
        reservationStatus: null,
        orderNumber: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.reservationId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加预约信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reservationId = row.reservationId || this.ids
      getRecord(reservationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预约信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reservationId != null) {
            updateRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecord(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const reservationIds = row.reservationId || this.ids;
      this.$modal.confirm('是否确认删除预约信息编号为"' + reservationIds + '"的数据项？').then(function() {
        return delRecord(reservationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/record/export', {
        ...this.queryParams
      }, `record_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
