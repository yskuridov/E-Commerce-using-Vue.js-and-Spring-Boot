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
                    <b-nav-item v-if="isLoggedIn">Profile</b-nav-item>
                    <b-nav-item v-if="!isLoggedIn">
                        <router-link to="/login">Login</router-link>
                    </b-nav-item>
                </b-navbar-nav>
            </div>
            <div v-if="isLoggedIn">
                Welcome, {{this.$loggedInUser.value.username}}
                <b-button @click="disconnect">
                    <b-icon icon="door-open" aria-hidden="true">Disconnect</b-icon>
                </b-button>
            </div>
        </b-navbar>
    </div>
</template>
<script>
export default {
    name: 'NavBar',
    methods: { 
        disconnect(){
            this.$loggedInUser.value = null
            this.$forceUpdate();
        },
    },
    computed:{
        isLoggedIn(){
            if(this.$loggedInUser.value != null) return true
            else return false
        }
    }
}
</script>
<style lang="">
    
</style>