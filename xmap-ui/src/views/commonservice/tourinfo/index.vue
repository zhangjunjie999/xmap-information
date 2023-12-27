<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="景区名称" prop="scenicAreaName">
        <el-input
          v-model="queryParams.scenicAreaName"
          placeholder="请输入景区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="介绍" prop="description" >
        <el-input
          v-model="queryParams.description"
          placeholder="请输入介绍"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开放时间" prop="openingHours">
        <el-input
          v-model="queryParams.openingHours"
          placeholder="请输入开放时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门票价格" prop="ticketPrice">
        <el-input
          v-model="queryParams.ticketPrice"
          placeholder="请输入门票价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="地点位置" prop="locationAddress">
        <el-input
          v-model="queryParams.locationAddress"
          placeholder="请输入地点位置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交通方式" prop="scenicTransportation">
        <el-input
          v-model="queryParams.scenicTransportation"
          placeholder="请输入交通方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态日期" prop="statusDate">
        <el-date-picker clearable
          v-model="queryParams.statusDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择状态日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="天气状况" prop="weatherCondition">
        <el-input
          v-model="queryParams.weatherCondition"
          placeholder="请输入天气状况"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="游客人数" prop="visitorCount">
        <el-input
          v-model="queryParams.visitorCount"
          placeholder="请输入游客人数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="饱和数量" prop="crowdCapacity">
        <el-input
          v-model="queryParams.crowdCapacity"
          placeholder="请输入饱和数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['commonservice:tourinfo:add']"
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
          v-hasPermi="['commonservice:tourinfo:edit']"
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
          v-hasPermi="['commonservice:tourinfo:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:tourinfo:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tourinfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="景区ID" align="center" prop="scenicAreaId" />
      <el-table-column label="景区名称" align="center" prop="scenicAreaName" :show-overflow-tooltip='true'/>
      <el-table-column label="介绍" align="center" prop="description" :show-overflow-tooltip='true'/>
 

     



      <el-table-column label="开放时间" align="center" prop="openingHours" :show-overflow-tooltip='true'/>
      <el-table-column label="门票价格" align="center" prop="ticketPrice" />
      <el-table-column label="地点位置" align="center" prop="locationAddress" :show-overflow-tooltip='true'/>
      <el-table-column label="交通方式" align="center" prop="scenicTransportation" :show-overflow-tooltip='true'/>
      <el-table-column label="状态日期" align="center" prop="statusDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.statusDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="天气状况" align="center" prop="weatherCondition" :show-overflow-tooltip='true'/>
      <el-table-column label="游客人数" align="center" prop="visitorCount" />
      <el-table-column label="饱和数量" align="center" prop="crowdCapacity" />
      <el-table-column label="电话" align="center" prop="phoneNumber" :show-overflow-tooltip='true'/>
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
            v-hasPermi="['commonservice:tourinfo:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:tourinfo:remove']"
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

    <!-- 添加或修改景点信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="景区名称" prop="scenicAreaName">
          <el-input v-model="form.scenicAreaName" placeholder="请输入景区名称" />
        </el-form-item>
        <el-form-item label="介绍" prop="description">
          <el-input v-model="form.description" placeholder="请输入介绍" />
        </el-form-item>
        <el-form-item label="开放时间" prop="openingHours">
          <el-input v-model="form.openingHours" placeholder="请输入开放时间" />
        </el-form-item>
        <el-form-item label="门票价格" prop="ticketPrice">
          <el-input v-model="form.ticketPrice" placeholder="请输入门票价格" />
        </el-form-item>
        <el-form-item label="地点位置" prop="locationAddress">
          <el-input v-model="form.locationAddress" placeholder="请输入地点位置" />
        </el-form-item>
        <el-form-item label="交通方式" prop="scenicTransportation">
          <el-input v-model="form.scenicTransportation" placeholder="请输入交通方式" />
        </el-form-item>
        <el-form-item label="状态日期" prop="statusDate">
          <el-date-picker clearable
            v-model="form.statusDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择状态日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="天气状况" prop="weatherCondition">
          <el-input v-model="form.weatherCondition" placeholder="请输入天气状况" />
        </el-form-item>
        <el-form-item label="游客人数" prop="visitorCount">
          <el-input v-model="form.visitorCount" placeholder="请输入游客人数" />
        </el-form-item>
        <el-form-item label="饱和数量" prop="crowdCapacity">
          <el-input v-model="form.crowdCapacity" placeholder="请输入饱和数量" />
        </el-form-item>
        <el-form-item label="电话" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话" />
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
import { listTourinfo, getTourinfo, delTourinfo, addTourinfo, updateTourinfo } from "@/api/commonservice/tourinfo";

export default {
  name: "Tourinfo",
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
      // 景点信息管理表格数据
      tourinfoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        scenicAreaName: null,
        description: null,
        openingHours: null,
        ticketPrice: null,
        locationAddress: null,
        scenicTransportation: null,
        statusDate: null,
        weatherCondition: null,
        visitorCount: null,
        crowdCapacity: null,
        phoneNumber: null,
        createDate: null,
        updateDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        scenicAreaName: [
          { required: true, message: "景区名称不能为空", trigger: "blur" }
        ],
        openingHours: [
          { required: true, message: "开放时间不能为空", trigger: "blur" }
        ],
        ticketPrice: [
          { required: true, message: "门票价格不能为空", trigger: "blur" }
        ],
        weatherCondition: [
          { required: true, message: "天气状况不能为空", trigger: "blur" }
        ],
        visitorCount: [
          { required: true, message: "游客人数不能为空", trigger: "blur" }
        ],
        crowdCapacity: [
          { required: true, message: "饱和数量不能为空", trigger: "blur" }
        ],
        phoneNumber: [
          { required: true, message: "电话不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询景点信息管理列表 */
    getList() {
      this.loading = true;
      listTourinfo(this.queryParams).then(response => {
        this.tourinfoList = response.rows;
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
        scenicAreaName: null,
        description: null,
        openingHours: null,
        ticketPrice: null,
        locationAddress: null,
        scenicTransportation: null,
        statusDate: null,
        weatherCondition: null,
        visitorCount: null,
        crowdCapacity: null,
        phoneNumber: null,
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
      this.ids = selection.map(item => item.scenicAreaId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加景点信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scenicAreaId = row.scenicAreaId || this.ids
      getTourinfo(scenicAreaId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改景点信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scenicAreaId != null) {
            updateTourinfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTourinfo(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除景点信息管理编号为"' + scenicAreaIds + '"的数据项？').then(function() {
        return delTourinfo(scenicAreaIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/tourinfo/export', {
        ...this.queryParams
      }, `tourinfo_${new Date().getTime()}.xlsx`)
    },

    showTips(obj, row){
      /*obj为鼠标移入时的事件对象*/

      /*currentWidth 为文本在页面中所占的宽度，创建标签，加入到页面，获取currentWidth ,最后在移除*/
      let TemporaryTag = document.createElement('span');
      TemporaryTag.innerText = row.note;
      TemporaryTag.className = 'getTextWidth';
      document.querySelector('body').appendChild(TemporaryTag);
      let currentWidth = document.querySelector('.getTextWidth').offsetWidth;
      document.querySelector('.getTextWidth').remove();

      /*cellWidth为表格容器的宽度*/
      const cellWidth = obj.target.offsetWidth

      /*当文本宽度小于||等于容器宽度两倍时，代表文本显示未超过两行*/
      currentWidth <= (2*cellWidth) ? row.showTooltip = false : row.showTooltip = true
}

  }
};
</script>


<style lang="scss" scoped>
.myNote{
  display:-webkit-box;
  text-overflow:ellipsis;
  overflow:hidden;
  -webkit-line-clamp: 2;
  -webkit-box-orient:vertical;
}

</style>
