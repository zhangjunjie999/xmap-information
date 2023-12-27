<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="景点名" prop="siteName">
        <el-input
          v-model="queryParams.siteName"
          placeholder="请输入景点名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="发布人" prop="publisher">
        <el-input
          v-model="queryParams.publisher"
          placeholder="请输入发布人"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['information:scenic1:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['information:scenic1:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['information:scenic1:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['information:scenic1:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scenic1List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="景点名" align="center" prop="siteName" show-overflow-tooltip="true"/>
      <el-table-column label="通知公告" align="center" prop="notice" show-overflow-tooltip="true"/>
      <el-table-column label="在园人数" align="center" prop="attendance" show-overflow-tooltip="true"/>
      <el-table-column label="剩余停车位" align="center" prop="parking" width="90"/>
      <el-table-column label="票价" align="center" prop="ticket" show-overflow-tooltip="true"/>
      <el-table-column label="建议景点" align="center" prop="nextScenic" show-overflow-tooltip="true"/>
      <el-table-column label="景点人数" align="center" prop="numberScenic" show-overflow-tooltip="true"/>
      <el-table-column label="附近景点" align="center" prop="nearbyScenic" show-overflow-tooltip="true"/>
      <el-table-column label="救援电话" align="center" prop="emergencyPhone" show-overflow-tooltip="true"/>
      <el-table-column label="发布人" align="center" prop="publisher" show-overflow-tooltip="true"/>
      <el-table-column label="审核状态" align="center" prop="verifyStatus">
        <template slot-scope="scope">
          <div :style="{
            color: scope.row.verifyStatus ? 'red' : 'red',
            fontSize: '10px',
            border: '1px solid #ccc'
          }">
            {{ scope.row.verifyStatus }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="审核人" align="center" prop="verifyPeople" />
      <el-table-column label="审核日期" align="center" prop="verifyDate" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.verifyDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding " width="100">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['information:scenic1:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-check"
            @click="handleAudit(scope.row)"
            v-hasPermi="['information:verify:edit']"
          >审核</el-button>
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

    <!-- 审核景点引导牌对话框 -->
    <el-dialog :title="title" :visible.sync="audit" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景点名" prop="siteName">
          <el-input v-model="form.siteName" placeholder="请输入景点名" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="通知公告" prop="notice">
          <el-input v-model="form.notice"  placeholder="请输入内容" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="在园人数" prop="attendance">
          <el-input v-model="form.attendance" placeholder="请输入在园人数" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="剩余停车位" prop="parking">
          <el-input v-model="form.parking" placeholder="请输入剩余停车位" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="票价" prop="ticket">
          <el-input v-model="form.ticket" placeholder="请输入票价" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="建议下一个景点" prop="nextScenic">
          <el-input v-model="form.nextScenic" placeholder="请输入建议下一个景点" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="景点人数" prop="numberScenic">
          <el-input v-model="form.numberScenic" placeholder="请输入景点人数" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="附近景点" prop="nearbyScenic">
          <el-input v-model="form.nearbyScenic" placeholder="请输入附近景点" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="救援电话" prop="emergencyPhone">
          <el-input v-model="form.emergencyPhone" placeholder="请输入救援电话" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="审核人" prop="verifyPeople">
          <el-select v-model="form.verifyPeople" placeholder="请输入审核人">
            <el-option
              v-for="site in userList"
              :key="site.id"
              :label="site.userName"
              :value="site.userName">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="审核日期" prop="verifyDate">
          <el-date-picker
            clearable
            :disabled="single"
            v-model="form.verifyDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择审核日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="审核状态" prop="status">
          <el-select v-model="form.verifyStatus" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.label">
            </el-option>
          </el-select>
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
import { listScenic1, getScenic1, delScenic1, addScenic1, updateScenic1, listUser} from "@/api/information/scenic1";
export default {
  name: "Scenic1",
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
      // 景点引导牌表格数据
      scenic1List: [],
      // 用户数据
      userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      audit: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        siteName: null,
        notice: null,
        weather: null,
        attendance: null,
        parking: null,
        ticket: null,
        nextScenic: null,
        numberScenic: null,
        nearbyScenic: null,
        emergencyPhone: null,
        verifyStatus: null,
        verifyPeople: null,
        verifyDate: null,
        publisher: null,
        publishTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      options: [{
        value: '选项2',
        label: '审核通过'
      }, {
        value: '选项3',
        label: '审核不通过'
      }]
    };
  },
  created() {
    this.getList();
    this.getNowTime();
    this.listUserData();
  },
  methods: {
    /**获取用户数据 */
    listUserData(){
      this.loading = true;
      listUser(this.queryParams).then(response => {
        this.userList = JSON.parse(JSON.stringify(response.rows));
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 获取当前日期时间*/
    getNowTime() {
      let date = new Date();
      let sign2 = ":";
      let year = date.getFullYear(); // 年
      let month = date.getMonth() + 1; // 月
      let day = date.getDate(); // 日
      let hour = date.getHours(); // 时
      let minutes = date.getMinutes(); // 分
      let seconds = date.getSeconds(); //秒
      // 给一位数的数据前面加 “0”
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (day >= 0 && day <= 9) {
        day = "0" + day;
      }
      if (hour >= 0 && hour <= 9) {
        hour = "0" + hour;
      }
      if (minutes >= 0 && minutes <= 9) {
        minutes = "0" + minutes;
      }
      if (seconds >= 0 && seconds <= 9) {
        seconds = "0" + seconds;
      }
      let defaultDate =
        year +
        "-" +
        month +
        "-" +
        day +
        " " +
        hour +
        sign2 +
        minutes +
        sign2 +
        seconds;
      return defaultDate;
    },
    /** 审核按钮操作 */
    handleAudit(row) {
      this.reset();
      const id = row.id || this.ids
      getScenic1(id).then(response => {
        this.form = response.data;
        this.form.verifyDate = this.getNowTime();
        this.audit = true;
        this.title = "审核内容管理列";
      });
    },
    /** 查询景点引导牌列表 */
    getList() {
      this.loading = true;
      listScenic1(this.queryParams).then(response => {
        this.scenic1List = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.audit = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        siteName: null,
        notice: null,
        weather: null,
        attendance: null,
        parking: null,
        ticket: null,
        nextScenic: null,
        numberScenic: null,
        nearbyScenic: null,
        emergencyPhone: null,
        verifyStatus: null,
        verifyPeople: null,
        verifyDate: this.getNowTime(),
        publisher: null,
        publishTime: null
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
      this.title = "添加景点引导牌";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getScenic1(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改景点引导牌";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateScenic1(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.audit = false;
              this.getList();
            });
          } else {
            addScenic1(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.audit = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除景点引导牌编号为"' + ids + '"的数据项？').then(function() {
        return delScenic1(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('information/scenic1/export', {
        ...this.queryParams
      }, `scenic1_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.custom-input .el-input__inner {
  background-color: #f0f0f0 !important;
}
</style>
