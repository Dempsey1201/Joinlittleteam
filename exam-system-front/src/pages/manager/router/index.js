import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../App'
import uploadExcel from '../view/uploadExcel.vue';
import deleteTeacher from '../view/deleteTeacher.vue';
import deleteClass from '../view/deleteClass.vue';
import deletePaper from '../view/deletePaper.vue';
import deleteStu from '../view/deleteStu.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/manager.html',
   redirect: '/manager.html/uploadExcel'
  },
  {
    path: '/manager.html/deleteClass/deleteStu',
    name:'stu',
    component:deleteStu
  },
  {
    path: '/manager.html/uploadExcel',
    meta:{
			icon:"el-icon-document",
			text:"导入名单"
		},
		component:uploadExcel
  },
  {
    path: '/manager.html/deleteTeacher',
    meta:{
			icon:"el-icon-s-custom",
			text:"教师管理"
		},
		component:deleteTeacher
  },
  {
    path: '/manager.html/deleteClass',
    meta:{
			icon:"el-icon-document-add",
			text:"班级管理"
		},
    component:deleteClass
  },
  {
    path: '/manager.html/deletePaper',
    meta:{
			icon:"el-icon-c-scale-to-original",
			text:"试卷管理"
		},
    component:deletePaper,
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
