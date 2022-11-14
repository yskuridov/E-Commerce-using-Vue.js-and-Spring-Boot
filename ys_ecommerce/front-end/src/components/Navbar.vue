<template>
    <div>
        <b-navbar type="dark" variant="dark">
            <div class="d-flex flex-row w-100 justify-content-start">
                <b-navbar-brand href="#">
                    <b-icon icon="list-nested" aria-hidden="true"></b-icon>
                    Seller
                </b-navbar-brand>
                <b-navbar-nav>
                    <b-nav-item><router-link to="/products">Products</router-link></b-nav-item>
                    <b-nav-item v-if="this.userStore.isLoggedIn && this.userStore.isVendor">My Profile</b-nav-item>
                    <b-nav-item v-if="this.userStore.isLoggedIn && !this.userStore.isVendor"><router-link to="/cart">My cart</router-link></b-nav-item>
                    <b-nav-item v-if="!this.userStore.isLoggedIn">
                        <router-link to="/login">Login</router-link>
                    </b-nav-item>
                </b-navbar-nav>
            </div>
            <div v-if="this.userStore.isLoggedIn" class="d-flex text-warning">
                Welcome, {{this.userStore.loggedInUser.firstName}}
                <b-button @click="this.userStore.logout">
                    <b-icon icon="door-open" aria-hidden="true">Disconnect</b-icon>
                </b-button>
            </div>
        </b-navbar>
    </div>
</template>
<script>
import { useUserStore } from '../stores/UserStore'

export default {
    name: 'NavBar',
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
}
</script>
<style lang="">
    
</style>