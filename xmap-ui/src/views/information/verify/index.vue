<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="审核类型" prop="type">
        <el-input
          v-model="queryParams.type"
          placeholder="请输入审核类型"
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
      <el-form-item label="审核状态" prop="status">
        <el-input
          v-model="queryParams.status"
          placeholder="请输入审核状态"
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['information:verify:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="verifyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="审核类型" align="center" prop="type" show-overflow-tooltip="true"/>
      <el-table-column label="站点名" align="center" prop="siteName" show-overflow-tooltip="true"/>
      <el-table-column label="通知公告" align="center" prop="notice" show-overflow-tooltip="true"/>
      <el-table-column label="图标" align="center" prop="icon" show-overflow-tooltip="true">
        <template slot-scope="scope">
          <image-preview :src="scope.row.icon" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="距离" align="center" prop="distance" show-overflow-tooltip="true"/>
      <el-table-column label="位置" align="center" prop="location" show-overflow-tooltip="true"/>
      <el-table-column label="剩余车位" align="center" prop="availablePark" show-overflow-tooltip="true"/>
      <el-table-column label="已用车位" align="center" prop="occupiedPark" show-overflow-tooltip="true"/>
      <el-table-column label="周边停车场" align="center" prop="nearbyPark" show-overflow-tooltip="true" width="100"/>
      <el-table-column label="空余位数" align="center" prop="availableSpace" show-overflow-tooltip="true"/>
      <el-table-column label="救援电话" align="center" prop="emergencyPhone" show-overflow-tooltip="true"/>
      <el-table-column label="发布人" align="center" prop="publisher" />
      <el-table-column label="审核人" align="center" prop="people" />
      <el-table-column label="审核状态" align="center" prop="status" show-overflow-tooltip="true">
        <template slot-scope="scope">
          <div :style="{
            color: scope.row.status ? 'red' : 'red',
            fontSize: '10px',
            border: '1px solid #ccc'
          }">
            {{ scope.row.status }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="审核日期" align="center" prop="verifyDate" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding" width="200">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['information:verify:remove']"
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

    <!-- 添加或修改审核管理列对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审核类型" prop="type">
          <el-input v-model="form.type"  placeholder="请输入类型" />
        </el-form-item>
        <el-form-item label="发布人" prop="publisher">
          <el-input v-model="form.publisher" placeholder="请输入发布人" />
        </el-form-item>
        <el-form-item label="审核人" prop="people">
          <el-input v-model="form.people"  placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="审核状态" prop="status">
          <el-input v-model="form.status"  placeholder="请输入状态" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>



    <el-dialog :title="title" :visible.sync="audit" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px" >
        <el-form-item label="审核类型" prop="type">
          <el-input v-model="form.type"  placeholder="请输入类型" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="站点名" prop="siteName">
          <el-input v-model="form.siteName"  placeholder="请选择地点标志"  :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="通知公告" prop="notice">
          <el-input v-model="form.notice"  placeholder="请输入内容" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <image-upload v-model="form.icon"/>
        </el-form-item>
        <el-form-item label="距离" prop="distance">
          <el-input v-model="form.distance" placeholder="请输入距离" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入位置" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="剩余车位" prop="availablePark">
          <el-input v-model="form.availablePark" placeholder="请输入剩余车位" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="已用车位" prop="occupiedPark">
          <el-input v-model="form.occupiedPark" placeholder="请输入已用车位" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="周边停车场" prop="nearbyPark" style="white-space: nowrap">
          <el-input v-model="form.nearbyPark" placeholder="请输入周边停车场" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="空余位数" prop="availableSpace">
          <el-input v-model="form.availableSpace" placeholder="请输入空余位数" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="救援电话" prop="emergencyPhone">
          <el-input v-model="form.emergencyPhone" placeholder="请输入救援电话" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="发布人" prop="publisher">
          <el-input v-model="form.publisher" placeholder="请输入发布人" :readonly="true" class="custom-input"/>
        </el-form-item>
        <el-form-item label="审核人" prop="people">
          <el-select v-model="form.people" placeholder="请输入审核人">
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
          <el-select v-model="form.status" placeholder="请选择">
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
import { listVerify, getVerify, delVerify, addVerify, updateVerify, listCard } from "@/api/information/verify";
import {listUser} from "@/api/information/scenic1";

export default {
  name: "Verify",
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
      // 审核管理列表格数据
      verifyList: [],
      // 用户数据
      userList: [],
      // 内容发布引导牌管理表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //审核弹出层
      audit: false,
      dialogVisible: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        type: null,
        content: null,
        publisher: null,
        people: null,
        status: null,
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
    this.getCardList();
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
    /** 查询内容发布引导牌管理列表 */
    getCardList() {
      this.loading = true;
      listCard(this.queryParams).then(response => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询审核管理列列表 */
    getList() {
      this.loading = true;
      listVerify(this.queryParams).then(response => {
        this.verifyList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.audit = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        type: null,
        content: null,
        publisher: null,
        people: null,
        status: null,
        createTime: null
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
      this.title = "添加审核管理列";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getVerify(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改审核管理列";
      });
    },
    /** 审核按钮操作 */
    handleAudit(row) {
      this.reset();
      const id = row.id || this.ids
      getVerify(id).then(response => {
        this.form = response.data;
        this.form.verifyDate = this.getNowTime();
        this.audit = true;
        this.title = "审核内容管理列";
      });
    },
    showTable() {
      this.dialogVisible = true;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateVerify(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.audit = false;
              this.getList();
            });
          } else {
            addVerify(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
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
      this.$modal.confirm('是否确认删除审核管理列编号为"' + ids + '"的数据项？').then(function() {
        return delVerify(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('information/verify/export', {
        ...this.queryParams
      }, `verify_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.custom-input .el-input__inner {
  background-color: #f0f0f0 !important;
}
</style>

