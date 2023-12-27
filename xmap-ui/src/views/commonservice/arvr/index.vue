<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="服务名称" prop="serviceName">
        <el-input
          v-model="queryParams.serviceName"
          placeholder="请输入服务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入服务描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="提供者" prop="providerId">
        <el-input
          v-model="queryParams.providerId"
          placeholder="请输入提供者ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入服务价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务状态" prop="availability">
        <el-input
          v-model="queryParams.availability"
          placeholder="请输入服务状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="持续时间" prop="duration">
        <el-input
          v-model="queryParams.duration"
          placeholder="请输入持续时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="服务位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入服务位置"
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
          v-hasPermi="['commonservice:arvr:add']"
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
          v-hasPermi="['commonservice:arvr:edit']"
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
          v-hasPermi="['commonservice:arvr:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:arvr:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="arvrList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="服务ID" align="center" prop="serviceId" />
      <el-table-column label="服务名称" align="center" prop="serviceName"  :show-overflow-tooltip='true'/>
      <el-table-column label="服务描述" align="center" prop="description"  :show-overflow-tooltip='true'/>
      <el-table-column label="提供者" align="center" prop="providerId"  :show-overflow-tooltip='true'/>
      <el-table-column label="服务价格" align="center" prop="price"  :show-overflow-tooltip='true'/>
      <el-table-column label="服务状态" align="center" prop="availability"  :show-overflow-tooltip='true'/>
      <el-table-column label="持续时间" align="center" prop="duration"  :show-overflow-tooltip='true'/>
      <el-table-column label="服务位置" align="center" prop="location"  :show-overflow-tooltip='true'/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:arvr:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:arvr:remove']"
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

    <!-- 添加或修改增强现实虚拟现实对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="服务名称" prop="serviceName">
          <el-input v-model="form.serviceName" placeholder="请输入服务名称" />
        </el-form-item>
        <el-form-item label="服务描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入服务描述" />
        </el-form-item>
        <el-form-item label="提供者" prop="providerId">
          <el-input v-model="form.providerId" placeholder="请输入提供者ID" />
        </el-form-item>
        <el-form-item label="服务价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入服务价格" />
        </el-form-item>
        <el-form-item label="服务状态" prop="availability">
          <el-input v-model="form.availability" placeholder="请输入服务状态" />
        </el-form-item>
        <el-form-item label="持续时间" prop="duration">
          <el-input v-model="form.duration" placeholder="请输入持续时间" />
        </el-form-item>
        <el-form-item label="服务位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入服务位置" />
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
import { listArvr, getArvr, delArvr, addArvr, updateArvr } from "@/api/commonservice/arvr";

export default {
  name: "Arvr",
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
      // 增强现实虚拟现实表格数据
      arvrList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        serviceName: null,
        description: null,
        providerId: null,
        price: null,
        availability: null,
        duration: null,
        location: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        serviceName: [
          { required: true, message: "服务名称不能为空", trigger: "blur" }
        ],
        providerId: [
          { required: true, message: "提供者ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询增强现实虚拟现实列表 */
    getList() {
      this.loading = true;
      listArvr(this.queryParams).then(response => {
        this.arvrList = response.rows;
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
        serviceId: null,
        serviceName: null,
        description: null,
        providerId: null,
        price: null,
        availability: null,
        duration: null,
        location: null,
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
      this.ids = selection.map(item => item.serviceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加增强现实虚拟现实";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const serviceId = row.serviceId || this.ids
      getArvr(serviceId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改增强现实虚拟现实";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.serviceId != null) {
            updateArvr(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addArvr(this.form).then(response => {
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
      const serviceIds = row.serviceId || this.ids;
      this.$modal.confirm('是否确认删除增强现实虚拟现实编号为"' + serviceIds + '"的数据项？').then(function() {
        return delArvr(serviceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/arvr/export', {
        ...this.queryParams
      }, `arvr_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
