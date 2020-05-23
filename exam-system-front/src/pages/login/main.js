import Vue from 'vue'
import App from './loginApp.vue'
import router from './router'
import store from '../../store'
//引用 element-ui
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';//样式文件单独引入
import 'element-ui/lib/theme-chalk/reset.css';//样式文件单独引入
import 'element-ui/lib/theme-chalk/display.css';
Vue.use(ElementUI);
import axios from "axios";
import vueAxios from "vue-axios";
Vue.use(vueAxios,axios);

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
