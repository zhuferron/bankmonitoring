<template>
  <div>
    <el-form :model="emailForm" :rules="emailRules" ref="emailForm" size="medium" label-position="left" label-width="10%">
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="14" :offset="6">
            <el-form-item label="标题" prop="subject" class="coption">
              <el-input v-model="emailForm.subject" style="width: 50%;align-self: center" clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="14" :offset="6">
            <el-form-item label="内容" prop="text" class="coption">
              <el-select v-model="emailForm.text">
                <el-option
                    v-for="item in contentOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="14" :offset="6">
            <el-form-item label="发送邮箱账号" prop="from" class="coption" >
              <el-input v-model="emailForm.from" disabled="disabled" style="align-self: center; width: 50%"  placeholder="2963624004@qq.com"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="14" :offset="6">
            <el-form-item label="接受邮箱账号" prop="to" class="coption" :rules="[
                { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]">
              <el-input v-model="emailForm.to" style="align-self: center; width: 50%"  clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="14" :offset="4">
            <el-form-item>
              <el-button type="primary" @click="submitForm()">发送</el-button>
              <el-button @click="resetForm()">重置</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </el-form>

  </div>


</template>

<script>
export default {
  name: "WeakWarning",
  data() {
    return {

      isCollapse: true,
      emailForm: {
        subject:'',
        text:'',
        to:'',
        from:'',
      },

      emailRules: {
        password: [
          {required: true, message: '请输入正确的密码', trigger: 'blur'},
        ]
      },
      contentOptions: [{
        value: '报警信息1',
        label: '报警信息1'
      }, {
        value: '报警信息2',
        label: '报警信息2'
      }, {
        value: '报警信息3',
        label: '报警信息3'
      }, {
        value: '报警信息4',
        label: '报警信息4'
      }, {
        value: '报警信息5',
        label: '报警信息5'
      }]
    };
  },
  methods:{
    async submitForm() {
        const {data:res} = await this.$http.post('http://192.168.43.73:9003/weakWarning',this.emailForm)  //将前端数据返回给后台，并且得到后台相应
        if( res.code == 200){
          this.$message.success(res.msg)  //发送成功
          this.resetForm() //发送完清空表单
        }else{
          this.$message.error(res.msg)  //发送失败
        }
      },
    resetForm() {
      this.$refs['emailForm'].resetFields();
    },
  }
}
</script>

<style lang="less" scoped>
.home-container{
  height: 100%;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}
.aside-container{
  width: 200px;
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
.coption /deep/ .el-input__inner {
  width: 522px;
  height: 50px;
  font-size: 24px;
}
.fsize /deep/ .el-form-item__label{
  font-size: 24px;
}
img{
  width: 55px;
  height: 55px;
}

</style>
