<template lang="">
    <div class="mt-5">
        <h3>Cart</h3>
        <h3 v-if="cartIsEmpty()" class="text-success">Your cart is empty!</h3>
        <div class="container">
            <b-list-group v-for="item in this.userStore.items" :key="item.id">
                <CartItem :item=item></CartItem>
            </b-list-group>
            <p v-if="!cartIsEmpty()">Cart total: {{getCartTotal}}$</p>
        </div>
    </div>
</template>
<script>
import CartItem from '../CartItem.vue'
import { useUserStore } from '@/stores/UserStore';

export default {
    components: {CartItem},
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
    computed: {
        getCartTotal(){
            let total = 0;
            this.userStore.items.forEach((item) => {
                total += parseInt(item.price) + parseInt(item.deliveryFee)
            })
            return total;
        }
    },
    methods: {
        cartIsEmpty(){
            if(this.userStore.items.length >= 1) return false;
            return true;
        }
    },
    name: 'CartComponent',
}
</script>
<style lang="">
    
</style>