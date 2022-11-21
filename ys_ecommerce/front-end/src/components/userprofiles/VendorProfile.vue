<template lang="">
    <div class="container-flex">
        <h2 class="m-4">{{ $route.params.username }}'s profile</h2>
        <div class="row">
            <div class="col border border-dark">

            </div>
            <div class="col border border-warning">
                <ProductsInventory></ProductsInventory>
            </div>
        </div>
    </div>
</template>
<script>
import ProductsInventory from '../products/ParentComponents/ProductsInventory.vue';
export default {
    name: "VendorProfile",
    components: {ProductsInventory},

}
</script>
<style lang="">
    
</style>