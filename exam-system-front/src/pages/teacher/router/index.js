import Vue from 'vue'
import VueRouter from 'vue-router'
import individual from "../views/individual/individual";
import yoursClass from "../views/yoursClass/yoursClass";
import publishExam from "../views/publishExam/a_ExamManage";
import stuGrade from "../views/stuGrade/stuGrade";
import loginApp from "../../../component/login/loginApp";
import login from "../../../component/login/components/login/login";
import register from "../../../component/login/components/register/register";
import home from "../components/home";
Vue.use(VueRouter)

const routes = [
	{
		path: '/teacher.html',
        redirect:"/teacher.html/login",
	},
	{
		path: '/teacher.html/login',
		component: loginApp,
		children: [
			{
				path: '/teacher.html/login',
				redirect:'/teacher.html/login/login'
			},
			{
				path: '/teacher.html/login/login',
				component: login
			},
			{
				path: '/teacher.html/login/register',
				component: register
			}
		]
	},
	{
		path: '/teacher.html/home',
		component:home,
		meta:{
			needLogin:true,
		},
		children: [
			{
				path: '/teacher.html/home',
				needLogin:true,
				redirect: '/teacher.html/home/individual'
			},
			{
				path: '/teacher.html/home/individual',
				meta:{
					icon:"el-icon-s-custom",
					text:"个人中心",
					needLogin:true,
				},
				component:individual
			},
			{
				path: '/teacher.html/home/yoursClass',
				meta:{
					icon:"el-icon-s-grid",
					needLogin:true,
					text:"班级管理"
				},
				component:yoursClass
			},
			{
				path: "/teacher.html/home/publishExam",
				meta:{
					icon:"el-icon-edit-outline",
					needLogin:true,
					text:"试卷管理"
				},
				component:publishExam
			},
			{
				path: "/teacher.html/home/stuGrade",
				meta:{
					icon:"el-icon-document",
					text:"成绩管理",
					needLogin:true,
				},
				component:stuGrade,
				children:[
					{
						path:"/teacher.html/home/stuGrade",
						meta:{
							needLogin:true,
						},
						component:()=>import("../views/stuGrade/showClass"),
						name:"showClass"
					},
					{
						path:"/teacher.html/home/stuGrade/showClass",
						meta:{
							needLogin:true,
						},
						component:()=>import("../views/stuGrade/showClass"),
						name:"showClass"
					},
					{
						path:"/teacher.html/home/stuGrade/showPaper",
						meta:{
							needLogin:true,
						},
						component:()=>import("../views/stuGrade/showPaper"),
						name:"showPaper"
					},
					{
						path:"/teacher.html/home/stuGrade/showStudent",
						meta:{
							needLogin:true,
						},
						component:()=>import("../views/stuGrade/showStudent"),
						name:"showStudent"
					},
				]
			}
		]
	}

]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

import store from "../../../store/index"
router.beforeEach((to, from, next) =>{
	console.log(to)
	if(to.meta.needLogin){// 需要登录
		if(!store.getters.userInfo){
			// 没有登陆
			console.log("请登录");
			next({
				path:'/teacher.html/login'

			})
		}else {
			next();
		}
	}else {
		next();
	}
})


export default router
