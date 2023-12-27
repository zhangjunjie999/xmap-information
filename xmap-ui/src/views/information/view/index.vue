<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="站点名" prop="siteName">
        <el-input
          v-model="queryParams.siteName"
          placeholder="请输入景点名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核人" prop="verifyPeople">
        <el-input
          v-model="queryParams.verifyPeople"
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
          v-hasPermi="['information:view:add']"
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
          v-hasPermi="['information:view:edit']"
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
          v-hasPermi="['information:view:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['information:view:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="viewList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="站点名" align="center" prop="siteName" show-overflow-tooltip="true"/>
      <el-table-column label="通知公告" align="center" prop="notice" show-overflow-tooltip="true"/>
<!--      <el-table-column label="天气" align="center" prop="weather" show-overflow-tooltip="true"/>-->
      <el-table-column label="在园人数" align="center" prop="attendance" show-overflow-tooltip="true"/>
      <el-table-column label="剩余停车位" align="center" prop="parking" show-overflow-tooltip="true" width="90"/>
      <el-table-column label="票价" align="center" prop="ticket" show-overflow-tooltip="true"/>
      <el-table-column label="建议景点" align="center" prop="nextScenic" show-overflow-tooltip="true"/>
      <el-table-column label="景点人数" align="center" prop="numberScenic" show-overflow-tooltip="true"/>
      <el-table-column label="附近景点" align="center" prop="nearbyScenic" show-overflow-tooltip="true"/>
      <el-table-column label="救援电话" align="center" prop="emergencyPhone" show-overflow-tooltip="true"/>
      <el-table-column label="审核状态" align="center" prop="verifyStatus" show-overflow-tooltip="true">
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
      <el-table-column label="发布人" align="center" prop="publisher" show-overflow-tooltip="true"/>
      <el-table-column label="发布时间" align="center" prop="publishTime" show-overflow-tooltip="true">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.publishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding " width="180">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['information:view:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['information:view:remove']"
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

    <!-- 添加或修改景点引导牌对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景点名" prop="siteName">
          <el-select v-model="form.siteName" placeholder="请选择景点标志">
            <el-option
              v-for="site in siteList"
              :key="site.id"
              :label="site.sign"
              :value="site.sign">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="通知公告" prop="notice">
          <el-input v-model="form.notice" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="在园人数" prop="attendance">
          <el-input v-model="form.attendance" placeholder="请输入在园人数" @input="validateInput('attendance')"/>
        </el-form-item>
        <el-form-item label="剩余停车位" prop="parking" style="white-space: nowrap">
          <el-input v-model="form.parking" placeholder="请输入剩余停车位" @input="validateInput('parking')"/>
        </el-form-item>
        <el-form-item label="票价" prop="ticket">
          <el-input v-model="form.ticket" placeholder="请输入票价" @input="validateInput('ticket')"/>
        </el-form-item>
        <el-form-item label="建议景点" prop="nextScenic">
          <el-select v-model="form.nextScenic" placeholder="请选择下一个景点">
            <el-option
              v-for="site in siteList"
              :key="site.id"
              :label="site.sign"
              :value="site.sign">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="景点人数" prop="numberScenic">
          <el-input v-model="form.numberScenic" placeholder="请输入景点人数" @input="validateInput('numberScenic')"/>
        </el-form-item>
        <el-form-item label="附近景点" prop="nearbyScenic">
          <el-input v-model="form.nearbyScenic" placeholder="请输入附近景点" />
        </el-form-item>
        <el-form-item label="救援电话" prop="emergencyPhone">
          <el-input v-model="form.emergencyPhone" placeholder="请输入救援电话" @input="validateInput('emergencyPhone')"/>
        </el-form-item>
        <el-form-item label="发布人" prop="publisher">
          <el-input v-model="form.publisher" placeholder="请输入发布人" />
        </el-form-item>
        <el-form-item label="发布时间" prop="publishTime">
          <el-date-picker
            clearable
            :disabled="single"
            v-model="form.publishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择发布时间">
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
import { listView, getView, delView, addView, updateView, listSite, getUserProfile} from "@/api/information/view";

export default {
  name: "View",
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
      viewList: [],
      // 地点标志数据
      siteList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
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
      options: [],
      value:''
    };
  },
  created() {
    this.getList();
    this.listSiteAll();
    this.getUser();
    this.getNowTime()
  },
  methods: {
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
    /** 获取用户数据 */
    getUser() {
      getUserProfile().then(response => {
        this.user = response.data;
        this.roleGroup = response.roleGroup;
        this.postGroup = response.postGroup;
      });
    },
    /** 限制输入框数字限制*/
    validateInput(fieldName) {
      // 使用正则表达式检查输入是否为数字
      this.form[fieldName] = this.form[fieldName].replace(/[^0-9]/g, '');
    },
    /** 查询地点标志列表 */
    listSiteAll() {
      this.loading = true;
      listSite(this.queryParams).then(response => {
        this.siteList = JSON.parse(JSON.stringify(response.rows));
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询景点引导牌列表 */
    getList() {
      this.loading = true;
      listView(this.queryParams).then(response => {
        this.viewList = response.rows;
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
        verifyStatus: '待审核',
        verifyPeople: null,
        verifyDate: null,
        publisher: this.user.userName,
        publishTime: this.getNowTime()
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
      getView(id).then(response => {
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
            updateView(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addView(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除景点引导牌编号为"' + ids + '"的数据项？').then(function() {
        return delView(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('information/view/export', {
        ...this.queryParams
      }, `view_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
