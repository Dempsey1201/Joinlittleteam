import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../App'
import uploadExcel from '../view/uploadExcel.vue';
import deleteTeacher from '../view/deleteTeacher.vue';
import deleteClass from '../view/deleteClass.vue';

Vue.use(VueRouter)

const routes = [
    {
        path: '/manager.html',
        redirect: '/manager.html/uploadExcel'
    },
    {
        path: '/manager.html/uploadExcel',
        meta: {
            icon: "el-icon-document",
            text: "导入名单"
        },
        component: uploadExcel
    },
    {
        path: '/manager.html/deleteTeacher',
        meta: {
            icon: "el-icon-s-custom",
            text: "教师管理"
        },
        component: deleteTeacher
    },
    {
        path: '/manager.html/deleteClass',
        meta: {
            icon: "el-icon-document-add",
            text: "班级管理"
        },
        component: deleteClass
    }
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

export default router
