<template lang="">
    <b-tab :title="item.name">
        <b-card-text>
            <div v-if="!this.inEditMode">
                <img :src="item.imageUrl" alt="Product image" width="150" height="150" class="mb-4 p-1 border border-dark">
                <p>Name: {{item.name}}</p>
                <p>Description: {{item.description}}</p>
                <p>Price: {{item.price}}$</p>
                <p>Delivery fee: {{item.deliveryFee}}$</p>
                <p>Stock: {{item.stock}}</p>
            </div>
            <ProductForm :item="item" :toggle-parent-mode="this.toggleEdit" v-if="this.inEditMode"></ProductForm>
        </b-card-text>
        <b-button v-if="!inEditMode && this.userStore.isLoggedIn" @click="this.toggleEdit" href="#" variant='dark'>
            Edit product
            <b-icon icon="wrench" aria-hidden="true" variant="success" class="p-1"></b-icon>
        </b-button>
    </b-tab>
</template>
<script>
import ProductForm from './ProductForm.vue';
import { useUserStore } from '@/stores/UserStore';
export default {
    name: "OwnedProduct",
    props:['item'],
    components: {ProductForm},
    setup() {
        const userStore = useUserStore();
        return { userStore }
    },
    data() {
        return{
            inEditMode: false,
        }
    },
    methods: {
       toggleEdit(){
            if(this.inEditMode == true) this.inEditMode = false;
            else this.inEditMode = true;
        },
    }
}
</script>
<style lang="">
    
</style>