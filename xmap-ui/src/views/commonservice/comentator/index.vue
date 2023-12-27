<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="姓名" prop="comentatorName">
        <el-input
          v-model="queryParams.comentatorName"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入手机号码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="comentatorGender">
        <el-input
          v-model="queryParams.comentatorGender"
          placeholder="请输入性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="简介" prop="comentatorIntroduction">
        <el-input
          v-model="queryParams.comentatorIntroduction"
          placeholder="请输入简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="起始价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入起始价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预定须知" prop="instruction">
        <el-input
          v-model="queryParams.instruction"
          placeholder="请输入预定须知"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通知设置" prop="messageSet">
        <el-input
          v-model="queryParams.messageSet"
          placeholder="请输入通知设置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createDate">
        <el-date-picker clearable
          v-model="queryParams.createDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateDate">
        <el-date-picker clearable
          v-model="queryParams.updateDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
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
          v-hasPermi="['commonservice:comentator:add']"
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
          v-hasPermi="['commonservice:comentator:edit']"
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
          v-hasPermi="['commonservice:comentator:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:comentator:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="comentatorList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="讲解员ID" align="center" prop="comentatorId" />
      <el-table-column label="姓名" align="center" prop="comentatorName"  :show-overflow-tooltip='true'/>
      <el-table-column label="手机号码" align="center" prop="phoneNumber"  :show-overflow-tooltip='true'/>
      <el-table-column label="性别" align="center" prop="comentatorGender" />
      <el-table-column label="简介" align="center" prop="comentatorIntroduction"  :show-overflow-tooltip='true'/>
      <el-table-column label="工作照" align="center" prop="photo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status"  :show-overflow-tooltip='true'/>
      <el-table-column label="起始价格" align="center" prop="price"  :show-overflow-tooltip='true'/>
      <el-table-column label="预定须知" align="center" prop="instruction"  :show-overflow-tooltip='true'/>
      <el-table-column label="通知设置" align="center" prop="messageSet" />
      <el-table-column label="创建时间" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:comentator:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:comentator:remove']"
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

    <!-- 添加或修改讲解员信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="姓名" prop="comentatorName">
          <el-input v-model="form.comentatorName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="性别" prop="comentatorGender">
          <el-input v-model="form.comentatorGender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="简介" prop="comentatorIntroduction">
          <el-input v-model="form.comentatorIntroduction" placeholder="请输入简介" />
        </el-form-item>
        <el-form-item label="工作照" prop="photo">
          <image-upload v-model="form.photo"/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="form.status" placeholder="请输入状态" />
        </el-form-item>
        <el-form-item label="起始价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入起始价格" />
        </el-form-item>
        <el-form-item label="预定须知" prop="instruction">
          <el-input v-model="form.instruction" placeholder="请输入预定须知" />
        </el-form-item>
        <el-form-item label="通知设置" prop="messageSet">
          <el-input v-model="form.messageSet" placeholder="请输入通知设置" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createDate">
          <el-date-picker clearable
            v-model="form.createDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updateDate">
          <el-date-picker clearable
            v-model="form.updateDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
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
import { listComentator, getComentator, delComentator, addComentator, updateComentator } from "@/api/commonservice/comentator";

export default {
  name: "Comentator",
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
      // 讲解员信息管理表格数据
      comentatorList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        comentatorName: null,
        phoneNumber: null,
        comentatorGender: null,
        comentatorIntroduction: null,
        photo: null,
        status: null,
        price: null,
        instruction: null,
        messageSet: null,
        createDate: null,
        updateDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        comentatorName: [
          { required: true, message: "姓名不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询讲解员信息管理列表 */
    getList() {
      this.loading = true;
      listComentator(this.queryParams).then(response => {
        this.comentatorList = response.rows;
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
        comentatorId: null,
        comentatorName: null,
        phoneNumber: null,
        comentatorGender: null,
        comentatorIntroduction: null,
        photo: null,
        status: null,
        price: null,
        instruction: null,
        messageSet: null,
        createDate: null,
        updateDate: null,
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
      this.ids = selection.map(item => item.comentatorId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加讲解员信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const comentatorId = row.comentatorId || this.ids
      getComentator(comentatorId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改讲解员信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.comentatorId != null) {
            updateComentator(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComentator(this.form).then(response => {
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
      const comentatorIds = row.comentatorId || this.ids;
      this.$modal.confirm('是否确认删除讲解员信息管理编号为"' + comentatorIds + '"的数据项？').then(function() {
        return delComentator(comentatorIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/comentator/export', {
        ...this.queryParams
      }, `comentator_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
