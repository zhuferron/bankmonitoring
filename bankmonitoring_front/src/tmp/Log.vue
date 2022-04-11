<template>
  <div>
    <strong style="font-size:30px;">---日志监控概况---</strong>
    <br>
    <br>
    <div class="block">
      <span class="demonstration">设置默认时间：</span>
      <el-date-picker
          v-model="params.createTime"
          type="date"
          placeholder="选择日期">
      </el-date-picker>
      <el-button type="text" @click="getTableData">搜索</el-button>
    </div>
    <div>
    <el-table :data="data" style="max-width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-alert
                    type= "warning"
                    :closable="false">
              <el-form-item label="日志摘要">
                <span>{{ props.row.infoContent}}</span>
              </el-form-item>
            </el-alert>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column prop="logId" label="日志ID" align="center">
      </el-table-column>
      <el-table-column prop="sysId" label="系统ID" align="center">
      </el-table-column>
      <el-table-column label="状态" align="center">
        <template scope="scope">
        <!--<el-tag :prop="scope.row.state" :type="setType(scope.row.state)" size="medium">{{scope.row.state}}</el-tag>-->
          <el-tag :prop="scope.row.state"
                  :type="scope.row.state === '0' ? 'danger' : 'success'"
                  disable-transitions>{{scope.row.state}}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" align="center">
      </el-table-column>
</el-table>
    </div>
    <div>
      <div id="myChart" :style="{width: '1000px', height: '300px'}"></div>
    </div>

  </div>
</template>

<script>
import moment from 'moment'
import Moment from "moment";
export default {
  data() {
    return {
      data: [],
      params: {
        createTime: null,
        sysId: null
      },
      options: [{
        value: '系统1',
        label: '系统1'
      }, {
        value: '系统2',
        label: '系统2'
      }, {
        value: '系统3',
        label: '系统3'
      }]
    }
  },
  created() {
    this.getTableData();
  },
  mounted() {
    this.test();
  },
  methods: {
    /*test(){
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      myChart.setOption({
        color: ['#80FFA5', '#00DDFF', '#37A2FF', '#FF0087', '#FFBF00'],
        title: {
          text: '渐变堆叠面积图'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'cross',
            label: {
              backgroundColor: '#6a7985'
            }
          }
        },
        legend: {
          data: ['Line 1', 'Line 2', 'Line 3', 'Line 4', 'Line 5']
        },
        toolbox: {
          feature: {
            saveAsImage: {}
          }
        },
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: [
          {
            type: 'category',
            boundaryGap: false,
            data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: 'Line 1',
            type: 'line',
            stack: '总量',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(128, 255, 165)'
              }, {
                offset: 1,
                color: 'rgba(1, 191, 236)'
              }])
            },
            emphasis: {
              focus: 'series'
            },
            data: [140, 232, 101, 264, 90, 340, 250]
          },
          {
            name: 'Line 2',
            type: 'line',
            stack: '总量',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(0, 221, 255)'
              }, {
                offset: 1,
                color: 'rgba(77, 119, 255)'
              }])
            },
            emphasis: {
              focus: 'series'
            },
            data: [120, 282, 111, 234, 220, 340, 310]
          },
          {
            name: 'Line 3',
            type: 'line',
            stack: '总量',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(55, 162, 255)'
              }, {
                offset: 1,
                color: 'rgba(116, 21, 219)'
              }])
            },
            emphasis: {
              focus: 'series'
            },
            data: [320, 132, 201, 334, 190, 130, 220]
          },
          {
            name: 'Line 4',
            type: 'line',
            stack: '总量',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(255, 0, 135)'
              }, {
                offset: 1,
                color: 'rgba(135, 0, 157)'
              }])
            },
            emphasis: {
              focus: 'series'
            },
            data: [220, 402, 231, 134, 190, 230, 120]
          },
          {
            name: 'Line 5',
            type: 'line',
            stack: '总量',
            smooth: true,
            lineStyle: {
              width: 0
            },
            showSymbol: false,
            label: {
              show: true,
              position: 'top'
            },
            areaStyle: {
              opacity: 0.8,
              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                offset: 0,
                color: 'rgba(255, 191, 0)'
              }, {
                offset: 1,
                color: 'rgba(224, 62, 76)'
              }])
            },
            emphasis: {
              focus: 'series'
            },
            data: [220, 302, 181, 234, 210, 290, 150]
          }
        ]
          }
      );
    },*/
    async getTableData() {
      const {data: res1} = await this.$http.get('http://172.21.218.52:9002/getSystemLogInfoByTime/' + '2021-05-18_00:00:00')  //访问后台
      if (res1.code == 200) {
        this.$message.success(res1.msg);  //信息提示
        this.data = JSON.parse(res1.data);
        for (var i = 0; i < this.data.length; i++) {
          this.data[i].createTime = this.getFullTime(this.data[i].createTime)
        }
      }
      setTimeout(() => {
        let date = new Date(this.params.createTime);
        date = moment(date).format('YYYY-MM-DD');
        if (this.params.createTime) {
          data = data.filter((r) => {
            return r.createTime == date;
          });
        }
        if (this.params.sysId) {
          data = data.filter((r) => {
            return r.sysId == this.params.sysId;
          });
        }
      }, 500);
    },
      /* getTableData() {
      let mockdata=[
        {
          "logId":"log123",
          "sysId":"系统1",
          "infoContent":"日志内容11",
          "state":"0",
          "createTime":"2021-04-15"}
        ,{"logId":"log456",
          "sysId":"系统1",
          "infoContent":"日志内容12",
          "state":"1",
          "createTime":"2021-04-16"}
        ,{"logId":"log789",
          "sysId":"系统2",
          "infoContent":"日志内容21",
          "state":"1",
          "createTime":"2021-04-15"}
        ,{"logId":"log101112",
          "sysId":"系统2",
          "infoContent":"日志内容22",
          "state":"0",
          "createTime":"2021-04-17"}
      ];
      setTimeout(()=>{
        let date=new Date(this.params.createTime);
        date=moment(date).format('YYYY-MM-DD');
        if(this.params.createTime){
          // date=timestampToTime(date);
          console.log(date);
          mockdata=mockdata.filter((r)=>{
            // let row=new Date(r.createTime);
            console.log(r.createTime);
            return r.createTime==date;
          });
        }
        if(this.params.sysId){
          mockdata=mockdata.filter((r)=>{
            return r.sysId==this.params.sysId;
          });
        }
        this.data=mockdata;
      },500);


      },*/
      setValueNull(val)
      {
        this.params.sysId = null;
      },
      setType(number)
      {
        var state = "success";
        if (number == 0) {
          return state = "info"
        }
        return state;
      },
      getFullTime(timeStamp)
      {
        const stamp = new Date(timeStamp);
        const time = Moment(stamp).format('YYYY-MM-DD HH:mm:ss');
        return time;
      }
    }
}
</script>