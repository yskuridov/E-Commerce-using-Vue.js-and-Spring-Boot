import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import ProductsComponent from './components/products/ProductsList.vue'
import LoginComponent from './components/usermanagement/LoginComponent.vue'
import RegisterComponent from './components/usermanagement/RegisterComponent.vue'
import VendorProfile from './components/userprofiles/VendorProfile.vue'
import CartComponent from './components/products/cart/Cart.vue'
import { createPinia, PiniaVuePlugin } from 'pinia'


Vue.use(VueRouter)
Vue.use(PiniaVuePlugin)

const routes = [
  {path: '/products', component: ProductsComponent},
  {path: '/login', component: LoginComponent},
  {path: '/register', component: RegisterComponent},
  {path: '/vendors/:username', component: VendorProfile},
  {path: '/cart', component: CartComponent},
]
const router = new VueRouter({
  routes,
})

const pinia = createPinia()

Vue.config.productionTip = false
new Vue({
  router,
  pinia,
  render: h => h(App),
}).$mount('#app')

