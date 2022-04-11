<template>
  <el-container>
    <el-header>
      <el-button-group>
        <el-button type="primary" icon="el-icon-arrow-left" @click="goStrongWarning">强告警页面</el-button>
        <el-button type="primary" @click="goWeakWaring">弱告警页面<i class="el-icon-arrow-right el-icon--right"></i></el-button>
      </el-button-group>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
  </el-container>
</template>



<script>
import Header from "@/components/common/Header";
export default {
  name: "Email",
  components: {Header},
  created() {
    this.goStrongWarning();
  },
  methods: {
    goStrongWarning(){
      this.$router.push("/strongWarning")
    },
    goWeakWaring(){
      this.$router.push("/weakWarning")
    }
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
img{
  width: 55px;
  height: 55px;
}

</style>


