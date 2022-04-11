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
            <el-table :data="data" style="max-width: 100%">
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
<!--        <el-pagination-->
<!--                class="page"-->
<!--                @size-change="handleSizeChange"-->
<!--                @current-change="handleCurrentChange"-->
<!--                background-->
<!--                :current-page.sync="currentPage1"-->
<!--                :page-size="1"-->
<!--                layout="prev, pager, next"-->
<!--                :total="10">-->
<!--        </el-pagination>-->
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
            const {data: res1} = await this.$http.get('http://192.168.43.233:9002/getSystemDbInfoByTime/'+ '2021-05-18_00:00:00')  //访问后台
            if(res1.code==200){
              this.$message.success(res1.msg);  //信息提示
              this.data=JSON.parse(res1.data);
              for(var i = 0; i < this.data.length; i++){
                this.data[i].createTime=this.getFullTime(this.data[i].createTime)
              }
            }
            setTimeout(()=>{
              let date=new Date(this.params.createTime);
              date=moment(date).format('yyyy-MM-dd HH:mm:ss');
              if(this.params.createTime){
                data=data.filter((r)=>{
                  return r.createTime==date;
                });
              }
            },500);
          },
            /*getTableData() {
                let mockdata=[
                    {"dbId":"db123",
                        "sysId":"系统1",
                        "dbName":"数据库1",
                        "dbType":"my sql",
                        "dbPort":"6379",
                        "dbState":"1",
                        "createTime":"2021-04-15 14:45:26.011"},
                    {"dbId":"db456",
                        "sysId":"系统2",
                        "dbName" : "数据库2",
                        "dbType":"sql server",
                        "dbPort":"5678",
                        "dbState":"0",
                        "createTime":"2021-04-15 14:45:26.011"},
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
            setValueNull(val) {
                this.params.sysId=null;
            },
            setType(number){
                var state = "success";
                if(number==0){return state="info"}
                return state;
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