<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车牌号" prop="plateNumber">
        <el-input
          v-model="queryParams.plateNumber"
          placeholder="请输入车牌号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车型号" prop="tourbusType">
        <el-input
          v-model="queryParams.tourbusType"
          placeholder="请输入车型号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车参数" prop="tourbusParameter">
        <el-input
          v-model="queryParams.tourbusParameter"
          placeholder="请输入车参数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车配置" prop="tourbusConfiguration">
        <el-input
          v-model="queryParams.tourbusConfiguration"
          placeholder="请输入车配置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="使用年限" prop="tourbusAge">
        <el-input
          v-model="queryParams.tourbusAge"
          placeholder="请输入使用年限"
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
          v-hasPermi="['commonservice:tourbus:add']"
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
          v-hasPermi="['commonservice:tourbus:edit']"
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
          v-hasPermi="['commonservice:tourbus:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:tourbus:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tourbusList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="观光车ID" align="center" prop="tourbusId" />
      <el-table-column label="车牌号" align="center" prop="plateNumber"  :show-overflow-tooltip='true'/>
      <el-table-column label="车型号" align="center" prop="tourbusType"  :show-overflow-tooltip='true'/>
      <el-table-column label="车参数" align="center" prop="tourbusParameter"  :show-overflow-tooltip='true'/>
      <el-table-column label="车配置" align="center" prop="tourbusConfiguration"  :show-overflow-tooltip='true'/>
      <el-table-column label="使用年限" align="center" prop="tourbusAge" />
      <el-table-column label="司机信息" align="center" prop="driverInfo"  :show-overflow-tooltip='true'/>
      <el-table-column label="状态" align="center" prop="status"  :show-overflow-tooltip='true'/>
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
            v-hasPermi="['commonservice:tourbus:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:tourbus:remove']"
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

    <!-- 添加或修改观光车信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌号" prop="plateNumber">
          <el-input v-model="form.plateNumber" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="车型号" prop="tourbusType">
          <el-input v-model="form.tourbusType" placeholder="请输入车型号" />
        </el-form-item>
        <el-form-item label="车参数" prop="tourbusParameter">
          <el-input v-model="form.tourbusParameter" placeholder="请输入车参数" />
        </el-form-item>
        <el-form-item label="车配置" prop="tourbusConfiguration">
          <el-input v-model="form.tourbusConfiguration" placeholder="请输入车配置" />
        </el-form-item>
        <el-form-item label="使用年限" prop="tourbusAge">
          <el-input v-model="form.tourbusAge" placeholder="请输入使用年限" />
        </el-form-item>
        <el-form-item label="司机信息" prop="driverInfo">
          <el-input v-model="form.driverInfo" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="form.status" placeholder="请输入状态" />
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
import { listTourbus, getTourbus, delTourbus, addTourbus, updateTourbus } from "@/api/commonservice/tourbus";

export default {
  name: "Tourbus",
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
      // 观光车信息管理表格数据
      tourbusList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        plateNumber: null,
        tourbusType: null,
        tourbusParameter: null,
        tourbusConfiguration: null,
        tourbusAge: null,
        driverInfo: null,
        status: null,
        createDate: null,
        updateDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        plateNumber: [
          { required: true, message: "车牌号不能为空", trigger: "blur" }
        ],
        tourbusParameter: [
          { required: true, message: "车参数不能为空", trigger: "blur" }
        ],
        tourbusConfiguration: [
          { required: true, message: "车配置不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询观光车信息管理列表 */
    getList() {
      this.loading = true;
      listTourbus(this.queryParams).then(response => {
        this.tourbusList = response.rows;
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
        tourbusId: null,
        plateNumber: null,
        tourbusType: null,
        tourbusParameter: null,
        tourbusConfiguration: null,
        tourbusAge: null,
        driverInfo: null,
        status: null,
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
      this.ids = selection.map(item => item.tourbusId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加观光车信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tourbusId = row.tourbusId || this.ids
      getTourbus(tourbusId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改观光车信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tourbusId != null) {
            updateTourbus(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTourbus(this.form).then(response => {
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
      const tourbusIds = row.tourbusId || this.ids;
      this.$modal.confirm('是否确认删除观光车信息管理编号为"' + tourbusIds + '"的数据项？').then(function() {
        return delTourbus(tourbusIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/tourbus/export', {
        ...this.queryParams
      }, `tourbus_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
