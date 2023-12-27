<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="商品名称" prop="emallName">
        <el-input
          v-model="queryParams.emallName"
          placeholder="请输入商品名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品类型" prop="emallType">
        <el-input
          v-model="queryParams.emallType"
          placeholder="请输入商品类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品描述" prop="emallDescription">
        <el-input
          v-model="queryParams.emallDescription"
          placeholder="请输入商品描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="付款方式" prop="emallMethod">
        <el-input
          v-model="queryParams.emallMethod"
          placeholder="请输入付款方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="商品价格" prop="emallPrice">
        <el-input
          v-model="queryParams.emallPrice"
          placeholder="请输入商品价格"
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
          v-hasPermi="['commonservice:emall:add']"
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
          v-hasPermi="['commonservice:emall:edit']"
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
          v-hasPermi="['commonservice:emall:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:emall:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="emallList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="商品ID" align="center" prop="emallId" show-overflow-tooltip="true"/>
      <el-table-column label="商品名称" align="center" prop="emallName" show-overflow-tooltip="true"/>
      <el-table-column label="商品类型" align="center" prop="emallType" show-overflow-tooltip="true"/>
      <el-table-column label="商品描述" align="center" prop="emallDescription" show-overflow-tooltip="true"/>
      <el-table-column label="付款方式" align="center" prop="emallMethod" show-overflow-tooltip="true"/>
      <el-table-column label="商品价格" align="center" prop="emallPrice" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:emall:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:emall:remove']"
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

    <!-- 添加或修改电子商城对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="emallName">
          <el-input v-model="form.emallName" placeholder="请输入商品名称" />
        </el-form-item>
        <el-form-item label="商品类型" prop="emallType">
          <el-input v-model="form.emallType" placeholder="请输入商品类型" />
        </el-form-item>
        <el-form-item label="商品描述" prop="emallDescription">
          <el-input v-model="form.emallDescription" placeholder="请输入商品描述" />
        </el-form-item>
        <el-form-item label="付款方式" prop="emallMethod">
          <el-input v-model="form.emallMethod" placeholder="请输入付款方式" />
        </el-form-item>
        <el-form-item label="商品价格" prop="emallPrice">
          <el-input v-model="form.emallPrice" placeholder="请输入商品价格" />
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
import { listEmall, getEmall, delEmall, addEmall, updateEmall } from "@/api/commonservice/emall";

export default {
  name: "Emall",
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
      // 电子商城表格数据
      emallList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        emallName: null,
        emallType: null,
        emallDescription: null,
        emallMethod: null,
        emallPrice: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        emallName: [
          { required: true, message: "商品名称不能为空", trigger: "blur" }
        ],
        emallDescription: [
          { required: true, message: "商品描述不能为空", trigger: "blur" }
        ],
        emallMethod: [
          { required: true, message: "付款方式不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询电子商城列表 */
    getList() {
      this.loading = true;
      listEmall(this.queryParams).then(response => {
        this.emallList = response.rows;
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
        emallId: null,
        emallName: null,
        emallType: null,
        emallDescription: null,
        emallMethod: null,
        emallPrice: null,
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
      this.ids = selection.map(item => item.emallId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加电子商城";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emallId = row.emallId || this.ids
      getEmall(emallId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改电子商城";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.emallId != null) {
            updateEmall(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEmall(this.form).then(response => {
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
      const emallIds = row.emallId || this.ids;
      this.$modal.confirm('是否确认删除电子商城编号为"' + emallIds + '"的数据项？').then(function() {
        return delEmall(emallIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/emall/export', {
        ...this.queryParams
      }, `emall_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
