<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="名称" prop="historyName">
        <el-input
          v-model="queryParams.historyName"
          placeholder="请输入名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="IP地址" prop="historyIp">
        <el-input
          v-model="queryParams.historyIp"
          placeholder="请输入IP地址"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="端口" prop="historyPort">
        <el-input
          v-model="queryParams.historyPort"
          placeholder="请输入端口"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接口" prop="historyApi">
        <el-input
          v-model="queryParams.historyApi"
          placeholder="请输入接口"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createDate">
        <el-input
          v-model="queryParams.createDate"
          placeholder="请输入创建时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="historyStatus">
        <el-input
          v-model="queryParams.historyStatus"
          placeholder="请输入状态"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="回复" prop="historyResponse">
        <el-input
          v-model="queryParams.historyResponse"
          placeholder="请输入回复"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字段类型" prop="fieldType">
        <el-input
          v-model="queryParams.fieldType"
          placeholder="请输入字段类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="字段值" prop="fieldValues">
        <el-input
          v-model="queryParams.fieldValues"
          placeholder="请输入字段值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作类型" prop="operatorType">
        <el-input
          v-model="queryParams.operatorType"
          placeholder="请输入操作类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据内容" prop="businessData">
        <el-input
          v-model="queryParams.businessData"
          placeholder="请输入数据内容"
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
          v-hasPermi="['screen:history:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <!-- <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['screen:history:edit']"
        >修改</el-button> -->
      </el-col>
      <el-col :span="1.5">
        <!-- <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['screen:history:remove']"
        >删除</el-button> -->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['screen:history:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="historyList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="historyId" :show-overflow-tooltip="true"/>
      <el-table-column label="名称" align="center" prop="historyName" :show-overflow-tooltip="true"/>
      <el-table-column label="IP地址" align="center" prop="historyIp" :show-overflow-tooltip="true"/>
      <el-table-column label="端口" align="center" prop="historyPort" :show-overflow-tooltip="true"/>
      <el-table-column label="接口" align="center" prop="historyApi" :show-overflow-tooltip="true"/>
      <el-table-column label="创建时间" align="center" prop="createDate" :show-overflow-tooltip="true"/>
      <el-table-column label="创建人" align="center" prop="createBy" :show-overflow-tooltip="true"/>
      <el-table-column label="状态" align="center" prop="historyStatus" :show-overflow-tooltip="true"/>
      <el-table-column label="回复" align="center" prop="historyResponse" :show-overflow-tooltip="true"/>
      <el-table-column label="字段类型" align="center" prop="fieldType" :show-overflow-tooltip="true"/>
      <el-table-column label="字段值" align="center" prop="fieldValues" :show-overflow-tooltip="true"/>
      <el-table-column label="操作类型" align="center" prop="operatorType" :show-overflow-tooltip="true"/>
      <el-table-column label="数据内容" align="center" prop="businessData" :show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['screen:history:edit']"
          >修改</el-button> -->
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['screen:history:remove']"
          >删除</el-button> -->
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

    <!-- 添加或修改设备对接历史对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="名称" prop="historyName">
          <el-input v-model="form.historyName" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="IP地址" prop="historyIp">
          <el-input v-model="form.historyIp" placeholder="示例：http://localhost" />
        </el-form-item>
        <el-form-item label="端口" prop="historyPort">
          <el-input v-model="form.historyPort" placeholder="示例：5000" />
        </el-form-item>
        <el-form-item label="接口" prop="historyApi">
          <el-input v-model="form.historyApi" placeholder="示例：/v1/material/refreshContent" />
        </el-form-item>
        <!-- <el-form-item label="创建时间" prop="createDate">
          <el-input v-model="form.createDate" placeholder="请输入创建时间" />
        </el-form-item> -->
        <!-- <el-form-item label="状态" prop="historyStatus">
          <el-input v-model="form.historyStatus" placeholder="请输入状态" />
        </el-form-item> -->
        <!-- <el-form-item label="回复" prop="historyResponse">
          <el-input v-model="form.historyResponse" placeholder="请输入回复" />
        </el-form-item> -->
        <el-form-item label="字段类型" prop="fieldType">
          <el-input v-model="form.fieldType" placeholder="示例：idType" />
        </el-form-item>
        <el-form-item label="字段值" prop="fieldValues">
          <el-input v-model="form.fieldValues" placeholder="示例： 101" />
        </el-form-item>
        <el-form-item label="操作类型" prop="operatorType">
          <el-input v-model="form.operatorType" placeholder="默认为 modifyAll" />
        </el-form-item>
        <el-form-item label="素材字段类型" prop="materialUniqueFieldType">
          <el-input v-model="form.materialUniqueFieldType" placeholder="示例：idType" />
        </el-form-item>
        <el-form-item label="素材字段值" prop="materialUniqueFieldValue">
          <el-input v-model="form.materialUniqueFieldValue" placeholder="示例： 12" />
        </el-form-item>
        <el-form-item label="业务类型" prop="resourceType">
          <el-input v-model="form.resourceType" placeholder="示例：web" />
        </el-form-item>
        <el-form-item label="播放时间" prop="playTime">
          <el-input v-model="form.playTime" placeholder="单位为秒，示例：20" />
        </el-form-item>
        <el-form-item label="业务数值" prop="resourceValue">
          <el-input v-model="form.resourceValue" placeholder="示例：http://192.68.33.100:8080/information/parking/parkhuizhan" />
        </el-form-item>
        <el-form-item label="业务编号" prop="materialNo">
          <el-input v-model="form.materialNo" placeholder="示例：1" />
        </el-form-item>
        <!-- <el-form-item label="数据内容" prop="businessData">
          <el-input v-model="form.businessData" placeholder="请输入数据内容" />
        </el-form-item> -->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listHistory, getHistory, delHistory, addHistory, updateHistory } from "@/api/screen/history";

export default {
  name: "History",
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
      // 设备对接历史表格数据
      historyList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        historyName: null,
        historyIp: null,
        historyPort: null,
        historyApi: null,
        createDate: null,
        createBy: null,
        historyStatus: null,
        historyResponse: null,
        fieldType: null,
        fieldValues: null,
        operatorType: null,
        businessData: null
      },
      //bussinessData  json
      bDJson:{
        materialUniqueFieldType: null,
        materialUniqueFieldValue: null,
        resourceType: null,
        playTime: null,
        resourceValue: null,
        materialNo: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        historyName: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        historyIp: [
          { required: true, message: "IP地址不能为空", trigger: "blur" }
        ],
        historyPort: [
          { required: true, message: "端口不能为空", trigger: "blur" }
        ],
        historyApi: [
          { required: true, message: "接口不能为空", trigger: "blur" }
        ],
        materialUniqueFieldType: [
          { required: true, message: "素材字段类型不能为空", trigger: "blur" }
        ],
        materialUniqueFieldValue: [
          { required: true, message: "素材字段值不能为空", trigger: "blur" }
        ],
        resourceValue: [
          { required: true, message: "业务数值不能为空", trigger: "blur" }
        ],
        resourceType: [
          { required: true, message: "业务类型不能为空", trigger: "blur" }
        ],
        
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询设备对接历史列表 */
    getList() {
      this.loading = true;
      listHistory(this.queryParams).then(response => {
        this.historyList = response.rows;
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
        historyId: null,
        historyName: null,
        historyIp: null,
        historyPort: null,
        historyApi: null,
        createDate: null,
        createBy: null,
        historyStatus: null,
        historyResponse: null,
        fieldType: null,
        fieldValues: null,
        operatorType: null,
        businessData: null,
        //businessData part
        materialUniqueFieldType: null,
        materialUniqueFieldValue: null,
        resourceType: null,
        playTime: null,
        resourceValue: null,
        materialNo: null,

      };
      this.bDJson = {
        materialUniqueFieldType: null,
        materialUniqueFieldValue: null,
        resourceType: null,
        playTime: null,
        resourceValue: null,
        materialNo: null,
      }
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
      this.ids = selection.map(item => item.historyId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加设备对接历史";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const historyId = row.historyId || this.ids
      getHistory(historyId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改设备对接历史";
      });
    },
    /** 提交按钮 */
    async submitForm() {
          this.addAttributeToForm();
         await  this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.historyId != null) {
          //可能，不需要修改功能          
            updateHistory(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHistory(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    addAttributeToForm(){
       //将属性值写入json对象
       console.log("this.form: ");
                   console.log(this.form);      
       this.bDJson.materialUniqueFieldType = this.form.materialUniqueFieldType;
           this.bDJson.materialUniqueFieldValue = this.form.materialUniqueFieldValue;
           this.bDJson.resourceType = this.form.resourceType;
           this.bDJson.resourceValue = this.form.resourceValue;
           this.bDJson.playTime = this.form.playTime;
           this.bDJson.materialNo = this.form.materialNo;
           this.form.businessData=JSON.stringify(this.bDJson);
           console.log("JSON.stringify(this.bDJson): ");
        
           console.log(JSON.stringify(this.bDJson));
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const historyIds = row.historyId || this.ids;
      this.$modal.confirm('是否确认删除设备对接历史编号为"' + historyIds + '"的数据项？').then(function() {
        return delHistory(historyIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('screen/history/export', {
        ...this.queryParams
      }, `history_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
