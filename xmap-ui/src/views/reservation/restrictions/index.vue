<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="时间段ID" prop="timeSlotId">
        <el-input
          v-model="queryParams.timeSlotId"
          placeholder="请输入时间段ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点预约人数上限" prop="scenicSpotLimit" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.scenicSpotLimit"
          placeholder="请输入景点预约人数上限"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 50px;"
        />
      </el-form-item>
      <el-form-item label="酒店预约人数上限" prop="hotelLimit" style="white-space: nowrap;margin-left: 50px;">
        <el-input
          v-model="queryParams.hotelLimit"
          placeholder="请输入酒店预约人数上限"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 50px;"
        />
      </el-form-item>
      <el-form-item label="餐饮预约人数上限" prop="diningLimit" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.diningLimit"
          placeholder="请输入餐饮预约人数上限"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 50px;"
        />
      </el-form-item>
      <el-form-item label="讲解预约人数上限" prop="guideLimit" style="white-space: nowrap; margin-left: 50px;">
        <el-input
          v-model="queryParams.guideLimit"
          placeholder="请输入讲解预约人数上限"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 50px;"
        />
      </el-form-item>
      <el-form-item label="最大可预约天数" prop="maxReservationDays" style="white-space: nowrap;margin-left: 50px;">
        <el-input
          v-model="queryParams.maxReservationDays"
          placeholder="请输入最大可预约天数"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 45px; margin-right: -10px;"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" style="margin-left: 45px;">搜索</el-button>
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
          v-hasPermi="['reservation:restrictions:add']"
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
          v-hasPermi="['reservation:restrictions:edit']"
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
          v-hasPermi="['reservation:restrictions:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:restrictions:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="restrictionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="限制ID" align="center" prop="restrictionId" />
      <el-table-column label="时间段ID" align="center" prop="timeSlotId" />
      <el-table-column label="景点预约人数上限" align="center" prop="scenicSpotLimit" />
      <el-table-column label="酒店预约人数上限" align="center" prop="hotelLimit" />
      <el-table-column label="餐饮预约人数上限" align="center" prop="diningLimit" />
      <el-table-column label="讲解预约人数上限" align="center" prop="guideLimit" />
      <el-table-column label="最大可预约天数" align="center" prop="maxReservationDays" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:restrictions:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:restrictions:remove']"
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

    <!-- 添加或修改预约限制管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="时间段ID" prop="timeSlotId">
          <el-input v-model="form.timeSlotId" placeholder="请输入时间段ID" />
        </el-form-item>
        <el-form-item label="景点预约人数上限" prop="scenicSpotLimit" style="white-space: nowrap;">
          <el-input v-model="form.scenicSpotLimit" placeholder="请输入景点预约人数上限" style="margin-left: 45px;width: 335px;" />
        </el-form-item>
        <el-form-item label="酒店预约人数上限" prop="hotelLimit" style="white-space: nowrap;">
          <el-input v-model="form.hotelLimit" placeholder="请输入酒店预约人数上限" style="margin-left: 45px;width: 335px;"/>
        </el-form-item>
        <el-form-item label="餐饮预约人数上限" prop="diningLimit" style="white-space: nowrap;">
          <el-input v-model="form.diningLimit" placeholder="请输入餐饮预约人数上限" style="margin-left: 45px;width: 335px;"/>
        </el-form-item>
        <el-form-item label="讲解预约人数上限" prop="guideLimit" style="white-space: nowrap;">
          <el-input v-model="form.guideLimit" placeholder="请输入讲解预约人数上限" style="margin-left: 45px;width: 335px;"/>
        </el-form-item>
        <el-form-item label="最大可预约天数" prop="maxReservationDays" style="white-space: nowrap;">
          <el-input v-model="form.maxReservationDays" placeholder="请输入最大可预约天数" style="margin-left: 45px;width: 335px;"/>
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
import { listRestrictions, getRestrictions, delRestrictions, addRestrictions, updateRestrictions } from "@/api/reservation/restrictions";

export default {
  name: "Restrictions",
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
      // 预约限制管理表格数据
      restrictionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        timeSlotId: null,
        scenicSpotLimit: null,
        hotelLimit: null,
        diningLimit: null,
        guideLimit: null,
        maxReservationDays: null
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
    /** 查询预约限制管理列表 */
    getList() {
      this.loading = true;
      listRestrictions(this.queryParams).then(response => {
        this.restrictionsList = response.rows;
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
        restrictionId: null,
        timeSlotId: null,
        scenicSpotLimit: null,
        hotelLimit: null,
        diningLimit: null,
        guideLimit: null,
        maxReservationDays: null
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
      this.ids = selection.map(item => item.restrictionId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加预约限制管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const restrictionId = row.restrictionId || this.ids
      getRestrictions(restrictionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预约限制管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.restrictionId != null) {
            updateRestrictions(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRestrictions(this.form).then(response => {
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
      const restrictionIds = row.restrictionId || this.ids;
      this.$modal.confirm('是否确认删除预约限制管理编号为"' + restrictionIds + '"的数据项？').then(function() {
        return delRestrictions(restrictionIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/restrictions/export', {
        ...this.queryParams
      }, `restrictions_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
