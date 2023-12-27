<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="taxiName">
        <el-input
          v-model="queryParams.taxiName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签" prop="taxiTag">
        <el-input
          v-model="queryParams.taxiTag"
          placeholder="请输入标签"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="taxiType">
        <el-input
          v-model="queryParams.taxiType"
          placeholder="请输入类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库存数量" prop="taxiCount">
        <el-input
          v-model="queryParams.taxiCount"
          placeholder="请输入库存数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="截止日期" prop="enddate">
        <el-date-picker clearable
          v-model="queryParams.enddate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择截止日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="起始价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入起始价格"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用车须知" prop="instruction">
        <el-input
          v-model="queryParams.instruction"
          placeholder="请输入用车须知"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="通知设置" prop="messageSet">
        <el-input
          v-model="queryParams.messageSet"
          placeholder="请输入通知设置"
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
          v-hasPermi="['commonservice:taxi:add']"
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
          v-hasPermi="['commonservice:taxi:edit']"
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
          v-hasPermi="['commonservice:taxi:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commonservice:taxi:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taxiList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="出租车ID" align="center" prop="taxiId" />
      <el-table-column label="名称" align="center" prop="taxiName"  :show-overflow-tooltip='true'/>
      <el-table-column label="标签" align="center" prop="taxiTag"  :show-overflow-tooltip='true'/>
      <el-table-column label="产品描述" align="center" prop="taxiDescription"  :show-overflow-tooltip='true'/>
      <el-table-column label="样车图片" align="center" prop="taxiPhoto" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.taxiPhoto" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="类型" align="center" prop="taxiType"  :show-overflow-tooltip='true'/>
      <el-table-column label="库存数量" align="center" prop="taxiCount" />
      <el-table-column label="截止日期" align="center" prop="enddate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.enddate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="起始价格" align="center" prop="price"  :show-overflow-tooltip='true'/>
      <el-table-column label="用车须知" align="center" prop="instruction"  :show-overflow-tooltip='true'/>
      <el-table-column label="通知设置" align="center" prop="messageSet" />
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
            v-hasPermi="['commonservice:taxi:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commonservice:taxi:remove']"
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

    <!-- 添加或修改出租车信息管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="taxiName">
          <el-input v-model="form.taxiName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="标签" prop="taxiTag">
          <el-input v-model="form.taxiTag" placeholder="请输入标签" />
        </el-form-item>
        <el-form-item label="产品描述" prop="taxiDescription">
          <el-input v-model="form.taxiDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="样车图片" prop="taxiPhoto">
          <image-upload v-model="form.taxiPhoto"/>
        </el-form-item>
        <el-form-item label="类型" prop="taxiType">
          <el-input v-model="form.taxiType" placeholder="请输入类型" />
        </el-form-item>
        <el-form-item label="库存数量" prop="taxiCount">
          <el-input v-model="form.taxiCount" placeholder="请输入库存数量" />
        </el-form-item>
        <el-form-item label="截止日期" prop="enddate">
          <el-date-picker clearable
            v-model="form.enddate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择截止日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="起始价格" prop="price">
          <el-input v-model="form.price" placeholder="请输入起始价格" />
        </el-form-item>
        <el-form-item label="用车须知" prop="instruction">
          <el-input v-model="form.instruction" placeholder="请输入用车须知" />
        </el-form-item>
        <el-form-item label="通知设置" prop="messageSet">
          <el-input v-model="form.messageSet" placeholder="请输入通知设置" />
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
import { listTaxi, getTaxi, delTaxi, addTaxi, updateTaxi } from "@/api/commonservice/taxi";

export default {
  name: "Taxi",
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
      // 出租车信息管理表格数据
      taxiList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taxiName: null,
        taxiTag: null,
        taxiDescription: null,
        taxiPhoto: null,
        taxiType: null,
        taxiCount: null,
        enddate: null,
        price: null,
        instruction: null,
        messageSet: null,
        createDate: null,
        updateDate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        taxiName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        taxiDescription: [
          { required: true, message: "产品描述不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询出租车信息管理列表 */
    getList() {
      this.loading = true;
      listTaxi(this.queryParams).then(response => {
        this.taxiList = response.rows;
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
        taxiId: null,
        taxiName: null,
        taxiTag: null,
        taxiDescription: null,
        taxiPhoto: null,
        taxiType: null,
        taxiCount: null,
        enddate: null,
        price: null,
        instruction: null,
        messageSet: null,
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
      this.ids = selection.map(item => item.taxiId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加出租车信息管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const taxiId = row.taxiId || this.ids
      getTaxi(taxiId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改出租车信息管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taxiId != null) {
            updateTaxi(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTaxi(this.form).then(response => {
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
      const taxiIds = row.taxiId || this.ids;
      this.$modal.confirm('是否确认删除出租车信息管理编号为"' + taxiIds + '"的数据项？').then(function() {
        return delTaxi(taxiIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commonservice/taxi/export', {
        ...this.queryParams
      }, `taxi_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
