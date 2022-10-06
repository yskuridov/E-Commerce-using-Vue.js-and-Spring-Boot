<template>
    <div>
        <b-navbar type="dark" variant="dark">
            <b-navbar-brand href="#">
              <b-icon icon="list-nested" aria-hidden="true"></b-icon>
              Seller
            </b-navbar-brand>
            <b-nav>
                <b-nav-item>Products</b-nav-item>
                <b-nav-item>Profile</b-nav-item>
            </b-nav>
            <b-navbar-nav class="ml-auto">
                <b-nav-item-dropdown text="Search products by" right>
                    <b-dropdown-item href="#">Product price</b-dropdown-item>
                    <b-dropdown-item href="#">Vendor name</b-dropdown-item>
                    <b-dropdown-item href="#">Delivery fee</b-dropdown-item>
                    <b-dropdown-item href="#">Product description</b-dropdown-item>
                    <b-dropdown-item href="#">Product name</b-dropdown-item>
                </b-nav-item-dropdown>
                <b-nav-form>
                  <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
                  <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
                </b-nav-form>
            </b-navbar-nav>
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