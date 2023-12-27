<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="游客流量分析" prop="visitorCount">
        <el-input
          v-model="queryParams.visitorCount"
          placeholder="请输入游客流量分析"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门票销售统计" prop="ticketSales">
        <el-input
          v-model="queryParams.ticketSales"
          placeholder="请输入门票销售统计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点热度统计" prop="attractionStatistics">
        <el-input
          v-model="queryParams.attractionStatistics"
          placeholder="请输入景点热度统计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="员工管理统计" prop="employeeManage">
        <el-input
          v-model="queryParams.employeeManage"
          placeholder="请输入员工管理统计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="统计时间" prop="statisticsTime">
        <el-input
          v-model="queryParams.statisticsTime"
          placeholder="请输入统计时间"
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
          v-hasPermi="['information:analy:add']"
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
          v-hasPermi="['information:analy:edit']"
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
          v-hasPermi="['information:analy:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['information:analy:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="analyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="统计ID" align="center" prop="statisticsId" />
      <el-table-column label="游客流量分析" align="center" prop="visitorCount" />
      <el-table-column label="门票销售统计" align="center" prop="ticketSales" />
      <el-table-column label="景点热度统计" align="center" prop="attractionStatistics" />
      <el-table-column label="员工管理统计" align="center" prop="employeeManage" />
      <el-table-column label="统计时间" align="center" prop="statisticsTime" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['information:analy:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['information:analy:remove']"
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

    <!-- 添加或修改统计分析对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="游客流量分析" prop="visitorCount">
          <el-input v-model="form.visitorCount" placeholder="请输入游客流量分析" />
        </el-form-item>
        <el-form-item label="门票销售统计" prop="ticketSales">
          <el-input v-model="form.ticketSales" placeholder="请输入门票销售统计" />
        </el-form-item>
        <el-form-item label="景点热度统计" prop="attractionStatistics">
          <el-input v-model="form.attractionStatistics" placeholder="请输入景点热度统计" />
        </el-form-item>
        <el-form-item label="员工管理统计" prop="employeeManage">
          <el-input v-model="form.employeeManage" placeholder="请输入员工管理统计" />
        </el-form-item>
        <el-form-item label="统计时间" prop="statisticsTime">
          <el-date-picker clearable
                          v-model="form.statisticsTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择统计时间">
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
import { listAnaly, getAnaly, delAnaly, addAnaly, updateAnaly } from "@/api/information/analy";

export default {
  name: "Analy",
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
      // 统计分析表格数据
      analyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        visitorCount: null,
        ticketSales: null,
        attractionStatistics: null,
        employeeManage: null,
        statisticsTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询统计分析列表 */
    getList() {
      this.loading = true;
      listAnaly(this.queryParams).then(response => {
        this.analyList = response.rows;
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
        statisticsId: null,
        visitorCount: null,
        ticketSales: null,
        attractionStatistics: null,
        employeeManage: null,
        statisticsTime: null
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
      this.ids = selection.map(item => item.statisticsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加统计分析";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const statisticsId = row.statisticsId || this.ids
      getAnaly(statisticsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改统计分析";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.statisticsId != null) {
            updateAnaly(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAnaly(this.form).then(response => {
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
      const statisticsIds = row.statisticsId || this.ids;
      this.$modal.confirm('是否确认删除统计分析编号为"' + statisticsIds + '"的数据项？').then(function() {
        return delAnaly(statisticsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('information/analy/export', {
        ...this.queryParams
      }, `analy_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
