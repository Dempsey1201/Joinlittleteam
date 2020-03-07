import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../teacherApp'
import individual from "../components/individual/individual";
import yoursClass from "../components/yoursClass/yoursClass";
import publishExam from "../components/publishExam/publishExam";
import stuGrade from "../components/stuGrade/stuGrade";
Vue.use(VueRouter)

const routes = [
	{
		path: '/teacher.html',
        redirect:"/teacher.html/individual"
	},
	{
		path: '/teacher.html/individual',
		component:individual
	},
	{
		path: '/teacher.html/yoursClass',
		component:yoursClass
	},
	{
		path: "/teacher.html/publishExam",
		component:publishExam
	},
	{
		path: "/teacher.html/stuGrade",
		component:stuGrade
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})

export default router
