<template>
  <div>
    <div>
      <p>你的账号是 {{id}}</p>
      <p>修改密码以后，可以使用账号和密码登录</p>
      <br>
      <br>
    </div>
    <template>
      <el-form class="user-account-key" ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="原密码" prop="oldPassword" style = "width: 450px;">
          <el-input type="password" placeholder="请输入原密码" v-model="form.oldPassword"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword" style = "width: 450px">
          <el-input type="password" placeholder="请设置新密码" v-model="form.newPassword"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="qrpassword" style = "width: 450px">
          <el-input type="password" placeholder="请确认新密码" v-model="form.qrpassword"></el-input>
        </el-form-item>

        <br>
        <el-form-item>
          <el-button type="primary" @click="onSubmit('form')" >修改</el-button>
          <el-button @click="$refs['form'].resetFields()" style = "margin-right: 73%">重置</el-button>
        </el-form-item>
      </el-form>
    </template>
  </div>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.form.qrpassword !== '') {
          this.$refs.form.validateField('qrpassword');
        }
        callback();
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.form.newPassword) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    }
    return {
      zhanghao: '',
      id:'',
      form: {
        id: '',
        oldPassword: '',
        newPassword: '',
        qrpassword: '',
      },
      rules: {
        //验证规则
        oldPassword: [{
          validator:validatePass,
          // required: true,
          // message: '请输入原密码',
          trigger: 'blur'
        }],
        newPassword: [{
          validator:validatePass,
          // required: true,
          // message: '请设置新密码',
          trigger: 'blur'
        },  {min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur'}],
        qrpassword: [{
          validator:validatePass2,
          // required: true,
          // message: '请确认新密码',
          trigger: 'blur'
        }, ]
      }
    }
  },
  created() {
    this.getZhang();
  },
  methods: {
    getZhang() {
      this.form.id = window.sessionStorage.getItem("id");
    },
    async onSubmit() {
      const {data: res} = await this.$http.post('http://192.168.43.73:9001/resetPass',this.form)  //访问后台
      if(res.code==200){
        this.$message.success(res.msg);  //信息提示
      }else{
        this.$message.error(res.msg);  //信息提示
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }

  },
}
</script>

<style scoped>
.change-back {
  position: absolute;
  top: 24%;
  left: 0%;
}

.change-content {
  position: absolute;
  top: 40%;
  left: 40%;
}

.change-zhanghao {
  position: relative;
  margin-top: 51px;
  margin-left: 15px;
}

.zhanghao {
  margin-bottom: 0;
}

.user-account-key {
  width: 88%;
}
</style>