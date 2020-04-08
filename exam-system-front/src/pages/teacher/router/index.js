import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../teacherApp'
import individual from "../views/individual/individual";
import yoursClass from "../views/yoursClass/yoursClass";
import publishExam from "../views/publishExam/a_ExamManage";
import stuGrade from "../views/stuGrade/stuGrade";
Vue.use(VueRouter)

const routes = [
	{
		path: '/teacher.html',
        redirect:"/teacher.html/individual"
	},
	{
		path: '/teacher.html/individual',
		meta:{
			icon:"el-icon-s-custom",
			text:"个人中心"
		},
		component:individual
	},
	{
		path: '/teacher.html/yoursClass',
		meta:{
			icon:"el-icon-s-grid",
			text:"班级管理"
		},
		component:yoursClass
	},
	{
		path: "/teacher.html/publishExam",
		meta:{
			icon:"el-icon-edit-outline",
			text:"试卷管理"
		},
		component:publishExam
	},
	{
		path: "/teacher.html/stuGrade",
		meta:{
			icon:"el-icon-document",
			text:"成绩管理"
		},
		component:stuGrade
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

export default router
