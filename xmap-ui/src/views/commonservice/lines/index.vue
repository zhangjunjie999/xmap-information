<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="路线名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入路线名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="规划概述" prop="overview">
        <el-input
          v-model="queryParams.overview"
          placeholder="请输入规划概述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路线规划" prop="plan">
        <el-input
          v-model="queryParams.plan"
          placeholder="请输入路线规划"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路线类型" prop="type">
        <el-input
          v-model="queryParams.type"
          placeholder="请输入路线类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路线优势" prop="advantages">
        <el-input
          v-model="queryParams.advantages"
          placeholder="请输入路线优势"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐餐饮" prop="recommendedRestaurant">
        <el-input
          v-model="queryParams.recommendedRestaurant"
          placeholder="请输入推荐餐饮"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐酒店" prop="recommendedHotel">
        <el-input
          v-model="queryParams.recommendedHotel"
          placeholder="请输入推荐酒店"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="推荐文创" prop="recommendedSouvenir">
        <el-input
          v-model="queryParams.recommendedSouvenir"
          placeholder="请输入推荐文创"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="温馨提示" prop="tips">
        <el-input
          v-model="queryParams.tips"
          placeholder="请输入温馨提示"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="路线状态" prop="routeStatus">
        <el-input
          v-model="queryParams.routeStatus"
          placeholder="请输入路线状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论者" prop="observer">
        <el-input
          v-model="queryParams.observer"
          placeholder="请输入评论者"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评论时间" prop="commentDate">
        <el-date-picker clearable
          v-model="queryParams.commentDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择评论时间">
        </el-date-picker>
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
          v-hasPermi="['commonservice:lines:add']"
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
          v-hasPermi="['commonservice:lines:edit']"
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
          v-hasPermi="['commonservice:lines:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:lines:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="linesList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="路线ID" align="center" prop="routeId" />
      <el-table-column label="路线名称" align="center" prop="name"  :show-overflow-tooltip='true'/>
      <el-table-column label="规划概述" align="center" prop="overview"  :show-overflow-tooltip='true'/>
      <el-table-column label="路线规划" align="center" prop="plan"  :show-overflow-tooltip='true'/>
      <el-table-column label="路线类型" align="center" prop="type" />
      <el-table-column label="路线优势" align="center" prop="advantages"  :show-overflow-tooltip='true'/>
      <el-table-column label="推荐餐饮" align="center" prop="recommendedRestaurant"  :show-overflow-tooltip='true'/>
      <el-table-column label="推荐酒店" align="center" prop="recommendedHotel"  :show-overflow-tooltip='true'/>
      <el-table-column label="推荐文创" align="center" prop="recommendedSouvenir"  :show-overflow-tooltip='true'/>
      <el-table-column label="温馨提示" align="center" prop="tips"  :show-overflow-tooltip='true'/>
      <el-table-column label="路线状态" align="center" prop="routeStatus" />
      <el-table-column label="最新评论" align="center" prop="comment"  :show-overflow-tooltip='true'/>
      <el-table-column label="评论者" align="center" prop="observer" />
      <el-table-column label="评论时间" align="center" prop="commentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.commentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['commonservice:lines:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:lines:remove']"
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

    <!-- 添加或修改线路规划对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="路线名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入路线名称" />
        </el-form-item>
        <el-form-item label="规划概述" prop="overview">
          <el-input v-model="form.overview" placeholder="请输入规划概述" />
        </el-form-item>
        <el-form-item label="路线规划" prop="plan">
          <el-input v-model="form.plan" placeholder="请输入路线规划" />
        </el-form-item>
        <el-form-item label="路线类型" prop="type">
          <el-input v-model="form.type" placeholder="请输入路线类型" />
        </el-form-item>
        <el-form-item label="路线优势" prop="advantages">
          <el-input v-model="form.advantages" placeholder="请输入路线优势" />
        </el-form-item>
        <el-form-item label="推荐餐饮" prop="recommendedRestaurant">
          <el-input v-model="form.recommendedRestaurant" placeholder="请输入推荐餐饮" />
        </el-form-item>
        <el-form-item label="推荐酒店" prop="recommendedHotel">
          <el-input v-model="form.recommendedHotel" placeholder="请输入推荐酒店" />
        </el-form-item>
        <el-form-item label="推荐文创" prop="recommendedSouvenir">
          <el-input v-model="form.recommendedSouvenir" placeholder="请输入推荐文创" />
        </el-form-item>
        <el-form-item label="温馨提示" prop="tips">
          <el-input v-model="form.tips" placeholder="请输入温馨提示" />
        </el-form-item>
        <el-form-item label="路线状态" prop="routeStatus">
          <el-input v-model="form.routeStatus" placeholder="请输入路线状态" />
        </el-form-item>
        <el-form-item label="最新评论" prop="comment">
          <el-input v-model="form.comment" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="评论者" prop="observer">
          <el-input v-model="form.observer" placeholder="请输入评论者" />
        </el-form-item>
        <el-form-item label="评论时间" prop="commentDate">
          <el-date-picker clearable
            v-model="form.commentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择评论时间">
          </el-date-picker>
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
import { listLines, getLines, delLines, addLines, updateLines } from "@/api/commonservice/lines";

export default {
  name: "Lines",
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
      // 线路规划表格数据
      linesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        overview: null,
        plan: null,
        type: null,
        advantages: null,
        recommendedRestaurant: null,
        recommendedHotel: null,
        recommendedSouvenir: null,
        tips: null,
        routeStatus: null,
        comment: null,
        observer: null,
        commentDate: null,
        createDate: null,
        updateDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "路线名称不能为空", trigger: "blur" }
        ],
        plan: [
          { required: true, message: "路线规划不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "路线类型不能为空", trigger: "blur" }
        ],
        recommendedHotel: [
          { required: true, message: "推荐酒店不能为空", trigger: "blur" }
        ],
        recommendedSouvenir: [
          { required: true, message: "推荐文创不能为空", trigger: "blur" }
        ],
        tips: [
          { required: true, message: "温馨提示不能为空", trigger: "blur" }
        ],
        routeStatus: [
          { required: true, message: "路线状态不能为空", trigger: "blur" }
        ],
        comment: [
          { required: true, message: "最新评论不能为空", trigger: "blur" }
        ],
        observer: [
          { required: true, message: "评论者不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询线路规划列表 */
    getList() {
      this.loading = true;
      listLines(this.queryParams).then(response => {
        this.linesList = response.rows;
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
        routeId: null,
        name: null,
        overview: null,
        plan: null,
        type: null,
        advantages: null,
        recommendedRestaurant: null,
        recommendedHotel: null,
        recommendedSouvenir: null,
        tips: null,
        routeStatus: null,
        comment: null,
        observer: null,
        commentDate: null,
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
      this.ids = selection.map(item => item.routeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加线路规划";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const routeId = row.routeId || this.ids
      getLines(routeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改线路规划";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.routeId != null) {
            updateLines(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLines(this.form).then(response => {
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
      const routeIds = row.routeId || this.ids;
      this.$modal.confirm('是否确认删除线路规划编号为"' + routeIds + '"的数据项？').then(function() {
        return delLines(routeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/lines/export', {
        ...this.queryParams
      }, `lines_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
