<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="景点ID" prop="attractionId">
        <el-input
          v-model="queryParams.attractionId"
          placeholder="请输入景点ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点名称" prop="attractionName">
        <el-input
          v-model="queryParams.attractionName"
          placeholder="请输入景点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门票价格" prop="ticketPrice">
        <el-input
          v-model="queryParams.ticketPrice"
          placeholder="请输入门票价格"
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
      <el-form-item label="参观日期" prop="visitDate">
        <el-date-picker clearable
          v-model="queryParams.visitDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择参观日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约人数" prop="reservationCount">
        <el-input
          v-model="queryParams.reservationCount"
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
          v-hasPermi="['reservation:gate:add']"
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
          v-hasPermi="['reservation:gate:edit']"
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
          v-hasPermi="['reservation:gate:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:gate:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="gateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约ID" align="center" prop="reservationId" />
      <el-table-column label="景点ID" align="center" prop="attractionId" />
      <el-table-column label="景点名称" align="center" prop="attractionName" />
      <el-table-column label="门票价格" align="center" prop="ticketPrice" />
      <el-table-column label="预约时间" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约单号" align="center" prop="reservationNumber" />
      <el-table-column label="参观日期" align="center" prop="visitDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.visitDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约人数" align="center" prop="reservationCount" />
      <el-table-column label="预约渠道" align="center" prop="reservationChannel" />
      <el-table-column label="预约状态" align="center" prop="reservationStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:gate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:gate:remove']"
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

    <!-- 添加或修改门票预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景点ID" prop="attractionId">
          <el-input v-model="form.attractionId" placeholder="请输入景点ID" />
        </el-form-item>
        <el-form-item label="景点名称" prop="attractionName">
          <el-input v-model="form.attractionName" placeholder="请输入景点名称" />
        </el-form-item>
        <el-form-item label="门票价格" prop="ticketPrice">
          <el-input v-model="form.ticketPrice" placeholder="请输入门票价格" />
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
        <el-form-item label="参观日期" prop="visitDate">
          <el-date-picker clearable
            v-model="form.visitDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择参观日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约人数" prop="reservationCount">
          <el-input v-model="form.reservationCount" placeholder="请输入预约人数" />
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
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listGate, getGate, delGate, addGate, updateGate } from "@/api/reservation/gate";

export default {
  name: "Gate",
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
      // 门票预约表格数据
      gateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        attractionId: null,
        attractionName: null,
        ticketPrice: null,
        reservationTime: null,
        reservationNumber: null,
        visitDate: null,
        reservationCount: null,
        reservationChannel: null,
        reservationStatus: null
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
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询门票预约列表 */
    getList() {
      this.loading = true;
      listGate(this.queryParams).then(response => {
        this.gateList = response.rows;
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
        attractionId: null,
        attractionName: null,
        ticketPrice: null,
        reservationTime: null,
        reservationNumber: null,
        visitDate: null,
        reservationCount: null,
        reservationChannel: null,
        reservationStatus: null
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
      this.title = "添加门票预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reservationId = row.reservationId || this.ids
      getGate(reservationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改门票预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reservationId != null) {
            updateGate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGate(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除门票预约编号为"' + reservationIds + '"的数据项？').then(function() {
        return delGate(reservationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/gate/export', {
        ...this.queryParams
      }, `gate_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
