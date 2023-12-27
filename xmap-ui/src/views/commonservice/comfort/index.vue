<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="景区名称" prop="comfortName">
        <el-input
          v-model="queryParams.comfortName"
          placeholder="请输入景区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实时人数" prop="comfortNumber">
        <el-input
          v-model="queryParams.comfortNumber"
          placeholder="请输入实时人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最佳容量" prop="comfortBest">
        <el-input
          v-model="queryParams.comfortBest"
          placeholder="请输入最佳容量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最大容量" prop="comfortBiggest">
        <el-input
          v-model="queryParams.comfortBiggest"
          placeholder="请输入最大容量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实时天气" prop="comfortWeather">
        <el-input
          v-model="queryParams.comfortWeather"
          placeholder="请输入实时天气"
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
          v-hasPermi="['commonservice:comfort:add']"
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
          v-hasPermi="['commonservice:comfort:edit']"
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
          v-hasPermi="['commonservice:comfort:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:comfort:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="comfortList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="景区ID" align="center" prop="comfortId" show-overflow-tooltip="true"/>
      <el-table-column label="景区名称" align="center" prop="comfortName" show-overflow-tooltip="true"/>
      <el-table-column label="实时人数" align="center" prop="comfortNumber" show-overflow-tooltip="true"/>
      <el-table-column label="最佳容量" align="center" prop="comfortBest" show-overflow-tooltip="true"/>
      <el-table-column label="最大容量" align="center" prop="comfortBiggest" show-overflow-tooltip="true"/>
      <el-table-column label="实时天气" align="center" prop="comfortWeather" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:comfort:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:comfort:remove']"
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

    <!-- 添加或修改旅游舒适度对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景区名称" prop="comfortName">
          <el-input v-model="form.comfortName" placeholder="请输入景区名称" />
        </el-form-item>
        <el-form-item label="实时人数" prop="comfortNumber">
          <el-input v-model="form.comfortNumber" placeholder="请输入实时人数" />
        </el-form-item>
        <el-form-item label="最佳容量" prop="comfortBest">
          <el-input v-model="form.comfortBest" placeholder="请输入最佳容量" />
        </el-form-item>
        <el-form-item label="最大容量" prop="comfortBiggest">
          <el-input v-model="form.comfortBiggest" placeholder="请输入最大容量" />
        </el-form-item>
        <el-form-item label="实时天气" prop="comfortWeather">
          <el-input v-model="form.comfortWeather" placeholder="请输入实时天气" />
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
import { listComfort, getComfort, delComfort, addComfort, updateComfort } from "@/api/commonservice/comfort";

export default {
  name: "Comfort",
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
      // 旅游舒适度表格数据
      comfortList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        comfortName: null,
        comfortNumber: null,
        comfortBest: null,
        comfortBiggest: null,
        comfortWeather: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        comfortName: [
          { required: true, message: "景区名称不能为空", trigger: "blur" }
        ],
        comfortBest: [
          { required: true, message: "最佳容量不能为空", trigger: "blur" }
        ],
        comfortBiggest: [
          { required: true, message: "最大容量不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询旅游舒适度列表 */
    getList() {
      this.loading = true;
      listComfort(this.queryParams).then(response => {
        this.comfortList = response.rows;
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
        comfortId: null,
        comfortName: null,
        comfortNumber: null,
        comfortBest: null,
        comfortBiggest: null,
        comfortWeather: null,
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
      this.ids = selection.map(item => item.comfortId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加旅游舒适度";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const comfortId = row.comfortId || this.ids
      getComfort(comfortId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改旅游舒适度";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.comfortId != null) {
            updateComfort(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComfort(this.form).then(response => {
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
      const comfortIds = row.comfortId || this.ids;
      this.$modal.confirm('是否确认删除旅游舒适度编号为"' + comfortIds + '"的数据项？').then(function() {
        return delComfort(comfortIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/comfort/export', {
        ...this.queryParams
      }, `comfort_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
