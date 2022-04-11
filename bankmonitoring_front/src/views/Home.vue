<template>
  <!--整体容器  -->
  <el-container class="home-container">
    <!--头部-->
    <el-header style="height: 80px;">
      <Header></Header>
    </el-header>
    <!--身体容器 -->
    <el-container>
      <!--侧边栏：头部导航栏已经模块化，侧边栏就不模块了，因为太麻烦且没必要-->
      <el-aside :width="isCollapse?'100px':'300px'">
        <div class="toggle-button" @click="toggleCollapase">
          <i class="iconfont icon-shousuo" style = "font-size: 30px;"></i>
        </div>
        <el-menu class="el-menu-vertical-demo"
                 @open="handleOpen"
                 @close="handleClose"
                 background-color="#545c64"
                 text-color="#fff"
                 active-text-color="#ffd04b"
                 :collapse="isCollapse">
          <el-menu-item index="1" @click="goScreen">
            <i class="el-icon-discover"></i>
            <span slot="title" style="margin-left:10px" >首页大屏</span>
          </el-menu-item>
          <el-menu-item index="2" @click="goWelcome">
            <i class="el-icon-s-home"></i>
            <span slot="title" style="margin-left:10px" >系统列表</span>
          </el-menu-item>
          <el-menu-item index="3" @click="goProcess" >
            <i class="el-icon-data-analysis"></i>
            <span slot="title" style="margin-left:10px">进程监控</span>
          </el-menu-item>
          <el-menu-item index="4" @click="goDataBase">
            <i class="el-icon-coin"></i>
            <span slot="title" style="margin-left:10px">数据库监控</span>
          </el-menu-item>
          <el-menu-item index="5" @click="goShowLog">
            <i class="el-icon-notebook-1" ></i>
            <span slot="title"style="margin-left:10px" >日志监控</span>
          </el-menu-item>
          <el-submenu index="6" style="padding-top: 1px;padding-bottom: 30px">
            <template  slot="title">
              <i  style="font-size: 45px" class="el-icon-monitor"></i>
              <span slot="title" style="margin-left:10px;font-size: 28px">主机监控</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="7-1" @click="goHost">内存状况</el-menu-item>
              <el-menu-item index="7-2" @click="getDeskStateByIdAndTime">磁盘状态</el-menu-item>
              <el-menu-item index="7-3" @click="goChartsTable">网络状态</el-menu-item>
              <el-menu-item index="7-4" @click="getTcpStateByIdAndTime">TCP连接</el-menu-item>
              <el-menu-item index="7-5" @click="getCpuStateByIdAndTime">Cpu状态</el-menu-item>
              <el-menu-item index="7-6" @click="getHostLoadByIdAndTime">主机负载</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item index="8" @click="goEmail">
            <i class="el-icon-message"></i>
            <span slot="title"style="margin-left:10px">邮件告警</span>
          </el-menu-item>
        </el-menu>
      </el-aside>
      <!--主体容器-->
      <el-container>
        <!--主体-->
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>

import Header from "@/components/common/Header";

export default {

  name: "Home",
  components: {Header},
  data(){
    return{
      isCollapse: true,
    }
  },

  methods:{
    goWelcome(){
      this.$router.push("/system")
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },

    goEmail(){
      this.$router.push("/email")
    },
    goProcess(){
      this.$router.push("/process")
    },
    goDataBase(){
      this.$router.push("/dataBase")
    },
    goShowLog(){
      this.$router.push("/log")
    },
    goScreen(){
      this.$router.push("/screen")
    },
    goHost(){
      this.$router.push("/memStateByIdAndTime")
    },
    goChartsTable(){
      this.$router.push("/netioStateByIdAndTime")
    },
    getSystemHostInfoByIdAndTime(){
      this.$router.push("/systemHostInfoByIdAndTime")
    },
    getTcpStateByIdAndTime(){
      this.$router.push("/tcpStateByIdAndTime")
    },
    getDeskStateByIdAndTime(){
      this.$router.push("/deskStateByIdAndTime")
    },
    getCpuStateByIdAndTime(){
      this.$router.push("/cpuStateByIdAndTime")
    },
    getHostLoadByIdAndTime(){
      this.$router.push("/loadStateByIdAndTime")
    },
    //控制伸缩
    toggleCollapase(){
      this.isCollapse = !this.isCollapse;
    },



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
  font-size: 10px;
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
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

</style>