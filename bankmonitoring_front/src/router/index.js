import Vue from 'vue'
import VueRouter from 'vue-router'

import Login from "@/views/User/Login";
import Home from "@/views/Home";
import Info from "@/views/User/Info";
import ResetPassword from "../views/User/ResetPassword";
import Email from "@/views/Warning/Email";
import StrongWarning from "@/views/Warning/StrongWarning";
import WeakWarning from "@/views/Warning/WeakWarning";
import Process from "@/views/System/Process";
import DataBase from "@/views/System/DataBase";
import Log from "@/views/System/Log";
import MemStateByIdAndTime from "@/views/Host/MemStateByIdAndTime";

import TcpStateByIdAndTime from "@/views/Host/TcpStateByIdAndTime";
import DeskStateByIdAndTime from "@/views/Host/DeskStateByIdAndTime";
import CpuStateByIdAndTime from "@/views/Host/CpuStateByIdAndTime";
import DiskUsage from "@/views/Host/DiskUsage";


import Screen from "@/views/Echarts/Screen";
import NetioStateByIdAndTime from "@/views/Host/NetioStateByIdAndTime";
import Echart1Page from "@/views/Echarts/Echart1Page";
import Echart2Page from "@/views/Echarts/Echart2Page";
import Echart3Page from "@/views/Echarts/Echart3Page";
import Echart4Page from "@/views/Echarts/Echart4Page";
import SystemDetail from "@/views/System/SystemDetail";
import System from "@/views/System/System";
import Echart5Page from "@/views/Echarts/Echart5Page";
import Echart6Page from "@/views/Echarts/Echart6Page";
import TimeExceptionPage from "@/views/Echarts/TimeExceptionPage";
import LoadStateByIdAndTime from "@/views/Host/LoadStateByIdAndTime";



Vue.use(VueRouter)

const routes = [
  {
    path: "/",
    redirect: "/login",

  },{
    path: "/login",
    component: Login
  },{
    path: "/home",
    component: Home,
    redirect: "/screen",
    children: [
      {path:"/screen", component:Screen},
      {path:"/system",component:System},
      {path:"/info",component:Info},
      {path:"/resetPassword",component:ResetPassword},
      {path:"/email",
        component: Email,
        children:[
          {path:"/strongWarning",component:StrongWarning},
          {path:"/weakWarning",component:WeakWarning},
        ]},
      {path:"/systemDetail",component: SystemDetail},
      {path:"/process",component: Process},
      {path:"/dataBase",component: DataBase},
      {path:"/log",component: Log},
      {path:"/memStateByIdAndTime",component: MemStateByIdAndTime},
      {path:"/tcpStateByIdAndTime",component: TcpStateByIdAndTime},
      {path:"/deskStateByIdAndTime",component: DeskStateByIdAndTime},
      {path:"/cpuStateByIdAndTime",component: CpuStateByIdAndTime},
      {path:"/netioStateByIdAndTime",component: NetioStateByIdAndTime},
      {path:"/loadStateByIdAndTime",component: LoadStateByIdAndTime},
    ]
  },{
    path:"/diskUsage",
    component: DiskUsage
  },{
    path:"/echart1Page",
    component:Echart1Page
  },{
    path:"/echart2Page",
    component:Echart2Page
  },{
    path:"/echart3Page",
    component: Echart3Page
  },{
    path:"/echart4Page",
    component: Echart4Page
  },{
    path:"/echart5Page",
    component:Echart5Page
  },{
    path:"/echart6Page",
    component:Echart6Page
  },{
    path:"/timeExceptionPage",
    component:TimeExceptionPage
  },

]

const router = new VueRouter({
  routes
})

//?????????????????????????????????
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
  return VueRouterPush.call(this, to).catch(err => err)
}


// ????????????
router.beforeEach((to,from,next) =>{
  //??????????????????????????????????????????
  if(to.path == '/login') return next();
  if(to.path == '/register') return next();
  //??????????????????????????????????????????
  const userFlag=window.sessionStorage.getItem("id");  //??????????????????
  if(!userFlag) return next('/login');  //????????????????????????
  next();  //?????????????????????
})

export default router
