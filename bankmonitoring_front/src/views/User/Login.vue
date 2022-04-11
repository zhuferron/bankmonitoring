<template>
  <div id="login" >
    <div class="login_container">
      <!--登录块-->
      <div class="login_box">
          <div class="avatar_box">
              <!--          <img src="../asse/ts/images/logo.png"/>-->
              <img src="../../assets/images/loginlogo.png"/>
              <h1>EagleBinGO!</h1>
        <!--表单区域-->
        <el-form ref="loginFormRef" :rules="loginRules" :model="loginForm" class="login_form" label-width="0px">
          <!--用户名-->
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder='用户名' prefix-icon="iconfont icon-denglu"></el-input>
          </el-form-item>
          <!--密码-->
          <el-form-item prop="password">
            <el-input v-model="loginForm.password" placeholder='密码' prefix-icon="iconfont icon-mima" type="password"></el-input>
          </el-form-item>

          <!--按钮-->
          <el-form-item class="btns">
            <el-button type="primary" @click="login()">登录</el-button>
            <el-button type="warning" @click="resetLoginForm()">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
  </div>
</template>


<script>


export default {
  name: "Login",
  data() {
    return {
      //表单数据
      loginForm: {
        username: "",
        password: ""
      },
      //验证对象：校验规则
      loginRules: {
        username: [
          {required: true, message: '用户名称为必填项', trigger: 'blur'}, //必填项验证
          {min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur'} //长度验证
        ],
        password: [
          {required: true, message: '用户密码为必填项', trigger: 'blur'}, //必填项验证
          {min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur'} //长度验证
        ]
      },
    }
  },
  methods: {
    //异步加载数据，登录
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return; //验证失败
        const {data: res} = await this.$http.post('http://192.168.43.73:9001/login',this.loginForm)  //访问后台
        if (res.code == 200) {
          this.$message.success(res.msg)  //信息提示
          window.sessionStorage.setItem("id", res.data.id); //存储user对象
          window.sessionStorage.setItem("username", res.data.username); //存储user对象
          this.$router.push({path: "/home"})  //页面跳转
        } else {
          this.$message.error(res.msg)  //错误提示
        }
      })
    },
    //重置表单内容
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
  },
}

</script>

<style lang='less' scoped>
//根节点样式
.login_container{
  width: 100vw;
  padding: 0;
  margin: 0;
  height: 100vh;
  font-size: 16px;
  background-repeat: no-repeat;
  background-position: left top;
  background-color: #242645;
  color: #fff;
  font-family: "Source Sans Pro";
  background-size: 100%;
  background-image: url("../../assets/images/login.png");
  position: absolute;
  -webkit-background-size: cover;
}

.avatar_box{
    position:absolute;
    height: 500px;
    width: 500px;
    top: 5%;
   right:-5%;
}
img{
    width: 170px;
    height: 140px;
}
h1{
    font-weight:bold;
    font-size: 60px;
    font-family: Calibri;
    position:absolute;
    bottom: 235px;
    right: 80px;
}

.login_box{
  width: 450px;
  height: 300px;
  background-color: #2b2b2b;
  border-radius: 3px;
  opacity:0.8;
  position: absolute;
  background-color:transparent;
  top: 30%;
  left: 50%;
  transform: translate(-50%,-50%);
}
.btns{
  display: flex;
  justify-content: center;
}
.login_form{
  position: absolute;
  bottom: 0;
  width: 80%;  /*这里宽度100%太宽了，所以设置成80%，为了居中，需要在向右移动10%的位置*/
  top: 50%;
  left: 10%;
  padding: 0 10px;
  box-sizing: border-box;
}

</style>