import Vue from 'vue'
import loginApp from './loginApp.vue'
import router from './router'
import store from '../../store'
//引用 element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';//样式文件单独引入
Vue.use(ElementUI);
import axios from "axios";
import vueAxios from "vue-axios";
Vue.use(vueAxios,axios);
axios.defaults.baseURL = "http://47.94.210.131:8080";

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(loginApp)
}).$mount('#app')
