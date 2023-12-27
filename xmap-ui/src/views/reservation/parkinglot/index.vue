<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="停车场名称" prop="parkingLotName" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.parkingLotName"
          placeholder="请输入停车场名称"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 10px;"
        />
      </el-form-item>
      <el-form-item label="用户ID" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约时间" prop="introduction">
        <el-date-picker clearable
          v-model="queryParams.introduction"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择预约时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车位数量" prop="capacity">
        <el-input
          v-model="queryParams.capacity"
          placeholder="请输入车位数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="已预订车位" prop="bookedSpaces" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.bookedSpaces"
          placeholder="请输入已预订车位"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 10px;"
        />
      </el-form-item>
      <el-form-item label="空余车位" prop="availableSpaces">
        <el-input
          v-model="queryParams.availableSpaces"
          placeholder="请输入空余车位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约状态" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入预约状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大停车时长" prop="maxParkingDuration" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.maxParkingDuration"
          placeholder="请输入最大停车时长"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 20px;"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="reservationTerms" style="margin-left: 20px;">
        <el-date-picker clearable
          v-model="queryParams.reservationTerms"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
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
          v-hasPermi="['reservation:parkinglot:add']"
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
          v-hasPermi="['reservation:parkinglot:edit']"
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
          v-hasPermi="['reservation:parkinglot:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:parkinglot:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="parkinglotList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约ID" align="center" prop="attractionId" />
      <el-table-column label="停车场名称" align="center" prop="parkingLotName" />
      <el-table-column label="用户ID" align="center" prop="location" />
      <el-table-column label="预约时间" align="center" prop="introduction" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.introduction, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车位数量" align="center" prop="capacity" />
      <el-table-column label="已预订车位" align="center" prop="bookedSpaces" />
      <el-table-column label="空余车位" align="center" prop="availableSpaces" />
      <el-table-column label="预约状态" align="center" prop="phone" />
      <el-table-column label="最大停车时长" align="center" prop="maxParkingDuration" />
      <el-table-column label="创建时间" align="center" prop="reservationTerms" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reservationTerms, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:parkinglot:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:parkinglot:remove']"
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

    <!-- 添加或修改停车场预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="停车场名称" prop="parkingLotName" style="white-space: nowrap;">
          <el-input v-model="form.parkingLotName" placeholder="请输入停车场名称" />
        </el-form-item>
        <el-form-item label="用户ID" prop="location">
          <el-input v-model="form.location" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="预约时间" prop="introduction">
          <el-date-picker clearable
            v-model="form.introduction"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车位数量" prop="capacity">
          <el-input v-model="form.capacity" placeholder="请输入车位数量" />
        </el-form-item>
        <el-form-item label="已预订车位" prop="bookedSpaces" style="white-space: nowrap;">
          <el-input v-model="form.bookedSpaces" placeholder="请输入已预订车位" />
        </el-form-item>
        <el-form-item label="空余车位" prop="availableSpaces">
          <el-input v-model="form.availableSpaces" placeholder="请输入空余车位" />
        </el-form-item>
        <el-form-item label="预约状态" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入预约状态" />
        </el-form-item>
        <el-form-item label="最大停车时长" prop="maxParkingDuration" style="white-space: nowrap;">
          <el-input v-model="form.maxParkingDuration" placeholder="请输入最大停车时长" style="margin-left: 10px; width: 370px;" />
        </el-form-item>
        <el-form-item label="创建时间" prop="reservationTerms">
          <el-date-picker clearable
            v-model="form.reservationTerms"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
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
import { listParkinglot, getParkinglot, delParkinglot, addParkinglot, updateParkinglot } from "@/api/reservation/parkinglot";

export default {
  name: "Parkinglot",
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
      // 停车场预约表格数据
      parkinglotList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        parkingLotName: null,
        location: null,
        introduction: null,
        capacity: null,
        bookedSpaces: null,
        availableSpaces: null,
        phone: null,
        maxParkingDuration: null,
        reservationTerms: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询停车场预约列表 */
    getList() {
      this.loading = true;
      listParkinglot(this.queryParams).then(response => {
        this.parkinglotList = response.rows;
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
        attractionId: null,
        parkingLotName: null,
        location: null,
        introduction: null,
        capacity: null,
        bookedSpaces: null,
        availableSpaces: null,
        phone: null,
        maxParkingDuration: null,
        reservationTerms: null
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
      this.ids = selection.map(item => item.attractionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加停车场预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const attractionId = row.attractionId || this.ids
      getParkinglot(attractionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改停车场预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.attractionId != null) {
            updateParkinglot(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addParkinglot(this.form).then(response => {
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
      const attractionIds = row.attractionId || this.ids;
      this.$modal.confirm('是否确认删除停车场预约编号为"' + attractionIds + '"的数据项？').then(function() {
        return delParkinglot(attractionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/parkinglot/export', {
        ...this.queryParams
      }, `parkinglot_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
