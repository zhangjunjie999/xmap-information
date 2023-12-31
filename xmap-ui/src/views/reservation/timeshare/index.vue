<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="景区ID" prop="scenicSpotId">
        <el-input
          v-model="queryParams.scenicSpotId"
          placeholder="请输入景区ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开放时间" prop="openingTime">
        <el-date-picker clearable
          v-model="queryParams.openingTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开放时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="关闭时间" prop="closingTime">
        <el-date-picker clearable
          v-model="queryParams.closingTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择关闭时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="可预约时间段" prop="availableTimeSlot" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.availableTimeSlot"
          placeholder="请输入可预约时间段"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 25px;"
        />
      </el-form-item>
      <el-form-item label="数量限制" prop="quantityLimit">
        <el-input
          v-model="queryParams.quantityLimit"
          placeholder="请输入数量限制"
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
          v-hasPermi="['reservation:timeshare:add']"
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
          v-hasPermi="['reservation:timeshare:edit']"
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
          v-hasPermi="['reservation:timeshare:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:timeshare:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="timeshareList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="时间段ID" align="center" prop="timeSlotId" />
      <el-table-column label="景区ID" align="center" prop="scenicSpotId" />
      <el-table-column label="开放时间" align="center" prop="openingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.openingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="关闭时间" align="center" prop="closingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.closingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="可预约时间段" align="center" prop="availableTimeSlot" />
      <el-table-column label="数量限制" align="center" prop="quantityLimit" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:timeshare:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:timeshare:remove']"
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

    <!-- 添加或修改分时预约对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景区ID" prop="scenicSpotId">
          <el-input v-model="form.scenicSpotId" placeholder="请输入景区ID" />
        </el-form-item>
        <el-form-item label="开放时间" prop="openingTime">
          <el-date-picker clearable
            v-model="form.openingTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开放时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="关闭时间" prop="closingTime">
          <el-date-picker clearable
            v-model="form.closingTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择关闭时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="可预约时间段" prop="availableTimeSlot" style="white-space: nowrap;">
          <el-input v-model="form.availableTimeSlot" placeholder="请输入可预约时间段" style="margin-left: 10px; width: 370px;" />
        </el-form-item>
        <el-form-item label="数量限制" prop="quantityLimit">
          <el-input v-model="form.quantityLimit" placeholder="请输入数量限制" />
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
import { listTimeshare, getTimeshare, delTimeshare, addTimeshare, updateTimeshare } from "@/api/reservation/timeshare";

export default {
  name: "Timeshare",
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
      // 分时预约表格数据
      timeshareList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        scenicSpotId: null,
        openingTime: null,
        closingTime: null,
        availableTimeSlot: null,
        quantityLimit: null
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
    /** 查询分时预约列表 */
    getList() {
      this.loading = true;
      listTimeshare(this.queryParams).then(response => {
        this.timeshareList = response.rows;
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
        timeSlotId: null,
        scenicSpotId: null,
        openingTime: null,
        closingTime: null,
        availableTimeSlot: null,
        quantityLimit: null
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
      this.ids = selection.map(item => item.timeSlotId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加分时预约";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const timeSlotId = row.timeSlotId || this.ids
      getTimeshare(timeSlotId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改分时预约";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.timeSlotId != null) {
            updateTimeshare(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTimeshare(this.form).then(response => {
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
      const timeSlotIds = row.timeSlotId || this.ids;
      this.$modal.confirm('是否确认删除分时预约编号为"' + timeSlotIds + '"的数据项？').then(function() {
        return delTimeshare(timeSlotIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/timeshare/export', {
        ...this.queryParams
      }, `timeshare_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
