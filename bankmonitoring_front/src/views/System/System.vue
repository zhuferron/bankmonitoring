
<template>
  <div>
    <strong style="font-size:30px;">---系统列表---</strong>
    <div style="padding-top: 2%">
      <el-carousel :interval="4000" type="card">
        <el-carousel-item v-for="item in systemBox" :key= "item.sysId">
          <el-card class="box-card">
            <div>
              <p class="text1">{{ "系统名称："+item.sysName }}</p>
              <p class="text2">{{ "系统版本："+item.version }}</p>
              <p class="text2">{{"系统ID："+item.sysId}}</p>
              <el-button style="float:right" class="text2" type="text" @click="goSystem(item.sysId)">详细信息</el-button>
            </div>
          </el-card>
        </el-carousel-item>
      </el-carousel>
    </div>
    <strong style="font-size:30px;">---消息提示---</strong>
    <div style="padding-top: 2%">

      <el-alert title="xxx成功登录" type="success" show-icon>
      </el-alert>
      <br>
      <el-alert title="xxx你有6条未读邮件" type="info" show-icon>
      </el-alert>
      <br>
      <el-alert title="系统xxx数据库xx掉线" type="error" show-icon>
      </el-alert>
      <br>
      <el-alert title="系统xx主机xx内存使用率过低" type="warning" show-icon>
      </el-alert>
      <br>
      <el-alert title="强告警邮件1发送成功" type="success" show-icon>
      </el-alert>
      <br>
      <el-alert title="强告警邮件2发送成功" type="success" show-icon>
      </el-alert>
      <br>
      <el-alert title="弱告警邮件1发送成功" type="info" show-icon>
      </el-alert>
      <br>
      <el-alert title="弱告警邮件2发送成功" type="info" show-icon>
      </el-alert>
    </div>

  </div>
</template>

<script>


export default {
  name: "Welcome",
  created() {
    this.getData();
  },
  methods:{
    async getData(){
      const {data: res} = await this.$http.get('http://192.168.43.73:9002/getAllSystemInfo')  //访问后台
      if(res.code==200){
        this.$message.success(res.msg)  //信息提示
        this.systemBox=JSON.parse(res.data);
      }
    },
    goSystem(id){
      this.$router.push("/systemDetail")
      window.sessionStorage.setItem("systemId",id); //存储user对象
    },
    goEmail(){
      this.$router.push("/email")
    },

  },
  data() {
    return {
      systemBox:[],
    }
  }
}
</script>

<style lang="less" scoped>
.home-container{
  height: 100%;
}
//头部导航栏
.el-header{
  background-color: #2b2b2b;
  display: flex;
  justify-content: space-between;  //左右贴边
  padding-left: 0;  //左边界
  align-items: center;
  color: #fff;
  font-size: 30px;
  >div{
    display: flex;
    align-items: center;
    span{
      margin-left: 15px;
    }
  }
}

//侧边导航栏整体
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 300px;
  min-height: 400px;
}
.el-menu--collapse {
  width: 100px;
}
.aside-container{
  width: 300px;
}

.el-aside{
  background-color: #545c64;
  .el-menu{
    border-right: none;
  }
}

.el-main{
  background-color: #eaedf1;
}
img{
  width: 55px;
  height: 55px;
}
//  |||按钮样式
.toggle-button{
  background-color: #4A5064;
  font-size: 20px;
  line-height: 50px;
  color: #fff;
  text-align: center;
  letter-spacing: 0.2em;
  cursor: pointer;  //显示小手
}

.el-menu-item i{
  color: #90a3c9;
}

//单个模块
.el-menu-item {
  font-size: 25px;
  color: #303133;
  cursor: pointer;
  transition: border-color .3s,background-color .3s,color .3s;
  box-sizing: border-box;
  height : 100px;
}
.el-menu-item * {
  vertical-align: middle;
  margin-top: 15px;
}

.el-menu-item [class^="el-icon-"] {
  margin-right: 5px;
  margin-top: 10px;
  width: 24px;
  text-align: center;
  font-size: 35px;
  vertical-align: middle;
}

.el-menu--collapse > .el-menu-item [class^="el-icon-"], .el-menu--collapse > .el-submenu > .el-submenu__title [class^="el-icon-"] {
  margin-right: 8px;
  vertical-align: middle;
  width: 24px;
  text-align: center;
}
.iconfont{
  font-family: "iconfont" !important;
  font-size: 35px;
  font-style: normal;
  margin-top :10px;
}

//首页组件
//.el-carousel__item h3 {
//  color: #616469;
//  font-size: 14px;
//  opacity: 0.5;
//  line-height: 70px;
//}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.box-card{
  background-color:#82cddb;
  height: 100%;
  width: 100%;
  font-size: 20px;
  font-style: normal;
}
.text1{
  color: #f6db34;
  font-size: 180%;
}
.text2{
  color: #ffffff;
  font-size: 100%;
}


</style>