<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="站点名" prop="siteName">
        <el-input
          v-model="queryParams.siteName"
          placeholder="请输入站点名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="位置" prop="location">
        <el-input
          v-model="queryParams.location"
          placeholder="请输入位置"
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
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['information:parking:add']"-->
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
<!--          v-hasPermi="['information:parking:edit']"-->
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
<!--          v-hasPermi="['information:parking:remove']"-->
<!--        >删除</el-button>-->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['information:parking:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
  <div style="overflow-x: auto;max-width: 100%;">
    <el-table v-loading="loading" :data="filteredTableData" @selection-change="handleSelectionChange" >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="站点名" align="center" prop="siteName" show-overflow-tooltip="true"/>
      <el-table-column label="通知公告" align="center" prop="notice" show-overflow-tooltip="true"/>
      <!-- <el-table-column label="天气" align="center" prop="weather" show-overflow-tooltip="true"/> -->
      <el-table-column label="图标" align="center" prop="icon" width="100" show-overflow-tooltip="true">
        <template slot-scope="scope">
          <image-preview :src="scope.row.icon" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="距离" align="center" prop="distance" show-overflow-tooltip="true"/>
      <el-table-column label="位置" align="center" prop="location" show-overflow-tooltip="true"/>
      <el-table-column label="剩余车位" align="center" prop="availablePark" show-overflow-tooltip="true"/>
      <el-table-column label="已用车位" align="center" prop="occupiedPark" show-overflow-tooltip="true"/>
      <el-table-column label="周边停车场" align="center" prop="nearbyPark" show-overflow-tooltip="true" width="100"/>
      <!-- <el-table-column label="空余位数" align="center" prop="availableSpace" show-overflow-tooltip="true"/> -->
      <el-table-column label="救援电话" align="center" prop="emergencyPhone" show-overflow-tooltip="true"/>
      <el-table-column label="操作" align="center" class-name="small-padding" width="100">
        <template slot-scope="scope">
          <el-button
            type="text"
            size="small"
            icon="el-icon-view"
            @click="openNewPage(scope.row)"
          >预览</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['information:parking:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改停车场引导牌管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="站点名" prop="siteName">
          <el-input v-model="form.siteName" placeholder="请输入站点名" />
        </el-form-item>
        <el-form-item label="通知公告" prop="notice">
          <el-input v-model="form.notice" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <!-- <el-form-item label="天气" prop="weather">
          <el-input v-model="form.weather" placeholder="请输入天气" />
        </el-form-item> -->
        <el-form-item label="图标" prop="icon">
          <image-upload v-model="form.icon"/>
        </el-form-item>
        <el-form-item label="距离" prop="distance">
          <el-input v-model="form.distance" placeholder="请输入距离" />
        </el-form-item>
        <el-form-item label="位置" prop="location">
          <el-input v-model="form.location" placeholder="请输入位置" />
        </el-form-item>
        <el-form-item label="剩余车位" prop="availablePark">
          <el-input v-model="form.availablePark" placeholder="请输入剩余车位" />
        </el-form-item>
        <el-form-item label="已用车位" prop="occupiedPark">
          <el-input v-model="form.occupiedPark" placeholder="请输入已用车位" />
        </el-form-item>
        <el-form-item label="周边停车场" prop="nearbyPark">
          <el-input v-model="form.nearbyPark" placeholder="请输入周边停车场" type="textarea" :row="number">
          </el-input>
        </el-form-item>
        <!-- <el-form-item label="空余位数" prop="availableSpace">
          <el-input v-model="form.availableSpace" placeholder="请输入空余位数" />
        </el-form-item> -->
        <el-form-item label="救援电话" prop="emergencyPhone">
          <el-input v-model="form.emergencyPhone" placeholder="请输入救援电话" />
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
import { listParking, getParking, delParking, addParking, updateParking } from "@/api/information/parking";

export default {
  name: "Parking",
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
      // 停车场引导牌管理表格数据
      parkingList: [],
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
        icon: null,
        distance: null,
        location: null,
        availablePark: null,
        occupiedPark: null,
        nearbyPark: null,
        availableSpace: null,
        emergencyPhone: null,
        verifyStatus: null,
      },
      preview: {
        open: false,
        title: "引导牌预览",
        data: {},
        activeName: "引导牌"
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      //周边停车场文本输入框的高度
      number: 3,
    };
  },
  created() {
    this.getList();
  },
  computed:{
    filteredTableData() {
      // debugger; // 添加这行
      const filteredData = this.parkingList.filter(item => item.verifyStatus === '审核通过');
      console.log('Filtered Data:', filteredData);
      return filteredData;
    },
  },
  methods: {
       //打开新的浏览器窗口
       openNewPage(row) {
   if(row.siteName =='游客服务中心2号停车场'){
    window.open('http://localhost/information/parktourist2', '_blank');
     }else if(row.siteName =='游客服务中心3号停车场(上)' ){
      window.open('http://localhost/information/parktourist3up', '_blank');
   }else if(row.siteName =='游客服务中心3号停车场(下)' ){
      window.open('http://localhost/information/parktourist3down', '_blank');
   }else if(row.siteName =='杜鹃广场停车场' ){
      window.open('http://localhost/information/parkdujuan', '_blank');
   }else if(row.siteName =='红歌广场停车场' ){
      window.open('http://localhost/information/parkhongge', '_blank');
   }else if(row.siteName =='会展中心停车场' ){
      window.open('http://localhost/information/parkhuizhan', '_blank');
   }
   else {
    console.log('抱歉，暂时无法预览');
    window.alert('抱歉，暂时无法预览');
   }
      
      //404
    },
    /** 查询停车场引导牌管理列表 */
    getList() {
      this.loading = true;
      listParking(this.queryParams).then(response => {
        this.parkingList = response.rows;
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
        icon: null,
        distance: null,
        location: null,
        availablePark: null,
        occupiedPark: null,
        nearbyPark: null,
        availableSpace: null,
        emergencyPhone: null,
        verifyStatus: null,
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
      this.title = "添加停车场引导牌管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getParking(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改停车场引导牌管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateParking(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addParking(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除停车场引导牌管理编号为"' + ids + '"的数据项？').then(function() {
        return delParking(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('information/parking/export', {
        ...this.queryParams
      }, `parking_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
