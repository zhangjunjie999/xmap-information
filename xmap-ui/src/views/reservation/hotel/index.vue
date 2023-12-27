<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="酒店或民宿名称" prop="propertyName" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.propertyName"
          placeholder="请输入酒店或民宿名称"
          clearable
          style="margin-left: 40px; width: 250px;"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地理位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入地理位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="quantity">
        <el-input
          v-model="queryParams.quantity"
          placeholder="请输入数量"
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
      <el-form-item label="入住日期" prop="checkInDate">
        <el-date-picker clearable
          v-model="queryParams.checkInDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择入住日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约人数" prop="reservationGuests">
        <el-input
          v-model="queryParams.reservationGuests"
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
          v-hasPermi="['reservation:hotel:add']"
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
          v-hasPermi="['reservation:hotel:edit']"
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
          v-hasPermi="['reservation:hotel:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:hotel:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="hotelList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="酒店或民宿ID" align="center" prop="propertyId" />
      <el-table-column label="酒店或民宿名称" align="center" prop="propertyName" />
      <el-table-column label="地理位置" align="center" prop="location" :show-overflow-tooltip='true'/>
      <el-table-column label="介绍" align="center" prop="description" />
      <el-table-column label="房型" align="center" prop="roomType" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="预约时间" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约单号" align="center" prop="reservationNumber" />
      <el-table-column label="入住日期" align="center" prop="checkInDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkInDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约人数" align="center" prop="reservationGuests" />
      <el-table-column label="预约渠道" align="center" prop="reservationChannel" />
      <el-table-column label="预约状态" align="center" prop="reservationStatus" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:hotel:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:hotel:remove']"
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

    <!-- 添加或修改酒店民宿预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="酒店或民宿名称" prop="propertyName" style="white-space: nowrap;">
          <el-input v-model="form.propertyName" placeholder="请输入酒店或民宿名称" style="margin-left: 25px; width: 360px;" />
        </el-form-item>
        <el-form-item label="地理位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入地理位置" />
        </el-form-item>
        <el-form-item label="介绍" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="房型" prop="roomType">
          <el-select v-model="form.roomType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="数量" prop="quantity">
          <el-input v-model="form.quantity" placeholder="请输入数量" />
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
        <el-form-item label="入住日期" prop="checkInDate">
          <el-date-picker clearable
            v-model="form.checkInDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择入住日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约人数" prop="reservationGuests">
          <el-input v-model="form.reservationGuests" placeholder="请输入预约人数" />
        </el-form-item>
        <el-form-item label="预约渠道" prop="reservationChannel">
          <el-input v-model="form.reservationChannel" placeholder="请输入预约渠道" />
        </el-form-item>
        <el-form-item label="预约状态" prop="reservationStatus">
          <el-select v-model="form.reservationStatus" placeholder="请选择">
            <el-option
              v-for="item in options1"
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
import { listHotel, getHotel, delHotel, addHotel, updateHotel } from "@/api/reservation/hotel";

export default {
  name: "Hotel",
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
      // 酒店民宿预约表格数据
      hotelList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        propertyName: null,
        location: null,
        description: null,
        roomType: null,
        quantity: null,
        reservationTime: null,
        reservationNumber: null,
        checkInDate: null,
        reservationGuests: null,
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
          value:'标准间',
          label:'标准间',
        },
        {
          value:'双床房',
          label:'双床房',
        },
        {
          value:'大床房',
          label:'大床房',
        },
        {
          value:'套房',
          label:'套房',
        },
        {
          value:'家庭房',
          label:'家庭房',
        },
        {
          value:'行政楼层房',
          label:'行政楼层房',
        }
      ],options1:[
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
      ],
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询酒店民宿预约列表 */
    getList() {
      this.loading = true;
      listHotel(this.queryParams).then(response => {
        this.hotelList = response.rows;
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
        propertyId: null,
        propertyName: null,
        location: null,
        description: null,
        roomType: null,
        quantity: null,
        reservationTime: null,
        reservationNumber: null,
        checkInDate: null,
        reservationGuests: null,
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
      this.ids = selection.map(item => item.propertyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加酒店民宿预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const propertyId = row.propertyId || this.ids
      getHotel(propertyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改酒店民宿预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.propertyId != null) {
            updateHotel(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHotel(this.form).then(response => {
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
      const propertyIds = row.propertyId || this.ids;
      this.$modal.confirm('是否确认删除酒店民宿预约编号为"' + propertyIds + '"的数据项？').then(function() {
        return delHotel(propertyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/hotel/export', {
        ...this.queryParams
      }, `hotel_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
