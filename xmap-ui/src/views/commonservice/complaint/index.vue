<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="处理状态" prop="complaintStatus">
        <el-input
          v-model="queryParams.complaintStatus"
          placeholder="请输入处理状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提交时间" prop="complaintCommit">
        <el-input
          v-model="queryParams.complaintCommit"
          placeholder="请输入提交时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="投诉内容" prop="complaintContent">
        <el-input
          v-model="queryParams.complaintContent"
          placeholder="请输入投诉内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="受理时间" prop="complaintResolve">
        <el-input
          v-model="queryParams.complaintResolve"
          placeholder="请输入受理时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="处理结果" prop="complaintResult">
        <el-input
          v-model="queryParams.complaintResult"
          placeholder="请输入处理结果"
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
          v-hasPermi="['commonservice:complaint:add']"
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
          v-hasPermi="['commonservice:complaint:edit']"
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
          v-hasPermi="['commonservice:complaint:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:complaint:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="complaintList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="投诉单号" align="center" prop="complaintId" show-overflow-tooltip="true"/>
      <el-table-column label="处理状态" align="center" prop="complaintStatus" show-overflow-tooltip="true"/>
      <el-table-column label="提交时间" align="center" prop="complaintCommit" show-overflow-tooltip="true"/>
      <el-table-column label="投诉内容" align="center" prop="complaintContent" show-overflow-tooltip="true"/>
      <el-table-column label="受理时间" align="center" prop="complaintResolve" show-overflow-tooltip="true"/>
      <el-table-column label="处理结果" align="center" prop="complaintResult" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:complaint:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:complaint:remove']"
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

    <!-- 添加或修改我的投诉对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="处理状态" prop="complaintStatus">
          <el-input v-model="form.complaintStatus" placeholder="请输入处理状态" />
        </el-form-item>
        <el-form-item label="提交时间" prop="complaintCommit">
          <el-input v-model="form.complaintCommit" placeholder="请输入提交时间" />
        </el-form-item>
        <el-form-item label="投诉内容" prop="complaintContent">
          <el-input v-model="form.complaintContent" placeholder="请输入投诉内容" />
        </el-form-item>
        <el-form-item label="受理时间" prop="complaintResolve">
          <el-input v-model="form.complaintResolve" placeholder="请输入受理时间" />
        </el-form-item>
        <el-form-item label="处理结果" prop="complaintResult">
          <el-input v-model="form.complaintResult" placeholder="请输入处理结果" />
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
import { listComplaint, getComplaint, delComplaint, addComplaint, updateComplaint } from "@/api/commonservice/complaint";

export default {
  name: "Complaint",
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
      // 我的投诉表格数据
      complaintList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        complaintStatus: null,
        complaintCommit: null,
        complaintContent: null,
        complaintResolve: null,
        complaintResult: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        complaintStatus: [
          { required: true, message: "处理状态不能为空", trigger: "blur" }
        ],
        complaintContent: [
          { required: true, message: "投诉内容不能为空", trigger: "blur" }
        ],
        complaintResolve: [
          { required: true, message: "受理时间不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询我的投诉列表 */
    getList() {
      this.loading = true;
      listComplaint(this.queryParams).then(response => {
        this.complaintList = response.rows;
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
        complaintId: null,
        complaintStatus: null,
        complaintCommit: null,
        complaintContent: null,
        complaintResolve: null,
        complaintResult: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null
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
      this.ids = selection.map(item => item.complaintId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加我的投诉";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const complaintId = row.complaintId || this.ids
      getComplaint(complaintId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改我的投诉";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.complaintId != null) {
            updateComplaint(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComplaint(this.form).then(response => {
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
      const complaintIds = row.complaintId || this.ids;
      this.$modal.confirm('是否确认删除我的投诉编号为"' + complaintIds + '"的数据项？').then(function() {
        return delComplaint(complaintIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/complaint/export', {
        ...this.queryParams
      }, `complaint_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
