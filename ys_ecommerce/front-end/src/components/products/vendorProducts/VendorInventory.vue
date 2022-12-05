<template lang="">
    <div>
        <h4 class="m-3">Products</h4>
        <div class="container">
            <div>
                <b-card no-body class="mb-4">
                    <b-button variant='dark' class="m-3" @click="toggleAddMode()" v-if="!this.inAddMode && this.userStore.isLoggedIn">
                        New product
                        <b-icon icon="plus" aria-hidden="true" variant="success"></b-icon>
                    </b-button>
                    <b-tabs pills card vertical v-if="!this.inAddMode">
                        <OwnedProduct v-for="item in this.productStore.items" :item=item :key="item.id"></OwnedProduct>
                    </b-tabs>
                    <div v-if="this.inAddMode" class="bg-light m-2 p-3">
                        <h5>New product</h5>
                        <ProductForm :item="{}" :toggle-parent-mode="this.toggleAddMode"></ProductForm>
                    </div>
                </b-card>
            </div>
        </div>
    </div>
</template>
<script>
import OwnedProduct from './VendorProduct.vue';
import { useUserStore } from '@/stores/UserStore';
import { useProductStore } from '@/stores/ProductStore'
import ProductForm from './ProductForm.vue';
export default {
    name: "ProductsInventory",
    components: {OwnedProduct, ProductForm},
    setup() {
          const userStore = useUserStore();
          const productStore = useProductStore();
          return { userStore, productStore }
    },
    data() {
        return{
            inAddMode: false,
        }
    },
    methods:{
        toggleAddMode(){
            if(this.inAddMode) this.inAddMode = false;
            else this.inAddMode = true;
        },
    },
}
</script>
<style lang="">
    
</style>