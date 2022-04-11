import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'

//引入全局样式
import "./assets/css/global.css"
//引入iconfont
import './assets/font/iconfont.css'
//导入axios
import axios from "axios";
Vue.prototype.$echarts =window.echarts


//挂载axios
Vue.prototype.$http =axios
//设置访问根路径
// axios.defaults.baseURL = "http://localhost:"


Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
