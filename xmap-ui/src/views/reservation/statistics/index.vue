<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="景点名称" prop="attractionName">
        <el-input
          v-model="queryParams.attractionName"
          placeholder="请输入景点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="酒店名称" prop="hotelName">
        <el-input
          v-model="queryParams.hotelName"
          placeholder="请输入酒店名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="餐饮名称" prop="restaurantName">
        <el-input
          v-model="queryParams.restaurantName"
          placeholder="请输入餐饮名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="停车位名称" prop="parkingName" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.parkingName"
          placeholder="请输入停车位名称"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 15px;"
        />
      </el-form-item>
      <el-form-item label="讲解员名称" prop="guideName" style="white-space: nowrap;">
        <el-input
          v-model="queryParams.guideName"
          placeholder="请输入讲解员名称"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 10px;"
        />
      </el-form-item>
      <el-form-item label="预约时间范围" prop="reservationTimeRange" style="white-space: nowrap;margin-left: 10px;">
        <el-input
          v-model="queryParams.reservationTimeRange"
          placeholder="请输入预约时间范围"
          clearable
          @keyup.enter.native="handleQuery"
          style="margin-left: 30px;"
        />
      </el-form-item>
      <el-form-item label="预约人数" prop="reservationCount" style="margin-left: 30px;">
        <el-input
          v-model="queryParams.reservationCount"
          placeholder="请输入预约人数"
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
          v-hasPermi="['reservation:statistics:add']"
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
          v-hasPermi="['reservation:statistics:edit']"
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
          v-hasPermi="['reservation:statistics:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['reservation:statistics:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="statisticsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="统计ID" align="center" prop="statisticId" />
      <el-table-column label="景点名称" align="center" prop="attractionName" />
      <el-table-column label="酒店名称" align="center" prop="hotelName" />
      <el-table-column label="餐饮名称" align="center" prop="restaurantName" />
      <el-table-column label="停车位名称" align="center" prop="parkingName" />
      <el-table-column label="讲解员名称" align="center" prop="guideName" />
      <el-table-column label="预约时间范围" align="center" prop="reservationTimeRange" />
      <el-table-column label="预约类型" align="center" prop="reservationType" />
      <el-table-column label="预约人数" align="center" prop="reservationCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['reservation:statistics:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['reservation:statistics:remove']"
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

    <!-- 添加或修改预约数据统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景点名称" prop="attractionName">
          <el-input v-model="form.attractionName" placeholder="请输入景点名称" />
        </el-form-item>
        <el-form-item label="酒店名称" prop="hotelName">
          <el-input v-model="form.hotelName" placeholder="请输入酒店名称" />
        </el-form-item>
        <el-form-item label="餐饮名称" prop="restaurantName">
          <el-input v-model="form.restaurantName" placeholder="请输入餐饮名称" />
        </el-form-item>
        <el-form-item label="停车位名称" prop="parkingName" style="white-space: nowrap;">
          <el-input v-model="form.parkingName" placeholder="请输入停车位名称" />
        </el-form-item>
        <el-form-item label="讲解员名称" prop="guideName" style="white-space: nowrap;">
          <el-input v-model="form.guideName" placeholder="请输入讲解员名称" />
        </el-form-item>
        <el-form-item label="预约时间范围" prop="reservationTimeRange" style="white-space: nowrap;">
          <el-input v-model="form.reservationTimeRange" placeholder="请输入预约时间范围" style="margin-left: 10px; width: 370px;"/>
        </el-form-item>
        <el-form-item label="预约类型" prop="reservationType">
          <el-select v-model="form.reservationType" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="预约人数" prop="reservationCount">
          <el-input v-model="form.reservationCount" placeholder="请输入预约人数" />
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
import { listStatistics, getStatistics, delStatistics, addStatistics, updateStatistics } from "@/api/reservation/statistics";

export default {
  name: "Statistics",
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
      // 预约数据统计表格数据
      statisticsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        attractionName: null,
        hotelName: null,
        restaurantName: null,
        parkingName: null,
        guideName: null,
        reservationTimeRange: null,
        reservationType: null,
        reservationCount: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options:[
        {
          value:'到店预约',
          label:'到店预约',
        },
        {
          value:'电话预约',
          label:'电话预约',
        },
        {
          value:'线上预约',
          label:'线上预约',
        }
      ]
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询预约数据统计列表 */
    getList() {
      this.loading = true;
      listStatistics(this.queryParams).then(response => {
        this.statisticsList = response.rows;
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
        statisticId: null,
        attractionName: null,
        hotelName: null,
        restaurantName: null,
        parkingName: null,
        guideName: null,
        reservationTimeRange: null,
        reservationType: null,
        reservationCount: null
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
      this.ids = selection.map(item => item.statisticId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加预约数据统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const statisticId = row.statisticId || this.ids
      getStatistics(statisticId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改预约数据统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.statisticId != null) {
            updateStatistics(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStatistics(this.form).then(response => {
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
      const statisticIds = row.statisticId || this.ids;
      this.$modal.confirm('是否确认删除预约数据统计编号为"' + statisticIds + '"的数据项？').then(function() {
        return delStatistics(statisticIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('reservation/statistics/export', {
        ...this.queryParams
      }, `statistics_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
