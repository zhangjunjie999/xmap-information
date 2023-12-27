<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="索引号" prop="discloseinfoNumber">
        <el-input
          v-model="queryParams.discloseinfoNumber"
          placeholder="请输入索引号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="分类" prop="discloseinfoClassification">
        <el-input
          v-model="queryParams.discloseinfoClassification"
          placeholder="请输入分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="责任部门" prop="discloseinfoDepartment">
        <el-input
          v-model="queryParams.discloseinfoDepartment"
          placeholder="请输入责任部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标题" prop="discloseinfoTitle">
        <el-input
          v-model="queryParams.discloseinfoTitle"
          placeholder="请输入标题"
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
          v-hasPermi="['commonservice:discloseinfo:add']"
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
          v-hasPermi="['commonservice:discloseinfo:edit']"
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
          v-hasPermi="['commonservice:discloseinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:discloseinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="discloseinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="信息ID" align="center" prop="discloseinfoId" show-overflow-tooltip="true"/>
      <el-table-column label="索引号" align="center" prop="discloseinfoNumber" show-overflow-tooltip="true"/>
      <el-table-column label="分类" align="center" prop="discloseinfoClassification" show-overflow-tooltip="true"/>
      <el-table-column label="责任部门" align="center" prop="discloseinfoDepartment" show-overflow-tooltip="true"/>
      <el-table-column label="标题" align="center" prop="discloseinfoTitle" show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:discloseinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:discloseinfo:remove']"
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

    <!-- 添加或修改信息公开对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="索引号" prop="discloseinfoNumber">
          <el-input v-model="form.discloseinfoNumber" placeholder="请输入索引号" />
        </el-form-item>
        <el-form-item label="分类" prop="discloseinfoClassification">
          <el-input v-model="form.discloseinfoClassification" placeholder="请输入分类" />
        </el-form-item>
        <el-form-item label="责任部门" prop="discloseinfoDepartment">
          <el-input v-model="form.discloseinfoDepartment" placeholder="请输入责任部门" />
        </el-form-item>
        <el-form-item label="标题" prop="discloseinfoTitle">
          <el-input v-model="form.discloseinfoTitle" placeholder="请输入标题" />
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
import { listDiscloseinfo, getDiscloseinfo, delDiscloseinfo, addDiscloseinfo, updateDiscloseinfo } from "@/api/commonservice/discloseinfo";

export default {
  name: "Discloseinfo",
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
      // 信息公开表格数据
      discloseinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        discloseinfoNumber: null,
        discloseinfoClassification: null,
        discloseinfoDepartment: null,
        discloseinfoTitle: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        discloseinfoClassification: [
          { required: true, message: "分类不能为空", trigger: "blur" }
        ],
        discloseinfoDepartment: [
          { required: true, message: "责任部门不能为空", trigger: "blur" }
        ],
        discloseinfoTitle: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        createBy: [
          { required: true, message: "创建者不能为空", trigger: "blur" }
        ],
        updateBy: [
          { required: true, message: "更新者不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询信息公开列表 */
    getList() {
      this.loading = true;
      listDiscloseinfo(this.queryParams).then(response => {
        this.discloseinfoList = response.rows;
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
        discloseinfoId: null,
        discloseinfoNumber: null,
        discloseinfoClassification: null,
        discloseinfoDepartment: null,
        discloseinfoTitle: null,
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
      this.ids = selection.map(item => item.discloseinfoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加信息公开";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const discloseinfoId = row.discloseinfoId || this.ids
      getDiscloseinfo(discloseinfoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改信息公开";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.discloseinfoId != null) {
            updateDiscloseinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDiscloseinfo(this.form).then(response => {
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
      const discloseinfoIds = row.discloseinfoId || this.ids;
      this.$modal.confirm('是否确认删除信息公开编号为"' + discloseinfoIds + '"的数据项？').then(function() {
        return delDiscloseinfo(discloseinfoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/discloseinfo/export', {
        ...this.queryParams
      }, `discloseinfo_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
