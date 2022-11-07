import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ProductsComponent from './components/products/ProductsComponent.vue'
import LoginComponent from './components/usermanagement/LoginComponent.vue'
import RegisterComponent from './components/usermanagement/RegisterComponent.vue'
import HomePage from './components/HomePage.vue'

Vue.use(VueRouter)
Vue.prototype.$loggedInUser = {value: null}

const routes = [
  {path: '/products', component: ProductsComponent},
  {path: '/login', component: LoginComponent},
  {path: '/register', component: RegisterComponent},
  {path: '/', component: HomePage}
]
const router = new VueRouter({
  routes,
})


Vue.config.productionTip = false
new Vue({
  router,
  render: h => h(App),
}).$mount('#app')

