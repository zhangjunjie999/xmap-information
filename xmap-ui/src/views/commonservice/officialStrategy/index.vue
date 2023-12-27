<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="攻略名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入攻略名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="攻略内容" prop="content">
        <el-input
          v-model="queryParams.content"
          placeholder="请输入攻略内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发布日期" prop="date">
        <el-input
          v-model="queryParams.date"
          placeholder="请输入发布日期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐理由" prop="reason">
        <el-input
          v-model="queryParams.reason"
          placeholder="请输入推荐理由"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作者" prop="author">
        <el-input
          v-model="queryParams.author"
          placeholder="请输入作者"
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
          v-hasPermi="['commonservice:officialstrategy:add']"
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
          v-hasPermi="['commonservice:officialstrategy:edit']"
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
          v-hasPermi="['commonservice:officialstrategy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:officialstrategy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="officialstrategyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="攻略ID" align="center" prop="strategyId" />
      <el-table-column label="攻略名称" align="center" prop="name" show-overflow-tooltip="true"/>
      <el-table-column label="攻略内容" align="center" prop="content" show-overflow-tooltip="true"/>
      <el-table-column label="发布日期" align="center" prop="date" show-overflow-tooltip="true"/>
      <el-table-column label="推荐理由" align="center" prop="reason" show-overflow-tooltip="true"/>
      <el-table-column label="作者" align="center" prop="author" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:officialstrategy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:officialstrategy:remove']"
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

    <!-- 添加或修改官方攻略对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="攻略名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入攻略名称" />
        </el-form-item>
        <el-form-item label="攻略内容" prop="content">
          <el-input v-model="form.content" placeholder="请输入攻略内容" />
        </el-form-item>
        <el-form-item label="发布日期" prop="date">
          <el-input v-model="form.date" placeholder="请输入发布日期" />
        </el-form-item>
        <el-form-item label="推荐理由" prop="reason">
          <el-input v-model="form.reason" placeholder="请输入推荐理由" />
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="form.author" placeholder="请输入作者" />
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
import { listOfficialstrategy, getOfficialstrategy, delOfficialstrategy, addOfficialstrategy, updateOfficialstrategy } from "@/api/commonservice/officialstrategy";

export default {
  name: "Officialstrategy",
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
      // 官方攻略表格数据
      officialstrategyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        content: null,
        date: null,
        reason: null,
        author: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "攻略名称不能为空", trigger: "blur" }
        ],
        date: [
          { required: true, message: "发布日期不能为空", trigger: "blur" }
        ],
        reason: [
          { required: true, message: "推荐理由不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询官方攻略列表 */
    getList() {
      this.loading = true;
      listOfficialstrategy(this.queryParams).then(response => {
        this.officialstrategyList = response.rows;
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
        strategyId: null,
        name: null,
        content: null,
        date: null,
        reason: null,
        author: null,
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
      this.ids = selection.map(item => item.strategyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加官方攻略";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const strategyId = row.strategyId || this.ids
      getOfficialstrategy(strategyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改官方攻略";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.strategyId != null) {
            updateOfficialstrategy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOfficialstrategy(this.form).then(response => {
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
      const strategyIds = row.strategyId || this.ids;
      this.$modal.confirm('是否确认删除官方攻略编号为"' + strategyIds + '"的数据项？').then(function() {
        return delOfficialstrategy(strategyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/officialstrategy/export', {
        ...this.queryParams
      }, `officialstrategy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
