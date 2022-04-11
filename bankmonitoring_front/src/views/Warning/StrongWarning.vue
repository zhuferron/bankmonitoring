<template>
  <div>
    <el-form ref="StrongWarningForm" :model="StrongWarningForm" label-width="160px" label-position="left">
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="14" :offset="6">
            <el-form-item label="时间">
              <el-slider v-model="StrongWarningForm.time" show-input show-stops :max="60":marks="StrongWarningForm.times" style="width: 40%"></el-slider>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="6" :offset="6">
            <el-form-item label="数据库">
              <el-switch
                  v-model="StrongWarningForm.dbState"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="日志">
              <el-switch
                  v-model="StrongWarningForm.logState"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
              </el-switch>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="6" :offset="6">
            <el-form-item label="进程">
              <el-switch
                  v-model="StrongWarningForm.processState"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
              </el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="主机">
              <el-switch v-model="StrongWarningForm.hostState"@change="show" active-color="#13ce66" inactive-color="#ff4949"></el-switch>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center" v-show="isShow">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="6" :offset="6">
            <el-form-item label="磁盘">
              <el-slider v-model="StrongWarningForm.desk" range show-stops :max="60":marks="disks" style="width: 40%"></el-slider>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="Cpu">
              <el-slider v-model="StrongWarningForm.cpu" range show-stops :max="80":marks="cpus" style="width: 40%"></el-slider>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="6" :offset="6">
            <el-form-item label="内存">
              <el-slider v-model="StrongWarningForm.mem" range show-stops :max="80":marks="mems" style="width: 40%"></el-slider>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="一分钟负载">
              <el-slider v-model="StrongWarningForm.oneLoad" range show-stops :max="10":marks="loads" style="width: 40%"></el-slider>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="6" :offset="6">
            <el-form-item label="五分钟负载">
              <el-slider v-model="StrongWarningForm.fiveLoad" range show-stops :max="10":marks="loads" style="width: 40%"></el-slider>
            </el-form-item>
          </el-col>
          <el-col :span="6">
            <el-form-item label="十五分钟负载">
              <el-slider v-model="StrongWarningForm.fifteenLoad" range show-stops :max="10":marks="loads" style="width: 40%"></el-slider>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center">
        <el-row :gutter="20" style="padding-top: 50px">
          <el-col :span="12" :offset="6">
            <el-form-item label="接受邮箱账号" prop="to" :rules="[{ type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }]">
              <el-input v-model="StrongWarningForm.to" style="align-self: center; width: 60%"  clearable></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
      <div align="center" style="padding-top: 30px">
        <el-form-item>
          <el-button type="primary" @click="submitForm()">发送</el-button>
          <el-button @click="resetForm()">重置</el-button>
        </el-form-item>
      </div>
    </el-form>
  </div>

</template>

<script>
export default {
  name: "StrongWarning",
  data() {
    return {
      isShow:false,
      StrongWarningForm: {
        time: 15,
        dbState: false,
        logState: false,
        processState: false,
        hostState: false,
        desk:[10,30],
        cpu:[10,75],
        mem:[20,60],
        oneLoad:[2,5],
        fiveLoad:[2,5],
        fifteenLoad:[2,5],
        to:''
      },
      times: {
        0: '0min',
        5: '5min',
        10: '10min',
        15:'15min',
        20: '20min',
      },
      disks: {
        0: '0%',
        20: '20%',
        40: '40%',
        60:'60%',
      },
      cpus:{
        0: '0%',
        20: '20%',
        40: '40%',
        60:'60%',
      },
      mems:{
        0: '0%',
        20: '20%',
        40: '40%',
        60:'60%',
      },
      loads:{
        0: '0',
        2: '2',
        4: '4',
        6:'6',
      }
    }
  },
  methods:{
    show(){
      this.isShow = !this.isShow
    },
    async submitForm() {
      const {data:res} = await this.$http.post('http://192.168.43.73:9003/strongWarning',this.StrongWarningForm)  //将前端数据返回给后台，并且得到后台相应
      if( res.code == 200){
        this.$message.success(res.msg)  //发送成功
        this.resetForm() //发送完清空表单
      }else{
        this.$message.error(res.msg)  //发送失败
      }
    },
    resetForm() {
      this.$refs['StrongWarningForm'].resetFields();
    },

  }
}
</script>

<style scoped>

</style>