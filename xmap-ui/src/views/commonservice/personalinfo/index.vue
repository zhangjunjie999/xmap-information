<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名称" prop="personalinfoName">
        <el-input
          v-model="queryParams.personalinfoName"
          placeholder="请输入用户名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="personalinfoPhone">
        <el-input
          v-model="queryParams.personalinfoPhone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="微信号" prop="personalinfoWeixin">
        <el-input
          v-model="queryParams.personalinfoWeixin"
          placeholder="请输入微信号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="personalinfoGender">
        <el-input
          v-model="queryParams.personalinfoGender"
          placeholder="请输入性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="居住地区" prop="personalinfoLocation">
        <el-input
          v-model="queryParams.personalinfoLocation"
          placeholder="请输入居住地区"
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
          v-hasPermi="['commonservice:personalinfo:add']"
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
          v-hasPermi="['commonservice:personalinfo:edit']"
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
          v-hasPermi="['commonservice:personalinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:personalinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="personalinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="personalinfoId" show-overflow-tooltip="true"/>
      <el-table-column label="用户名称" align="center" prop="personalinfoName" show-overflow-tooltip="true"/>
      <el-table-column label="手机号" align="center" prop="personalinfoPhone" show-overflow-tooltip="true"/>
      <el-table-column label="微信号" align="center" prop="personalinfoWeixin" show-overflow-tooltip="true"/>
      <el-table-column label="性别" align="center" prop="personalinfoGender" show-overflow-tooltip="true"/>
      <el-table-column label="居住地区" align="center" prop="personalinfoLocation" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:personalinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:personalinfo:remove']"
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

    <!-- 添加或修改个人信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名称" prop="personalinfoName">
          <el-input v-model="form.personalinfoName" placeholder="请输入用户名称" />
        </el-form-item>
        <el-form-item label="手机号" prop="personalinfoPhone">
          <el-input v-model="form.personalinfoPhone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="微信号" prop="personalinfoWeixin">
          <el-input v-model="form.personalinfoWeixin" placeholder="请输入微信号" />
        </el-form-item>
        <el-form-item label="性别" prop="personalinfoGender">
          <el-input v-model="form.personalinfoGender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="居住地区" prop="personalinfoLocation">
          <el-input v-model="form.personalinfoLocation" placeholder="请输入居住地区" />
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
import { listPersonalinfo, getPersonalinfo, delPersonalinfo, addPersonalinfo, updatePersonalinfo } from "@/api/commonservice/personalinfo";

export default {
  name: "Personalinfo",
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
      // 个人信息表格数据
      personalinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        personalinfoName: null,
        personalinfoPhone: null,
        personalinfoWeixin: null,
        personalinfoGender: null,
        personalinfoLocation: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        personalinfoName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        personalinfoWeixin: [
          { required: true, message: "微信号不能为空", trigger: "blur" }
        ],
        personalinfoGender: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询个人信息列表 */
    getList() {
      this.loading = true;
      listPersonalinfo(this.queryParams).then(response => {
        this.personalinfoList = response.rows;
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
        personalinfoId: null,
        personalinfoName: null,
        personalinfoPhone: null,
        personalinfoWeixin: null,
        personalinfoGender: null,
        personalinfoLocation: null,
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
      this.ids = selection.map(item => item.personalinfoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加个人信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const personalinfoId = row.personalinfoId || this.ids
      getPersonalinfo(personalinfoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改个人信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.personalinfoId != null) {
            updatePersonalinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPersonalinfo(this.form).then(response => {
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
      const personalinfoIds = row.personalinfoId || this.ids;
      this.$modal.confirm('是否确认删除个人信息编号为"' + personalinfoIds + '"的数据项？').then(function() {
        return delPersonalinfo(personalinfoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/personalinfo/export', {
        ...this.queryParams
      }, `personalinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
