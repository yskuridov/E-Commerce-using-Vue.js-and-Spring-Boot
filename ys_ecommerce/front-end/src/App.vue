<template>
  <div id="app" class="m-0">
    <NavBar />
    <router-view></router-view>
  </div>
</template>

<script>

import Vue from 'vue'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import NavBar from './components/Navbar.vue'

Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

export default {
  name: 'App',
  components: {
    NavBar,
},
data() {
  return {
    
  }
}
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
