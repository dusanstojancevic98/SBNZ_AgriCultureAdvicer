import Vue from 'vue'
import App from './App.vue'


import store from './store/store'
import vuetify from './plugins/vuetify'
import router from './router/router'

Vue.config.productionTip = false

router.beforeEach((to, from, next)=>{
  if (!localStorage.getItem('jwt') && (to.name!=='Login'&&to.path!=='/login') && (to.name!=='Register'&&to.path!=='/register')) {
    next({ name: 'Login' });
  }else{
    next();
  }
})

new Vue({
  render: h => h(App),
  vuetify,
  router,
  store: store
}).$mount('#app')
