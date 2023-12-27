<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="信息标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入信息标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信息类型" prop="type">
        <el-input
          v-model="queryParams.type"
          placeholder="请输入信息类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="信息状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入信息状态"
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
          v-hasPermi="['information:message:add']"
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
          v-hasPermi="['information:message:edit']"
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
          v-hasPermi="['information:message:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['information:message:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="messageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="信息ID" align="center" prop="id" />
      <el-table-column label="信息类型" align="center" prop="type" />
      <el-table-column label="信息标题" align="center" prop="title" show-overflow-tooltip="true"/>
      <el-table-column label="信息内容" align="center" prop="content" show-overflow-tooltip="true">
<!--        <template slot-scope="scope">-->
<!--          <div  @click="showFullContent(scope.row.content)" class="text-container">-->
<!--            {{ scope.row.content }}-->
<!--          </div>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="信息状态" align="center" prop="status" />
      <el-table-column label="添加时间" align="center" prop="addTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.addTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修改时间" align="center" prop="changeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.changeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['information:message:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['information:message:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

<!--    <el-dialog :visible.sync="showFullContentDialog">-->
<!--      <div v-html="selectedContent"></div>-->
<!--      <span slot="footer" class="dialog-footer">-->
<!--        <el-button @click="showFullContentDialog = false">关闭</el-button>-->
<!--    </span>-->
<!--    </el-dialog>-->

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改井冈山基本信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="信息类型" prop="title">
          <el-input v-model="form.type" placeholder="请输入信息类型" />
        </el-form-item>
        <el-form-item label="信息标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入信息标题" />
        </el-form-item>
        <el-form-item label="信息内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="信息状态" prop="title">
          <el-radio v-model="form.status" label="启用" border>启用</el-radio>
          <el-radio v-model="form.status" label="停用" border>停用</el-radio>
        </el-form-item>
        <el-form-item label="添加时间" prop="addTime">
          <el-date-picker clearable
            v-model="form.addTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择添加时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修改时间" prop="changeTime">
          <el-date-picker clearable
            v-model="form.changeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择修改时间">
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
import { listMessage, getMessage, delMessage, addMessage, updateMessage } from "@/api/information/message";

export default {
  name: "Message",
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
      // 井冈山基本信息管理表格数据
      messageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        title: null,
        content: null,
        status: null,
        addTime: null,
        changeTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      radio: '1',
      // showFullContentDialog: false,
      // selectedContent: ''

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询井冈山基本信息管理列表 */
    getList() {
      this.loading = true;
      listMessage(this.queryParams).then(response => {
        this.messageList = response.rows;
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
        id: null,
        type: null,
        title: null,
        content: null,
        status: null,
        addTime: null,
        changeTime: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加井冈山基本信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getMessage(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改井冈山基本信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateMessage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMessage(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除井冈山基本信息管理编号为"' + ids + '"的数据项？').then(function() {
        return delMessage(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('information/message/export', {
        ...this.queryParams
      }, `message_${new Date().getTime()}.xlsx`)
    },
    /** 展示省略内容 */
    // showFullContent(content) {
    //   this.selectedContent = content;
    //   this.showFullContentDialog = true;
    // }
  }
};
</script>


<style>
.text-container {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

</style>
