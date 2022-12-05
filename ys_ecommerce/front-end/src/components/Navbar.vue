<template>
    <div>
        <b-navbar type="dark" variant="dark">
            <div class="d-flex flex-row w-100 justify-content-start">
                <b-navbar-brand href="#">
                    <b-icon icon="list-nested" aria-hidden="true"></b-icon>
                    Seller
                </b-navbar-brand>
                <b-navbar-nav>
                    <b-nav-item v-if="isCustomer">
                        <router-link to="/products">Products</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="isVendor">
                        <router-link :to="'/vendors/' + this.userStore.loggedInUser.username">My Profile</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="isCustomer">
                        <router-link to="/cart">My cart</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="isCustomer">
                        <router-link to="/orders">My orders</router-link>
                    </b-nav-item>
                    <b-nav-item v-if="!isLoggedIn">
                        <router-link to="/login">Login</router-link>
                    </b-nav-item>
                </b-navbar-nav>
            </div>
            <div v-if="isLoggedIn" class="d-flex text-warning">
                <p v-if="isCustomer">Balance: {{this.userStore.loggedInUser.balance}}$</p>
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
    computed: {
        isVendor: function() {
            if(this.isLoggedIn && this.userStore.isVendor) return true;
            else return false;
        },
        isCustomer: function(){
            if(this.isLoggedIn && !this.userStore.isVendor) return true;
            else return false;
        },
        isLoggedIn: function(){
            if(this.userStore.isLoggedIn) return true;
            else return false;
        }
    }

}
</script>
<style lang="">
    
</style>