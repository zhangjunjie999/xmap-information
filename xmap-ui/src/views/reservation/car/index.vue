<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单编号" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="取车时间" prop="pickupTime">
        <el-date-picker clearable
          v-model="queryParams.pickupTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择取车时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="还车时间" prop="returnTime">
        <el-date-picker clearable
          v-model="queryParams.returnTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择还车时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预订时间" prop="reservationTime">
        <el-date-picker clearable
          v-model="queryParams.reservationTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预订时间">
        </el-date-picker>
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
          v-hasPermi="['reservation:car:add']"
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
          v-hasPermi="['reservation:car:edit']"
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
          v-hasPermi="['reservation:car:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:car:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="carList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预订ID" align="center" prop="reservationId" />
      <el-table-column label="订单编号" align="center" prop="orderNumber" />
      <el-table-column label="车辆型号" align="center" prop="vehicleType" :show-overflow-tooltip='true'/>
      <el-table-column label="费用明细" align="center" prop="costDetails" />
      <el-table-column label="取车时间" align="center" prop="pickupTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.pickupTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="还车时间" align="center" prop="returnTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.returnTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预订时间" align="center" prop="reservationTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:car:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:car:remove']"
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

    <!-- 添加或修改车辆预订对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="订单编号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="车辆型号" prop="vehicleType">
          <el-select v-model="form.vehicleType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="费用明细" prop="costDetails">
          <el-input v-model="form.costDetails" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="取车时间" prop="pickupTime">
          <el-date-picker clearable
            v-model="form.pickupTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择取车时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="还车时间" prop="returnTime">
          <el-date-picker clearable
            v-model="form.returnTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择还车时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预订时间" prop="reservationTime">
          <el-date-picker clearable
            v-model="form.reservationTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预订时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择">
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
import { listCar, getCar, delCar, addCar, updateCar } from "@/api/reservation/car";

export default {
  name: "Car",
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
      // 车辆预订表格数据
      carList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        orderNumber: null,
        vehicleType: null,
        costDetails: null,
        pickupTime: null,
        returnTime: null,
        reservationTime: null,
        status: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options1:[
        {
          value:'已预定',
          label:'已预定',
        },
        {
          value:'已出租',
          label:'已出租',
        },
        {
          value:'已归还',
          label:'已归还',
        },
        {
          value:'待审核',
          label:'待审核',
        },
        {
          value:'不可预订',
          label:'不可预订',
        }
      ], options:[
        {
          value:'比亚迪（BYD）-秦PLUSE DM-i',
          label:'比亚迪（BYD）-秦PLUSE DM-i',
        },
        {
          value:'Hyundai（现代）-领动CVT智炫',
          label:'Hyundai（现代）-领动CVT智炫',
        },
        {
          value:'Volkswagen（大众）-途观L',
          label:'Volkswagen（大众）-途观L',
        },
        {
          value:'Audi（奥迪）-A4',
          label:'Audi（奥迪）-A4',
        },
        {
          value:'Ford（福特）-途胜PLUSE',
          label:'Ford（福特）-途胜PLUSE',
        }
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询车辆预订列表 */
    getList() {
      this.loading = true;
      listCar(this.queryParams).then(response => {
        this.carList = response.rows;
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
        orderNumber: null,
        vehicleType: null,
        costDetails: null,
        pickupTime: null,
        returnTime: null,
        reservationTime: null,
        status: null
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
      this.title = "添加车辆预订";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reservationId = row.reservationId || this.ids
      getCar(reservationId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改车辆预订";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reservationId != null) {
            updateCar(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCar(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除车辆预订编号为"' + reservationIds + '"的数据项？').then(function() {
        return delCar(reservationIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/car/export', {
        ...this.queryParams
      }, `car_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
