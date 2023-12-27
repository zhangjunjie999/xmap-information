<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入活动名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动地点" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入活动地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动景点" prop="attraction">
        <el-input
          v-model="queryParams.attraction"
          placeholder="请输入活动景点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动类型" prop="type">
        <el-input
          v-model="queryParams.type"
          placeholder="请输入活动类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动时间" prop="activityTime">
        <el-input
          v-model="queryParams.activityTime"
          placeholder="请输入活动时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动人数" prop="participants">
        <el-input
          v-model="queryParams.participants"
          placeholder="请输入活动人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="启用状态" prop="activityStatus">
        <el-input
          v-model="queryParams.activityStatus"
          placeholder="请输入启用状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核人" prop="auditorName">
        <el-input
          v-model="queryParams.auditorName"
          placeholder="请输入审核人"
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
          v-hasPermi="['commonservice:attractions:add']"
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
          v-hasPermi="['commonservice:attractions:edit']"
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
          v-hasPermi="['commonservice:attractions:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:attractions:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="attractionsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="活动ID" align="center" prop="activityId" />
      <el-table-column label="活动名称" align="center" prop="name"  :show-overflow-tooltip='true'/>
      <el-table-column label="活动地点" align="center" prop="location"  :show-overflow-tooltip='true'/>
      <el-table-column label="活动景点" align="center" prop="attraction"  :show-overflow-tooltip='true'/>
      <el-table-column label="活动类型" align="center" prop="type" />
      <el-table-column label="活动时间" align="center" prop="activityTime"  :show-overflow-tooltip='true'/>
      <el-table-column label="活动人数" align="center" prop="participants" />
      <el-table-column label="活动介绍" align="center" prop="description"  :show-overflow-tooltip='true'/>
      <el-table-column label="活动图片" align="center" prop="activityImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.activityImage" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="启用状态" align="center" prop="activityStatus" />
      <el-table-column label="审核人" align="center" prop="auditorName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commonservice:attractions:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:attractions:remove']"
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

    <!-- 添加或修改景点介绍对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入活动名称" />
        </el-form-item>
        <el-form-item label="活动地点" prop="location">
          <el-input v-model="form.location" placeholder="请输入活动地点" />
        </el-form-item>
        <el-form-item label="活动景点" prop="attraction">
          <el-input v-model="form.attraction" placeholder="请输入活动景点" />
        </el-form-item>
        <el-form-item label="活动类型" prop="type">
          <el-input v-model="form.type" placeholder="请输入活动类型" />
        </el-form-item>
        <el-form-item label="活动时间" prop="activityTime">
          <el-input v-model="form.activityTime" placeholder="请输入活动时间" />
        </el-form-item>
        <el-form-item label="活动人数" prop="participants">
          <el-input v-model="form.participants" placeholder="请输入活动人数" />
        </el-form-item>
        <el-form-item label="活动介绍" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="活动图片" prop="activityImage">
          <image-upload v-model="form.activityImage"/>
        </el-form-item>
        <el-form-item label="启用状态" prop="activityStatus">
          <el-input v-model="form.activityStatus" placeholder="请输入启用状态" />
        </el-form-item>
        <el-form-item label="审核人" prop="auditorName">
          <el-input v-model="form.auditorName" placeholder="请输入审核人" />
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
import { listAttractions, getAttractions, delAttractions, addAttractions, updateAttractions } from "@/api/commonservice/attractions";

export default {
  name: "Attractions",
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
      // 景点介绍表格数据
      attractionsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
        location: null,
        attraction: null,
        type: null,
        activityTime: null,
        participants: null,
        description: null,
        activityImage: null,
        activityStatus: null,
        auditorName: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: true, message: "活动名称不能为空", trigger: "blur" }
        ],
        attraction: [
          { required: true, message: "活动景点不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "活动类型不能为空", trigger: "blur" }
        ],
        participants: [
          { required: true, message: "活动人数不能为空", trigger: "blur" }
        ],
        auditorName: [
          { required: true, message: "审核人不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询景点介绍列表 */
    getList() {
      this.loading = true;
      listAttractions(this.queryParams).then(response => {
        this.attractionsList = response.rows;
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
        activityId: null,
        name: null,
        location: null,
        attraction: null,
        type: null,
        activityTime: null,
        participants: null,
        description: null,
        activityImage: null,
        activityStatus: null,
        auditorName: null,
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
      this.ids = selection.map(item => item.activityId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加景点介绍";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const activityId = row.activityId || this.ids
      getAttractions(activityId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改景点介绍";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.activityId != null) {
            updateAttractions(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAttractions(this.form).then(response => {
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
      const activityIds = row.activityId || this.ids;
      this.$modal.confirm('是否确认删除景点介绍编号为"' + activityIds + '"的数据项？').then(function() {
        return delAttractions(activityIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/attractions/export', {
        ...this.queryParams
      }, `attractions_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
