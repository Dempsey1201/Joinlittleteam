import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//引用 element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';//样式文件单独引入
Vue.use(ElementUI);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
