import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../App'
import personal from '../components/personal.vue'
import home from '../components/home.vue'
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
    component: home
  },
  {
    path: '/student.html/personal',
    name: 'personal',
    component: personal,
    children: [
      {
        path: '/student.html/personal',
        meta:{name: "我的信息",icon: "el-icon-user"},
        redirect:'/student.html/personal/profile'
      },
      {
        path: '/student.html/personal/profile',
        meta:{name: "我的信息",icon: "el-icon-user"},
        component: () => import("../views/profile.vue")
      },
      {
        path: '/student.html/personal/myGrade',
        meta:{name: "我的班级",icon: "el-icon-school"},
        component: () => import("../views/myGrade.vue")
      }
    ],
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
