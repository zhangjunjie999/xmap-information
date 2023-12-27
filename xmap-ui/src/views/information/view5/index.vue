


<template >
  <div class="box" ref="myImg">

    <!-- 顶部 -->
    <div class="body-top">
      <div class="station-name">景点名称： {{ stationName }}</div>
      <!--我这个需求是有消息时才会让这个功能显示，所以使用了v-if-->
      <div class="bs-sysMsg" v-if="systemMsg.length > 0">
        <i class="el-alert__icon el-icon-warning"></i>
        <div class="msg__content">
          <el-carousel height="20px" direction="horizontal" indicator-position="none" :autoplay="true">
            <el-carousel-item v-for="item in systemMsg" :key="item.id">
              <a href="javascript:void(0)" class="item">{{ item.title }}</a>
            </el-carousel-item>
          </el-carousel>
        </div><br>
      </div>
    </div>

    <!-- 主体 -->
    <div class="body-main">
      <div class="weather-info">
        <div class="time"><span> {{formattedDateTime}} </span></div>
        <div class="weather"><span> {{ weather }} {{ temperature }} </span></div>

      </div>

      <div class="park-info">
        <!-- part1 -->
        <div class="park-info-p1">
          <span class="park-left">
<!--            <div class="park-icon">-->
<!--&lt;!&ndash;              <img class="img" src="./image/rightturn.png" />&ndash;&gt;-->
<!--            </div>-->

            <div class="restNumber"> 在园人数 ：
            <span class="restNumberColor">
              {{ restNumber }}
            </span>
            人
          </div>
          <div class="usedNumber"> 剩余停车位 ：
            <span class="usedNumberColor">
              {{ usedNumber }}
            </span>
            个
          </div>

            <div class="restNumber"> 票价 ：
            <span class="restNumberColor">
              {{ tickets }}
            </span>
            元/人
          </div>



          </span>
          <span class="park-right">




          </span>
        </div>
        <!-- part2 -->
        <div class="park-info-p2">
<!--          <div class="restNumber"> 剩余车位 ：-->
<!--            <span class="restNumberColor">-->
<!--              {{ restNumber }}-->
<!--            </span>-->
<!--            个-->
<!--          </div>-->
<!--          <div class="usedNumber"> 已用车位 ：-->
<!--            <span class="usedNumberColor">-->
<!--              {{ usedNumber }}-->
<!--            </span>-->
<!--            个-->
<!--          </div>-->
          <!-- <span>建议停车场</span> <span>空余车位</span> -->
        </div>
        <!-- part3 -->
        <div class="park-info-p3">
          <el-table :data="parkList"
                    :cell-style="{ fontWeight: 'bold', fontSize: '100%', background: 'white', borderBottom: '1px solid white' }"
                    :header-cell-style="{ color: 'black', fontWeight: 'bold', fontSize: '100%', background: 'white', borderBottom: '3px solid #5B9BD8', borderColor: '#5B9BD8', border: '3px solid #5B9BD8 none none none' }"
                    style="border-Bottom:1px solid white">

            <el-table-column label="建议下一个景点" align="center" prop="nextScenic" style="border-bottom:1px solid red">
              <template slot-scope="scope">
                <span style="color: black ;font-Weight: bold ;">{{ scope.row.name }}</span>
              </template>
            </el-table-column>


            <el-table-column label="景点人数" align="center" prop="numberScenic">
              <template slot-scope="scope">
                <span style="color: red; font-Size:100%;">{{ scope.row.value }}</span>
              </template>
            </el-table-column>

          </el-table>

        </div>

        <div class="park-info-p2">
          <span class="park-left">
<!--            <div class="park-icon">-->
            <!--&lt;!&ndash;              <img class="img" src="./image/rightturn.png" />&ndash;&gt;-->
            <!--            </div>-->

            <div class="restNumber"> 在园人数 ：
            <span class="restNumberColor">
              {{ restNumber }}
            </span>
            人
          </div>
          </span>
        </div>
      </div>
    </div>
    <!-- 底部 -->
    <div class="body-down">
      <div class="phone-info">
        <div class="phone-info-h">救援电话：{{ phone }}</div>
        <i class="el-icon-data-line" @click="toggleFullScreen"></i>

      </div>

    </div>

  </div>
</template>
<script>
import { listScenic, weatherParking ,getWeather } from "@/api/information/scenic";
import screenfull from "screenfull";
import axios from "axios";
export default {
  name: 'Home',
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
      scenicList: [],
      //星期
      weekDay: '',
      //杜鹃广场停车场剩余车位数
      numberDujuan: '',
      //红歌广场停车场剩余车位数
      numberHongge: '',
      //会展中心停车场剩余车位数
      numberHuizhan: '',
      //游客服务中心3号停车场剩余车位数
      numberTourist3: '',
      //游客服务中心2号停车场剩余车位数
      numberTourist2: '',

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
        weekDay: null
      },
      // 从数据库中获取的天气数据
      weatherList: [],
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        weatherId: null,
        weatherInfo: null,
        weatherHigh: null,
        weatherLow: null,
        weatherTime: null,
      },
      // 滚屏消息
      systemMsg: [
        { id: 1, title: '公告：井冈山市旅游局欢迎您' },

      ],
      formattedDateTime: '',
      newTime: "",
      //天气
      weather: ' ',
      //温度
      temperature: '',
      //风向
      winddirection: '',
      //风力
      windpower: '',
      //湿度
      humidity: '',
      //报告时间
      reporttime: '',

      tickets: '',
      //电话
      phone: '0796-1192634',
      //方向
      turn: '右前方',
      //距离
      distance: '5',
      //站点名
      stationName: '龙潭',
      //车位数
      restNumber: '36',
      usedNumber: '114',
      parkList: [{ name: "茅坪", value: "13" },
        { name: "百竹园", value: "10" },
        { name: "龙潭", value: "3" },
        { name: "龙潭（满员）", value: "6" },
      ],
    }
  },
  create() {
    this.getList();
  },
  mounted() {
    this.getList();
    this.getWeatherList();
    this.flash();
    this.updateDateTime();

    // 每秒更新一次
    this.timer = setInterval(() => {
      this.updateDateTime();
    }, 1000);
  },
  // 在Vue实例销毁前，清除我们的定时器
  beforeDestroy() {
    clearInterval(this.timer);
  },
  methods: {

    updateDateTime() {
      const now = new Date();

      // 日期时间格式化选项
      const options = {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        weekday: 'long',
        hour: 'numeric',
        minute: 'numeric',
        hour12: false
      };

      // 使用 Intl.DateTimeFormat 格式化日期时间
      const formatter = new Intl.DateTimeFormat('zh-CN', options);
      this.formattedDateTime = formatter.format(now);
    },
    /** weatherList from database */
    getWeatherList(){
      getWeather(this.queryParams2).then(response => {
        this.loading = false;
        this.weatherList = response.rows;

        const item = this.weatherList.find(item1 => item1.weatherId === 1 );
        this.weather = item.weatherInfo + " " + item.weatherLow + "~" + item.weatherHigh;
      });
    },
    /** weather */
    // getWeather() {
    //   console.log("getWeather方法正在被调用")
    //   this.loading = true;
    //   weatherParking().then(response => {
    //     this.loading = false;
    //     this.weather = response[0].type + " " + response[0].low + "~" + response[0].high;
    //
    //     console.log("getWeather方法停止被调用")
    //   });
    // },
    /**定时器 */
    flash() {
      const timer = setInterval(() => {
        // 获取后端数据
        this.getList();
        // 获取数据库的天气数据
        this.getWeatherList();
      }, 1000 * 60 *3);
      this.$once('hook:beforeDestroy', () => {
        clearInterval(timer);
      })
    },
    /**页面全屏 */
    toggleFullScreen() {
      if (screenfull.isEnabled) {
        screenfull.toggle(this.$refs.myImg);
      }
    },
    /** 查询停车场引导牌管理列表 */
    getList() {
      this.loading = true;
      listScenic(this.queryParams).then(response => {
        this.scenicList = response.rows;
        this.total = response.total;
        this.loading = false;
        const item = this.scenicList.find(item1 => item1.siteName === this.stationName || item1.siteName === '龙潭')
        this.phone = item.emergencyPhone;
        this.distance = item.distance;
        this.restNumber = item.attendance;
        this.usedNumber = item.parking;
        this.tickets = item.ticket;
        this.systemMsg[0].title = item.notice;
        this.weekDay = item.weekDay;
        this.newTime = this.weekDay;
        this.nearbyScenic = item.nearbyScenic;
        const str1 = item.nearbyScenic;
        var arr1 = eval('(' + str1 + ')');

        this.parkList = arr1;
      });
    },

  },

  // 修改 table cell边框的背景色
  tableCellStyle({ row, column, rowIndex, columnIndex }) {
    if (column.property === 'emptyNumber') {
      return {
        color: '#ec0b0b'
      }
    } else {
      return {
        color: '#FFFFFF'
      }
    }


  },
  // 修改 table header cell的背景色
  tableHeaderCellStyle() {
    return ' color: #606266; font-weight: bold; font-size:100%;'
  }

}
</script>
<style>
html,
body {
  width: 100%;
  height: 100%;
}

/*轮翻消息*/
.bs-sysMsg {
  position: relative;
  display: flex;
  width: 100%;
  padding: 8px 12px;
  margin-bottom: 10px;
  border-radius: 2px;
  color: hwb(357 28% 13%);
  background-color: #DFDFDF;
  overflow: hidden;
  opacity: 1;
  align-items: center;
  transition: opacity .2s;
}

.bs-sysMsg .msg__content {
  display: table-cell;
  padding: 0 8px;
  width: 100%;
  height: 50%;
}

.bs-sysMsg .msg__content a.item {
  color: #e01717;
  font-size: 17px;
  opacity: 0.75;
  font-weight: bold;
}

.bs-sysMsg .msg__content a.item:hover {
  text-decoration: underline;
}

.time {
  color: #f7f7f7;
  text-align: center;
}

.weather {
  color: #f7f7f7;
  text-align: center;
}

.box {
  margin: auto;
  text-align: center;
  height: 90%;
  width: 100%;
  background-color: #FAFAFA;
  position: fixed;
}

.body-top {
  background-color: #FAFAFA;
  height: 10%;
}

.station-name {
  text-align: center;
  height: 50%;

  position: relative;
  top: 0%;
  /*偏移*/
  transform: translateY(10%);
}

.body-main {
  background-color: #5B9BD8;
  height: 85%;
  width: 100%;
  margin: auto;
}

.weather-info {
  text-align: center;
  position: relative;
  top: 0%;
  /*偏移*/
  transform: translateY(10%);
  height: 10%;
}

.park-info {
  height: 95%;
  width: 95%;
  margin: auto;
}

.park-info-p1 {
  border-style: solid;
  /* 上 右 下 左 */
  border-color: #5B9BD8 #5B9BD8 #FFFFFF #5B9BD8;
  background-color: #FFFFFF;
  height: 30%;
  width: 99%;
}

.park-info-p2 {
  border-style: solid;
  border-color: #FFFFFF #5B9BD8 #5B9BD8 #5B9BD8;
  background-color: #FFFFFF;
  height: 10%;
  width: 99%;
}

.park-info-p3 {

  height: 30%;
  width: 99%;
}

.park-left {
  height: 90%;
  width: 50%;
  text-align: center;
  float: left;
}

.park-icon {
  height: 90%;
  width: 60%;
  margin: auto;
  display: inline-block;
}

.park-right {
  height: 90%;
  width: 50%;

  margin: auto;
  float: left;
}

.park-distance {
  height: 45%;
  width: 100%;
  text-align: center;
  position: relative;
  top: 5%;
  /*偏移*/
  transform: translateY(50%);

  float: left;
  color: black;
  font-Size: 100%;
  font-weight: bold;



}

.park-station {
  height: 45%;
  width: 100%;
  text-align: center;
  float: left;
  color: black;
  font-Size: 100%;
  font-weight: bold;

}

.img {
  width: 90%;
  height: 90%;
  padding: 10px 0;
  text-align: center;
  float: left;

}


.body-down {
  background-color: #5B9BD8;
  height: 25%;
}

.phone-info {
  background-color: #5B9BD8;
  height: 50%;
  text-align: center;
  position: relative;
  top: 0%;
  /*偏移*/
  transform: translateY(10%);
  font-size: calc(100vw * 30 / 1920);

}

.phone-info-h {
  background-color: #5B9BD8;
  color: #f6f3f3;

}

.restNumber {
  height: 50%;
  font-weight: bold;
  font-size: 100%;
}

.usedNumber {
  height: 50%;
  font-weight: bold;
  font-size: 100%;
}

.restNumberColor {
  color: #e01717;
}

.usedNumberColor {
  color: #17aa28;
}

.test {
  color: #ec0b0b;
}

.el-table::before {
  background-color: white;
}


</style>
