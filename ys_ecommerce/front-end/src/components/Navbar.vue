<template>
    <div>
        <b-navbar type="dark" variant="dark">
            <div class="d-flex flex-row w-100 justify-content-start">
                <b-navbar-brand href="#">
                    <b-icon icon="list-nested" aria-hidden="true"></b-icon>
                    Seller
                </b-navbar-brand>
                <b-navbar-nav>
                    <b-nav-item v-if="isVendor()">
                        <router-link to="/products">Products</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="isVendor()">
                        <router-link>My Profile</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="isCustomer()">
                        <router-link to="/cart">My cart</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="!isLoggedIn()">
                        <router-link to="/login">Login</router-link>
                    </b-nav-item>
                </b-navbar-nav>
            </div>
            <div v-if="isLoggedIn()" class="d-flex text-warning">
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
    methods:{
        isVendor(){
            if(this.isLoggedIn() && this.userStore.isVendor) return true;
            else return false;
        },
        isCustomer(){
            if(this.isLoggedIn() && !this.userStore.isVendor) return true;
            else return false;
        },
        isLoggedIn(){
            if(this.userStore.isLoggedIn) return true;
            else return false;
        }
    }

}
</script>
<style lang="">
    
</style>