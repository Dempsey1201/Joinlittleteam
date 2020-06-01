import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../App'
import personal from '../components/personal.vue'
import loginApp from "../../../component/login/loginApp";
import login from "../../../component/login/components/login/login";
import register from "../../../component/login/components/register/register";

Vue.use(VueRouter)

const routes = [
  {
    path: '/student.html',
    redirect: '/student.html/login'
  },
  {
		path: '/student.html/login',
		component: loginApp,
		children: [
			{
				path: '/student.html/login',
				redirect:'/student.html/login/login'
			},
			{
				path: '/student.html/login/login',
				component: login
			},
			{
				path: '/student.html/login/register',
				component: register
			}
		]
	},
  {
    path: '/student.html/home',
    name: 'Home',
    component: () => import("../views/main.vue"),
    children:[
      {
        path: '/student.html/home',
        name: 'Home',
        component: () => import("../components/home.vue"),
          meta:{
              needLogin:true
          }
      },
      {
        path: '/student.html/personal',
        name: 'personal',
        component: personal,
          meta:{
              needLogin:true
          },
        children: [
          {
            path: '/student.html/personal',
            meta:{name: "我的信息",icon: "el-icon-user",needLogin:true},
            redirect:'/student.html/personal/profile'
          },
          {
            path: '/student.html/personal/profile',
            meta:{name: "我的信息",icon: "el-icon-user",needLogin:true},
            component: () => import("../views/profile.vue")
          },
          {
            path: '/student.html/personal/myGrade',
            meta:{name: "我的班级",icon: "el-icon-school",needLogin:true},
            component: () => import("../views/myGrade.vue")
          },
          {
            path: '/student.html/personal/score',
            meta:{name: "我的成绩",icon: "el-icon-bank-card",needLogin:true},
            component: () => import("../views/score.vue")
          },
          {
            path: '/student.html/personal/myGrade/myPaper',
            name: "paper",
            component: () => import("../views/paper.vue"),
              meta:{
                  needLogin:true
              }
          }
        ],
      },
      {
        path:'/student.html/detailPaper',
        name:'detailPaper',
        component: () => import("../views/detailPaper.vue"),
        meta:{
            needLogin:true
        }

      }
    ]
  },

]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


router.beforeEach(
    (to, from, next) =>{
    if(to.meta.needLogin){// 需要登录
        if(!JSON.parse(sessionStorage.getItem("studentInfo"))||!JSON.parse(sessionStorage.getItem("studentInfo")).username){
            // 老师没有登陆
            next({
                path:'/student.html/login'
            })
        }else {
            next();
        }
    }else {
        next();
    }
})


export default router
