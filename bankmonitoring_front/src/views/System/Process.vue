<template>
    <div>
      <strong style="font-size:30px;">---进程监控概况---</strong>
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
                <div class = 'label' style = "text-align: center">
                  <el-col :span="20" style = "padding-left: 15%">
                    <el-card class = "status" shadow="hover" style = "text-align: center">
                      <el-form-item label="CPU">
                        <el-progress type="circle" :percentage="getNumber(props.row.processCpuOccupy)"></el-progress>
                      </el-form-item>
                      <el-form-item label="内存" style="padding-left: 10%" >
                        <el-progress type="circle" :percentage="getNumber(props.row.processMemOccupy)"></el-progress>
                      </el-form-item>
                      <br>
                      <el-form-item label="读取" style="padding-right: 15%;font-size: larger">
                        <el-tag type="warning">{{ props.row.processRead }} m/s</el-tag>
                      </el-form-item>
                      <el-form-item label="写入" style="right: 5%;font-size: larger">
                        <el-tag type="warning">{{ props.row.processWrite }} m/s</el-tag>
                      </el-form-item>
                    </el-card>
                  </el-col>
                </div>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column prop="processId" label="进程ID" align="center">
          </el-table-column>
          <el-table-column prop="sysId" label="系统ID" align="center">
          </el-table-column>
          <el-table-column prop="processName" label="进程名称" align="center">
          </el-table-column>
          <el-table-column label="状态" align="center">
            <template scope="scope">
              <el-tag :prop="scope.row.processState"
                      :type="scope.row.processState === '0' ? 'danger' : 'success'"
                      disable-transitions>{{scope.row.processState}}
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
    import Moment from 'moment';
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
                value: '系统2',
                label: '系统2'
              }],
        }
        },
        created() {
            this.getTableData();

        },
        methods:{
          async getTableData() {
            const {data: res1} = await this.$http.get('http://192.168.43.73:9002/getSystemProcessInfoByTime/'+ '2021-06-25_14:00:00')  //访问后台
            if(res1.code==200){
              this.$message.success(res1.msg);  //信息提示
              this.data=JSON.parse(res1.data);
              for(var i = 0; i < this.data.length; i++){
                this.data[i].createTime=this.getFullTime(this.data[i].createTime)
              };
            }
            setTimeout(()=>{
              let date=new Date(this.params.createTime);
              date=Moment(date).format('YYYY-MM-DD');
              if(this.params.createTime){
                this.dataShow=this.dataShow.filter((r)=>{
                  return this.chooseYMD(r.createTime)==date;
                });
              }
            },500);
            // 分页功能实现
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

          setValueNull(val) {
            this.params.sysId=null;
          },
          setType(number){
            var state = "success";
            if(number==0){return state="info"}
            return state;
          },
          getNumber(str){
            return parseInt(str.replace("%",""));
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
          },

        }
    }
</script>

<style scoped>
.page{
    position :absolute;
    bottom: 5%;
    right: 35%;
}
    .label{
        position: relative;
        text-align:center;
    }
</style>