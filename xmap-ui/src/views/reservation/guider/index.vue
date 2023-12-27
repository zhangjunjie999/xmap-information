<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="预约单号" prop="bookingNumber">
        <el-input
          v-model="queryParams.bookingNumber"
          placeholder="请输入预约单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="导游编号" prop="guideId">
        <el-input
          v-model="queryParams.guideId"
          placeholder="请输入导游编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="导游姓名" prop="guideName">
        <el-input
          v-model="queryParams.guideName"
          placeholder="请输入导游姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预约开始时间" prop="startTime" style="white-space: nowrap;">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          style="margin-left: 25px;"
          placeholder="请选择预约开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约结束时间" prop="endTime" style="white-space: nowrap;">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="yyyy-MM-dd"
          style="margin-left: 25px;"
          placeholder="请选择预约结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预约导游接待地点" prop="location" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入预约导游接待地点"
          clearable
          style="margin-left: 50px;"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery" style="margin-left: 50px;">搜索</el-button>
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
          v-hasPermi="['reservation:guider:add']"
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
          v-hasPermi="['reservation:guider:edit']"
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
          v-hasPermi="['reservation:guider:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:guider:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="guiderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="预约ID" align="center" prop="appointmentId" />
      <el-table-column label="预约单号" align="center" prop="bookingNumber" />
      <el-table-column label="导游编号" align="center" prop="guideId" />
      <el-table-column label="导游姓名" align="center" prop="guideName" />
      <el-table-column label="预约线路" align="center" prop="route" />
      <el-table-column label="预约开始时间" align="center" prop="startTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约结束时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="预约导游接待地点" align="center" prop="location" />
      <el-table-column label="创建信息时间" align="center" prop="createdAt" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdAt, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:guider:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:guider:remove']"
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

    <!-- 添加或修改导游预约管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="预约单号" prop="bookingNumber">
          <el-input v-model="form.bookingNumber" placeholder="请输入预约单号" />
        </el-form-item>
        <el-form-item label="导游编号" prop="guideId">
          <el-input v-model="form.guideId" placeholder="请输入导游编号" />
        </el-form-item>
        <el-form-item label="导游姓名" prop="guideName">
          <el-input v-model="form.guideName" placeholder="请输入导游姓名" />
        </el-form-item>
        <el-form-item label="预约线路" prop="route">
          <el-input v-model="form.route" placeholder="请输入预约线路" />
        </el-form-item>
        <el-form-item label="预约开始时间" prop="startTime" style="white-space: nowrap;">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="yyyy-MM-dd"
            style="margin-left: 20px;"
            placeholder="请选择预约开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约结束时间" prop="endTime" style="white-space: nowrap;">
          <el-date-picker clearable
            v-model="form.endTime"
            style="margin-left: 20px;"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择预约结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="预约导游接待地点" prop="location" style="white-space: nowrap;">
          <el-input v-model="form.location" placeholder="请输入预约导游接待地点" style="margin-left: 40px; width: 340px;"/>
        </el-form-item>
        <el-form-item label="创建信息时间" prop="createdAt" style="white-space: nowrap;">
          <el-date-picker clearable
            v-model="form.createdAt"
            style="margin-left: 15px;"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建信息时间">
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
import { listGuider, getGuider, delGuider, addGuider, updateGuider } from "@/api/reservation/guider";

export default {
  name: "Guider",
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
      // 导游预约管理表格数据
      guiderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bookingNumber: null,
        guideId: null,
        guideName: null,
        startTime: null,
        endTime: null,
        location: null,
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
    /** 查询导游预约管理列表 */
    getList() {
      this.loading = true;
      listGuider(this.queryParams).then(response => {
        this.guiderList = response.rows;
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
        appointmentId: null,
        bookingNumber: null,
        guideId: null,
        guideName: null,
        route: null,
        startTime: null,
        endTime: null,
        location: null,
        createdAt: null
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
      this.ids = selection.map(item => item.appointmentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加导游预约管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const appointmentId = row.appointmentId || this.ids
      getGuider(appointmentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改导游预约管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.appointmentId != null) {
            updateGuider(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addGuider(this.form).then(response => {
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
      const appointmentIds = row.appointmentId || this.ids;
      this.$modal.confirm('是否确认删除导游预约管理编号为"' + appointmentIds + '"的数据项？').then(function() {
        return delGuider(appointmentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/guider/export', {
        ...this.queryParams
      }, `guider_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
