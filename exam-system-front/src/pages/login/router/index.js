import Vue from 'vue'
import VueRouter from 'vue-router'
import app from '../loginApp'
import login from "../components/login/login";
import register from "../components/register/register";

Vue.use(VueRouter)

const routes = [
  {
    path: '/login.html',
    redirect:'/login.html/login',
  },
  {
    path: '/login.html/login',
    component: login
  },
  {
    path: '/login.html/register',
    component: register
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
