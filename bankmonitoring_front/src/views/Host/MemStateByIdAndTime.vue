<template>
  <div>
    <div class="block">
      <span class="demonstration">设置默认时间</span>
      <el-date-picker
          v-model="params.createTime"
          type="date"
          placeholder="选择日期">
      </el-date-picker>
      <el-button type="text" @click="getTableData">搜索</el-button>
    </div>
    <div>
      <el-table :data="dataShow" style="width: 100%">
        <el-table-column prop="hostId" label="主机ID" align="center">
        </el-table-column>
        <el-table-column prop="createTime" label="查询时间" align="center">
        </el-table-column>
        <el-table-column label="进度" align="center">
          <template scope="scope">
            <el-progress :stroke-width="22":text-inside="true":percentage="getNumber(scope.row.usePer)" :show-text='true':status="setColor(getNumber(scope.row.usePer))"></el-progress>
            <div slot="reference" class="name-wrapper">
              <el-tag size="medium">未用：{{ scope.row.free }}</el-tag>
              &#12288;
              <el-tag size="medium">已用：{{ scope.row.free }}</el-tag>
              &#12288;
              <el-tag size="medium">总共：{{ scope.row.free }}</el-tag>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="memStateId" label="memId" align="center">
        </el-table-column>
      </el-table>
    </div>
    <div style="padding-top: 5%">
      <el-pagination background layout="prev, pager, next" :page-count="pageNum" :current-page="currentPage"  @current-change="curPage" @prev-click="prePage" @next-click="nextPage"></el-pagination>
    </div>
  </div>
</template>

<script>
import moment from "moment";
import Moment from "moment";

export default {
  data() {
    return {
      data: [],
      totalPage: [],
      // 每页显示数量
      pageSize: 8,
      // 共几页
      pageNum: 50,
      // 当前显示的数据
      dataShow: [],
      // 默认当前显示第一页
      currentPage: 1,
      params: {
        createTime: null,
        hostId: null
      },
      options: [{
        value: 'host1',
        label: 'host1'
      }, {
        value: 'host2',
        label: 'host2'
      }, {
        value: 'host3',
        label: 'host3'
      }]
    }
  },
  created() {
    this.getTableData();
  },
  methods:{
    async getTableData() {
      const {data: res1} = await this.$http.get('http://192.168.43.73:9002/getMemStateByTime/'+ '2021-06-25_14:00:00')  //访问后台
      if(res1.code==200){
        this.$message.success(res1.msg);  //信息提示
        this.data=JSON.parse(res1.data);
        for(var i = 0; i < this.data.length; i++){
          this.data[i].createTime=this.getFullTime(this.data[i].createTime)
        };
      }

      setTimeout(()=>{
        let date=new Date(this.params.createTime);
        date=moment(date).format('YYYY-MM-DD');
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // System.out.println(sdf.format(date));
        if(this.params.createTime){
          this.dataShow=this.dataShow.filter((r)=>{
            let row=new Date(r.createTime);
            return this.chooseYMD(r.createTime)==date;
          });
        }
      },500);

      //分页功能实现
      this.pageNum = Math.ceil(this.data.length / this.pageSize) || 1;
      for (let i = 1; i < this.pageNum+1; i++) {
        this.totalPage[i] = this.data.slice(this.pageSize * (i-1), this.pageSize * i)
      }
      // 获取到数据后显示第一页内容
      this.dataShow = this.totalPage[this.currentPage];
    },
    //下一页
    nextPage() {
      if (this.currentPage === this.pageNum - 1) return ;
      this.dataShow = this.totalPage[++this.currentPage];
    },
    // 上一页
    prePage() {
      if (this.currentPage === 0) return ;
      this.dataShow = this.totalPage[--this.currentPage];
    },
    curPage(val){
      this.currentPage = val;
      this.dataShow = this.totalPage[this.currentPage];
    },
    setColor(percentage){
      var state = "success";
      if(percentage>50&&percentage<80){return state="warning"}
      if(percentage>=80){return state="exception"}
      return state;
    },
    setValueNull(val) {
      this.params.hostId=null;
    },
    getNumber(str){
      return parseInt(str.replace("%",""));
    },
    chooseYMD(times){
      var timearr = times.replace(" ", ":").replace(/\:/g, "-").split("-");
      var timestr = ""+timearr[0]+"-" + timearr[1] + "-" + timearr[2]
      return timestr
    },
    getFullTime(timeStamp) {
      const stamp = new Date(timeStamp);
      const time = Moment(stamp).format('YYYY-MM-DD HH:mm:ss');
      return time;
    },

  }

}

</script>

<style scoped>

</style>