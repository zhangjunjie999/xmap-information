<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="广告名称" prop="operatingName">
        <el-input
          v-model="queryParams.operatingName"
          placeholder="请输入广告名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="广告类型" prop="operatingType">
        <el-input
          v-model="queryParams.operatingType"
          placeholder="请输入广告类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="广告位置" prop="operatingPosition">
        <el-input
          v-model="queryParams.operatingPosition"
          placeholder="请输入广告位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="跳转方式" prop="operatingMethod">
        <el-input
          v-model="queryParams.operatingMethod"
          placeholder="请输入跳转方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="展示内容" prop="operatingContent">
        <el-input
          v-model="queryParams.operatingContent"
          placeholder="请输入展示内容"
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
          v-hasPermi="['commonservice:operating:add']"
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
          v-hasPermi="['commonservice:operating:edit']"
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
          v-hasPermi="['commonservice:operating:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:operating:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="operatingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="运营ID" align="center" prop="operatingId" show-overflow-tooltip="true"/>
      <el-table-column label="广告名称" align="center" prop="operatingName" show-overflow-tooltip="true"/>
      <el-table-column label="广告类型" align="center" prop="operatingType" show-overflow-tooltip="true"/>
      <el-table-column label="广告位置" align="center" prop="operatingPosition" show-overflow-tooltip="true"/>
      <el-table-column label="跳转方式" align="center" prop="operatingMethod" show-overflow-tooltip="true"/>
      <el-table-column label="展示内容" align="center" prop="operatingContent" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:operating:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:operating:remove']"
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

    <!-- 添加或修改运营位服务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="广告名称" prop="operatingName">
          <el-input v-model="form.operatingName" placeholder="请输入广告名称" />
        </el-form-item>
        <el-form-item label="广告类型" prop="operatingType">
          <el-input v-model="form.operatingType" placeholder="请输入广告类型" />
        </el-form-item>
        <el-form-item label="广告位置" prop="operatingPosition">
          <el-input v-model="form.operatingPosition" placeholder="请输入广告位置" />
        </el-form-item>
        <el-form-item label="跳转方式" prop="operatingMethod">
          <el-input v-model="form.operatingMethod" placeholder="请输入跳转方式" />
        </el-form-item>
        <el-form-item label="展示内容" prop="operatingContent">
          <el-input v-model="form.operatingContent" placeholder="请输入展示内容" />
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
import { listOperating, getOperating, delOperating, addOperating, updateOperating } from "@/api/commonservice/operating";

export default {
  name: "Operating",
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
      // 运营位服务表格数据
      operatingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        operatingName: null,
        operatingType: null,
        operatingPosition: null,
        operatingMethod: null,
        operatingContent: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        operatingName: [
          { required: true, message: "广告名称不能为空", trigger: "blur" }
        ],
        operatingPosition: [
          { required: true, message: "广告位置不能为空", trigger: "blur" }
        ],
        operatingMethod: [
          { required: true, message: "跳转方式不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询运营位服务列表 */
    getList() {
      this.loading = true;
      listOperating(this.queryParams).then(response => {
        this.operatingList = response.rows;
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
        operatingId: null,
        operatingName: null,
        operatingType: null,
        operatingPosition: null,
        operatingMethod: null,
        operatingContent: null,
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
      this.ids = selection.map(item => item.operatingId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加运营位服务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const operatingId = row.operatingId || this.ids
      getOperating(operatingId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改运营位服务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.operatingId != null) {
            updateOperating(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOperating(this.form).then(response => {
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
      const operatingIds = row.operatingId || this.ids;
      this.$modal.confirm('是否确认删除运营位服务编号为"' + operatingIds + '"的数据项？').then(function() {
        return delOperating(operatingIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/operating/export', {
        ...this.queryParams
      }, `operating_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
