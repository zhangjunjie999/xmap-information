<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="景点名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入景点名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="导览路线" prop="guideRoute">
        <el-input
          v-model="queryParams.guideRoute"
          placeholder="请输入导览路线"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="景点介绍" prop="introduction">
        <el-input
          v-model="queryParams.introduction"
          placeholder="请输入景点介绍"
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
          v-hasPermi="['commonservice:tourguide:add']"
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
          v-hasPermi="['commonservice:tourguide:edit']"
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
          v-hasPermi="['commonservice:tourguide:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:tourguide:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tourguideList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="景区ID" align="center" prop="scenicAreaId" />
      <el-table-column label="景点名称" align="center" prop="name"  :show-overflow-tooltip='true'/>
      <el-table-column label="景点地图" align="center" prop="map" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.map" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="导览路线" align="center" prop="guideRoute"  :show-overflow-tooltip='true'/>
      <el-table-column label="景点介绍" align="center" prop="introduction"  :show-overflow-tooltip='true'/>
      <el-table-column label="实时信息" align="center" prop="realTimeInfo"  :show-overflow-tooltip='true'/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:tourguide:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:tourguide:remove']"
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

    <!-- 添加或修改导游导览对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景点名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入景点名称" />
        </el-form-item>
        <el-form-item label="景点地图" prop="map">
          <image-upload v-model="form.map"/>
        </el-form-item>
        <el-form-item label="导览路线" prop="guideRoute">
          <el-input v-model="form.guideRoute" placeholder="请输入导览路线" />
        </el-form-item>
        <el-form-item label="景点介绍" prop="introduction">
          <el-input v-model="form.introduction" placeholder="请输入景点介绍" />
        </el-form-item>
        <el-form-item label="实时信息" prop="realTimeInfo">
          <el-input v-model="form.realTimeInfo" type="textarea" placeholder="请输入内容" />
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
import { listTourguide, getTourguide, delTourguide, addTourguide, updateTourguide } from "@/api/commonservice/tourguide";

export default {
  name: "Tourguide",
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
      // 导游导览表格数据
      tourguideList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        map: null,
        guideRoute: null,
        introduction: null,
        realTimeInfo: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "景点名称不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询导游导览列表 */
    getList() {
      this.loading = true;
      listTourguide(this.queryParams).then(response => {
        this.tourguideList = response.rows;
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
        scenicAreaId: null,
        name: null,
        map: null,
        guideRoute: null,
        introduction: null,
        realTimeInfo: null,
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
      this.ids = selection.map(item => item.scenicAreaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加导游导览";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scenicAreaId = row.scenicAreaId || this.ids
      getTourguide(scenicAreaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改导游导览";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scenicAreaId != null) {
            updateTourguide(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTourguide(this.form).then(response => {
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
      const scenicAreaIds = row.scenicAreaId || this.ids;
      this.$modal.confirm('是否确认删除导游导览编号为"' + scenicAreaIds + '"的数据项？').then(function() {
        return delTourguide(scenicAreaIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/tourguide/export', {
        ...this.queryParams
      }, `tourguide_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
