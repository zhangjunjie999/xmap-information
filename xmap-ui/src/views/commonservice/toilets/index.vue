<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公厕名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入公厕名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公厕位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入公厕位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="公厕说明" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入公厕说明"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入启用状态"
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
          v-hasPermi="['commonservice:toilets:add']"
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
          v-hasPermi="['commonservice:toilets:edit']"
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
          v-hasPermi="['commonservice:toilets:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:toilets:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="toiletsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="公厕ID" align="center" prop="restroomId" />
      <el-table-column label="公厕名称" align="center" prop="name"  :show-overflow-tooltip='true'/>
      <el-table-column label="公厕位置" align="center" prop="location"  :show-overflow-tooltip='true'/>
      <el-table-column label="公厕说明" align="center" prop="description"  :show-overflow-tooltip='true'/>
      <el-table-column label="启用状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:toilets:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:toilets:remove']"
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

    <!-- 添加或修改公厕信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="公厕名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入公厕名称" />
        </el-form-item>
        <el-form-item label="公厕位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入公厕位置" />
        </el-form-item>
        <el-form-item label="公厕说明" prop="description">
          <el-input v-model="form.description" placeholder="请输入公厕说明" />
        </el-form-item>
        <el-form-item label="启用状态" prop="status">
          <el-input v-model="form.status" placeholder="请输入启用状态" />
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
import { listToilets, getToilets, delToilets, addToilets, updateToilets } from "@/api/commonservice/toilets";

export default {
  name: "Toilets",
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
      // 公厕信息表格数据
      toiletsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        location: null,
        description: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "公厕名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询公厕信息列表 */
    getList() {
      this.loading = true;
      listToilets(this.queryParams).then(response => {
        this.toiletsList = response.rows;
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
        restroomId: null,
        name: null,
        location: null,
        description: null,
        status: null,
        createTime: null,
        updateTime: null
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
      this.ids = selection.map(item => item.restroomId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加公厕信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const restroomId = row.restroomId || this.ids
      getToilets(restroomId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改公厕信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.restroomId != null) {
            updateToilets(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addToilets(this.form).then(response => {
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
      const restroomIds = row.restroomId || this.ids;
      this.$modal.confirm('是否确认删除公厕信息编号为"' + restroomIds + '"的数据项？').then(function() {
        return delToilets(restroomIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/toilets/export', {
        ...this.queryParams
      }, `toilets_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
