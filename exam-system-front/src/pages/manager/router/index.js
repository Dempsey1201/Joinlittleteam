import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../App'
import uploadExcel from '../view/uploadExcel.vue';
import deleteTeacher from '../view/deleteTeacher.vue';
import deleteClass from '../view/deleteClass.vue';
import deleteStu from '../view/deleteStu.vue'
import deletePaper from '../view/deletePaper.vue'
import Home from "../components/Home";
Vue.use(VueRouter)

const routes = [
    {
        path:"/manager.html",
        meta: {
            needLogin:true
        },
        redirect:"/manager.html/home"
    },
    {
        path: "/manager.html/home",
        component: Home,
        children:[
            {
                path: '/manager.html/home/',
                meta: {
                    needLogin:true
                },
                redirect: '/manager.html/home/uploadExcel',
            },
            {
                path: '/manager.html/home/deleteStu',
                name:'stu',
                component: deleteStu
            },
            {
                path: '/manager.html/home/uploadExcel',
                meta: {
                    icon: "el-icon-document",
                    text: "导入名单",
                    needLogin:true
                },
                component: uploadExcel
            },
            {
                path: '/manager.html/home/deleteTeacher',
                meta: {
                    icon: "el-icon-s-custom",
                    text: "教师管理",
                    needLogin:true
                },
                component: deleteTeacher
            },
            {
                path: '/manager.html/home/deleteClass',
                meta: {
                    icon: "el-icon-document-add",
                    text: "班级管理",
                    needLogin:true
                },
                component: deleteClass
            },
            {
                path: '/manager.html/home/deletePaper',
                meta: {
                    icon: "el-icon-c-scale-to-original",
                    text: "试卷管理",
                    needLogin:true
                },
                component: deletePaper
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) =>{
    if(to.meta.needLogin){// 需要登录
        if(!JSON.parse(sessionStorage.getItem("userInfo"))||!JSON.parse(sessionStorage.getItem("userInfo")).adminname){
            // 老师没有登陆
            window.location = window.location.origin+"/managerLogin.html"
        }else {
            next();
        }
    }else {
        next();
    }
})


export default router
