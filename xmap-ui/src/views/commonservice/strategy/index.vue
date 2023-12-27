<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="攻略名称" prop="guideName">
        <el-input
          v-model="queryParams.guideName"
          placeholder="请输入攻略名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="攻略简介" prop="guideSummary">
        <el-input
          v-model="queryParams.guideSummary"
          placeholder="请输入攻略简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="guideCreator">
        <el-input
          v-model="queryParams.guideCreator"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评价人" prop="reviewBy">
        <el-input
          v-model="queryParams.reviewBy"
          placeholder="请输入评价人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评价时间" prop="reviewTime">
        <el-date-picker clearable
          v-model="queryParams.reviewTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择评价时间">
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
          v-hasPermi="['commonservice:strategy:add']"
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
          v-hasPermi="['commonservice:strategy:edit']"
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
          v-hasPermi="['commonservice:strategy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:strategy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="strategyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="攻略ID" align="center" prop="guideId" />
      <el-table-column label="攻略名称" align="center" prop="guideName"  :show-overflow-tooltip='true'/>
      <el-table-column label="攻略内容" align="center" prop="guideContent"  :show-overflow-tooltip='true'/>
      <el-table-column label="攻略简介" align="center" prop="guideSummary"  :show-overflow-tooltip='true'/>
      <el-table-column label="创建人" align="center" prop="guideCreator" />
      <el-table-column label="高赞评价" align="center" prop="reviewContent"  :show-overflow-tooltip='true'/>
      <el-table-column label="评价人" align="center" prop="reviewBy" />
      <el-table-column label="评价时间" align="center" prop="reviewTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reviewTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:strategy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:strategy:remove']"
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

    <!-- 添加或修改攻略推荐对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="攻略名称" prop="guideName">
          <el-input v-model="form.guideName" placeholder="请输入攻略名称" />
        </el-form-item>
        <el-form-item label="攻略内容">
          <editor v-model="form.guideContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="攻略简介" prop="guideSummary">
          <el-input v-model="form.guideSummary" placeholder="请输入攻略简介" />
        </el-form-item>
        <el-form-item label="创建人" prop="guideCreator">
          <el-input v-model="form.guideCreator" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="高赞评价">
          <editor v-model="form.reviewContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="评价人" prop="reviewBy">
          <el-input v-model="form.reviewBy" placeholder="请输入评价人" />
        </el-form-item>
        <el-form-item label="评价时间" prop="reviewTime">
          <el-date-picker clearable
            v-model="form.reviewTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评价时间">
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
import { listStrategy, getStrategy, delStrategy, addStrategy, updateStrategy } from "@/api/commonservice/strategy";

export default {
  name: "Strategy",
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
      // 攻略推荐表格数据
      strategyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        guideName: null,
        guideContent: null,
        guideSummary: null,
        guideCreator: null,
        reviewContent: null,
        reviewBy: null,
        reviewTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        guideName: [
          { required: true, message: "攻略名称不能为空", trigger: "blur" }
        ],
        guideSummary: [
          { required: true, message: "攻略简介不能为空", trigger: "blur" }
        ],
        guideCreator: [
          { required: true, message: "创建人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询攻略推荐列表 */
    getList() {
      this.loading = true;
      listStrategy(this.queryParams).then(response => {
        this.strategyList = response.rows;
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
        guideId: null,
        guideName: null,
        guideContent: null,
        guideSummary: null,
        guideCreator: null,
        reviewContent: null,
        reviewBy: null,
        reviewTime: null,
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
      this.ids = selection.map(item => item.guideId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加攻略推荐";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const guideId = row.guideId || this.ids
      getStrategy(guideId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改攻略推荐";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.guideId != null) {
            updateStrategy(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStrategy(this.form).then(response => {
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
      const guideIds = row.guideId || this.ids;
      this.$modal.confirm('是否确认删除攻略推荐编号为"' + guideIds + '"的数据项？').then(function() {
        return delStrategy(guideIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/strategy/export', {
        ...this.queryParams
      }, `strategy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
