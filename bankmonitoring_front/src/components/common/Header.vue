<template>
  <div>
    <div>
      <img src="../../assets/images/platformlogo.png"/>
    </div>
    <span style="font-weight:bold">EagleBinGO</span>
    <span style="font-size: 25px;padding-left: 1500px">Hi! {{this.username}}</span>
    <div style="width: 200px" >
      <el-avatar icon="el-icon-user-solid" :src="img" >
      </el-avatar>
    </div>

    <el-dropdown>
      <i class="el-icon-setting" style="padding-left: 0px;font-size: 30px;"></i>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item>
          <div type="info" @click="goInfo" > 个人信息</div>
        </el-dropdown-item>
        <el-dropdown-item>
          <div type="info" @click="resetPassword" > 修改密码</div>
        </el-dropdown-item>
        <el-dropdown-item>
          <div type="info" @click="logout" > 退出登录</div>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>

</template>

<script>
export default {
  name: "Header",
  data(){
    return{
      username:'',
      img:require("../../assets/images/img.jpg")
    }
  },
  created() {
    this.loadUsername();
  },
  methods: {
    //退出登录
    logout() {
      window.sessionStorage.clear(); //清除session
      this.$router.push("/login")  //回到首页
    },
    loadUsername(){
      this.username=window.sessionStorage.getItem("username");
    },
    goInfo(){
      this.$router.push("/Info");
    },
    resetPassword(){
      this.$router.push("/ResetPassword");
    },
  }
}
</script>

<style lang="less" scoped>
.home-container{
 height: 100%;
}
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
      margin-left: 8px;
    }
  }
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
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
  width: 60px;
  height: 50px;
  margin-top: 5px;
  margin-left: 30px;
}


</style>