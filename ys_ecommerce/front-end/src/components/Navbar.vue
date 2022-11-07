<template>
    <div>
        <b-navbar type="dark" variant="dark">
            <div class="d-flex flex-row w-100 bg-success justify-content-between">
                <b-navbar-brand href="#">
                    <b-icon icon="list-nested" aria-hidden="true"></b-icon>
                    Seller
                </b-navbar-brand>
                <b-navbar-nav>
                    <b-nav-item>Products</b-nav-item>
                    <b-nav-item v-if="this.$loggedInUser.value != null">Profile</b-nav-item>
                    <b-nav-item v-if="this.$loggedInUser.value == null">
                        <router-link to="/login">Login</router-link>
                    </b-nav-item>
                </b-navbar-nav>
            </div>
        </b-navbar>
    </div>
</template>
<script>
export default {
    name: 'NavBar',
}
</script>
<style lang="">
    
</style>