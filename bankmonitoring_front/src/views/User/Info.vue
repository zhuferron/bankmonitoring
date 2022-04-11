<template xmlns="http://www.w3.org/1999/html">
  <div>
<!--    用户头像展示-->
    <div>
<!--      使用post方式，传入file文件和id-->
      <!--          //  下面action处是随便写的地址，到时候上传修改头像信息到哪个地址，就写哪个url-->
<!--      <el-upload-->
<!--          action="/Info/userFace"-->
<!--          :headers="headers"-->
<!--          :data="infoForm"-->
<!--          :on-success="onSuccess"-->
<!--          show-file-list="false">-->
<!--          <img title="点击修改用户头像" src="src/assets/images/background.jpeg" style="height: 100px;width: 100px;border-radius: 50px"-->
<!--               alt="">-->
<!--        <div>-->
<!--          <el-button size="small" type="primary">点击上传</el-button>-->
<!--          <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>-->
<!--        </div>-->
<!--      </el-upload>-->
    </div>
<!--    用户信息展示-->
    <el-form :model="infoForm" :rules="rules" ref="infoFormRef" label-width="100px" class="demo-ruleForm">
      <!--ID -->
      <el-form-item label="ID" prop="name" :span="10" >
        <el-input v-model="infoForm.id"></el-input>
      </el-form-item>
      <!--用户名 -->
      <el-form-item label="用户名" prop="name">
        <el-input v-model="infoForm.username" ></el-input>
      </el-form-item>
      <!--密码 -->
      <el-form-item label="密码" prop="name">
        <el-input v-model="infoForm.password" show-password></el-input>
      </el-form-item>
      <!--email -->
      <el-form-item label="email" prop="name">
        <el-input v-model="infoForm.email"></el-input>
      </el-form-item>
      <!--创建时间 -->
      <el-form-item label="创建时间" required>
        <el-col :span="11">
          <el-form-item prop="date1">
            <el-date-picker type="date" placeholder="选择日期" v-model="infoForm.createTime1" style="width: 100%;" ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item prop="date2">
            <el-time-picker placeholder="选择时间" v-model="infoForm.createTime2" style="width: 100%;" ></el-time-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <!--修改时间 -->
      <el-form-item label="修改时间" required>
        <el-col :span="11">
          <el-form-item prop="date1">
            <el-date-picker type="date" placeholder="选择日期" v-model="infoForm.updateTime1" style="width: 100%;" ></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="11">
          <el-form-item prop="date2">
            <el-time-picker placeholder="选择时间" v-model="infoForm.updateTime2" style="width: 100%;" ></el-time-picker>
          </el-form-item>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="showUpdataInfo">修改</el-button>
      </el-form-item>
    </el-form>
<!--    用户信息展示表单结束-->
<!--    修改用户用户信息，点击修改按钮，弹出一个dialog，-->
    <el-dialog
        title="提示"
        :visible.sync="dialogVisible"
        width="30%">
      <div>
        <table>
          <tr>
            <td>用户姓名</td>
            <td><el-input v-model="infoForm2.username" :rules="rules"></el-input></td>
          </tr>
          <tr>
            <td>用户邮箱</td>
            <td><el-input v-model="infoForm2.email" :rules="rules"></el-input></td>
          </tr>
        </table>
      </div>
      <span slot="footer" class="dialog-footer">
      <el-button @click="dialogVisible = false">取 消</el-button>
      <el-button type="primary" @click="updataInfo">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Info",
  data() {
    return {
      dialogVisible:false,
      headers:{
        //获取请求头部，目前未填
        Authorization:window.sessionStorage.getItem("")
      },
      //表单信息，这是初始表单，用户从后端传输数据
      infoForm: {
        id:'',
        username: '',
        password: '',
        email: '',
        createTime1: '',
        createTime2: '',
        updateTime1: '',
        updateTime2: '',
      },
      //表单信息，这是用于修改用户信息的表单
      infoForm2: {
        id:'',
        username: '',
        password: '',
        email: '',
        createTime1: '',
        createTime2: '',
        updateTime1: '',
        updateTime2: '',
      },
      //表单校验规则
      rules: {
        username: [
          { required: true, message: '用户名称为必填项', trigger: 'blur' }, //必填项验证
          { min: 5, max: 12, message: '长度在 5 到 12 个字符', trigger: 'blur' } //长度验证
        ],
        password: [
          { required: true, message: '用户密码为必填项', trigger: 'blur' }, //必填项验证
          { min: 6, max: 10, message: '长度在 6 到 10 个字符', trigger: 'blur' } //长度验证
        ],
        email: [
          { required: true, message: '邮箱地址为必填项', trigger: 'blur' }, //必填项验证
          { max: 30, message: '长度不能超过 30 个字符', trigger: 'blur' } //长度验证
        ],
      },
      disabled: true,
    };
  },
  created() {
    this.getInfo();
  },

  methods:{
    //修改头像成功后重新获取用户信息
    onSuccess(){
      this.getInfo();
    },
    //获取用户信息的函数
    async getInfo(){
      this.infoForm.id = window.sessionStorage.getItem("id");
      const {data:res} = await this.$http.get('http://192.168.43.73:9001/getInfo/' +this.infoForm.id);
      if(res.code == 200){
        //填充数据
        this.infoForm.id = res.data.id;
        this.infoForm.username = res.data.username;
        this.infoForm.password = res.data.password;
        this.infoForm.email = res.data.email;
        this.infoForm.createTime1 = new Date(res.data.createTime)
        this.infoForm.createTime2 = new Date(res.data.createTime)
        this.infoForm.updateTime1 = new Date(res.data.updateTime)
        this.infoForm.updateTime2 = new Date(res.data.updateTime)
        //以上完成从后台传输数据到网页，以下这行同步用于修改用户数据的备用表单
        this.infoForm2=this.infoForm;
        // this.infoForm=Object.assign({},this.infoForm2);
      }else{
        return this.$message.error(res.msg);
      }
    },
    //点击修改按钮，弹出dialog（改变dialog模块的可视与否属性）
    showUpdataInfo(){
      this.dialogVisible=true;
    },

    async updataInfo(){
      //下面url随便填的，反正就是和更新用户信息的后端接口连上？
      const {data:res} = await this.$http.post('http://192.168.43.73:9001/resetInfo',this.infoForm2);
        if(res.code == 200) {
          this.dialogVisible = false;
          this.$message.success(res.msg);
          this.getInfo();
        }else{
          this.dialogVisible = false;
          this.$message.error(res.msg);
        }
    }
  }
}
</script>

<style scoped>

</style>