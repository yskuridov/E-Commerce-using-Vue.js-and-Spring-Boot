<template lang="">
    <div class="container">
        <h2 class="m-4">{{ $route.params.username }}'s profile</h2>
        <div class="row">
            <div class="col border border-dark m-2">
                <ReviewsList></ReviewsList>
            </div>
            <div class="col border border-warning m-2">
                <ProductsInventory></ProductsInventory>
            </div>
        </div>
    </div>
</template>
<script>
import ProductsInventory from '../products/vendorProducts/VendorInventory.vue';
import ReviewsList from '../reviews/ReviewsList.vue';
export default {
    name: "VendorProfile",
    components: {ProductsInventory, ReviewsList},

}
</script>
<style lang="">
    
</style>