<template>
  <div>
    <strong style="font-size:30px;">---数据库监控概况---</strong>
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
      <el-table :data="dataShow" style="max-width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-alert
                  type= "warning"
                  :closable="false">
                <el-form-item label="数据库ID">
                  <span>{{ props.row.dbId }}</span>
                </el-form-item>
                <el-form-item label="数据库名称">
                  <span>{{ props.row.dbName }}</span>
                </el-form-item>
                <el-form-item label="数据库类型">
                  <span>{{ props.row.dbType }}</span>
                </el-form-item>
                <el-form-item label="数据库端口">
                  <span>{{ props.row.port }}</span>
                </el-form-item>
                <el-form-item label="数据库状态">
                  <span>{{ props.row.dbState}}</span>
                </el-form-item>
              </el-alert>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column prop="dbId" label="数据库ID" align="center">
        </el-table-column>
        <el-table-column prop="sysId" label="系统ID" align="center">
        </el-table-column>
        <el-table-column prop="dbName" label="数据库名称" align="center">
        </el-table-column>
        <el-table-column label="状态" align="center">
          <template scope="scope">
            <el-tag :prop="scope.row.dbState"
                    :type="scope.row.dbState === '0' ? 'danger' : 'success'"
                    disable-transitions>{{scope.row.dbState}}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" align="center">
        </el-table-column>
      </el-table>
    </div>
    <div style="padding-top: 5%">
      <el-pagination background layout="prev, pager, next" :page-count="pageNum" :current-page="currentPage"  @current-change="curPage" @prev-click="prePage" @next-click="nextPage"></el-pagination>
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
              totalPage: [],
              // 每页显示数量
              pageSize: 10,
              // 共几页
              pageNum: 50,
              // 当前显示的数据
              dataShow: [],
              // 默认当前显示第一页
              currentPage: 1,
              params: {
                createTime: null,
                sysId: null
              },
              options: [{
                value: '系统1',
                label: '系统1'
              }, {
                value: '系统1',
                label: '系统2'
              }, ]
            }
        },
        created() {
            this.getTableData();
        },
        methods:{
          async getTableData() {
            const {data: res1} = await this.$http.get('http://192.168.43.73:9002/getSystemDbInfoByTime/'+ '2021-06-25_14:00:00')  //访问后台
            if(res1.code==200) {
              this.$message.success(res1.msg);  //信息提示
              this.data = JSON.parse(res1.data);
              for (var i = 0; i < this.data.length; i++) {
                this.data[i].createTime = this.getFullTime(this.data[i].createTime)
              }


              setTimeout(()=>{
                let date=new Date(this.params.createTime);
                date=moment(date).format('YYYY-MM-DD');
                if(this.params.createTime){
                  this.dataShow=this.dataShow.filter((r)=>{
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

            }
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
          getFullTime(timeStamp) {
            const stamp = new Date(timeStamp);
            const time = Moment(stamp).format('YYYY-MM-DD HH:mm:ss');
            return time;
          },
          chooseYMD(times){
            var timearr = times.replace(" ", ":").replace(/\:/g, "-").split("-");
            var timestr = ""+timearr[0]+"-" + timearr[1] + "-" + timearr[2]
            return timestr
          }



  }
}

</script>

<style scoped>

.demo-table-expand {
  font-size: 0;
}
.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}
.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.page{
  position :absolute;
  bottom: 5%;
  right: 35%;
}

</style>