import Vue from 'vue'
import VueRouter from 'vue-router'
import loginApp from "../loginApp";
Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: loginApp
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
