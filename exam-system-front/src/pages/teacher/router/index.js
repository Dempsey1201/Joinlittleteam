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

		children: [
			{
				path: '/teacher.html/home',
				redirect: '/teacher.html/home/individual'
			},
			{
				path: '/teacher.html/home/individual',
				meta:{
					icon:"el-icon-s-custom",
					text:"个人中心"
				},
				component:individual
			},
			{
				path: '/teacher.html/home/yoursClass',
				meta:{
					icon:"el-icon-s-grid",
					text:"班级管理"
				},
				component:yoursClass
			},
			{
				path: "/teacher.html/home/publishExam",
				meta:{
					icon:"el-icon-edit-outline",
					text:"试卷管理"
				},
				component:publishExam
			},
			{
				path: "/teacher.html/home/stuGrade",
				meta:{
					icon:"el-icon-document",
					text:"成绩管理"
				},
				component:stuGrade,
				children:[
					{
						path:"/teacher.html/home/stuGrade",
						component:()=>import("../views/stuGrade/showClass"),
						name:"showClass"
					},
					{
						path:"/teacher.html/home/stuGrade/showClass",
						component:()=>import("../views/stuGrade/showClass"),
						name:"showClass"
					},
					{
						path:"/teacher.html/home/stuGrade/showPaper",
						component:()=>import("../views/stuGrade/showPaper"),
						name:"showPaper"
					},
					{
						path:"/teacher.html/home/stuGrade/showStudent",
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



export default router
