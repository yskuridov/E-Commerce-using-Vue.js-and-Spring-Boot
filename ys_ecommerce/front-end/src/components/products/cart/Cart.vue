<template lang="">
    <div class="mt-5">
        <h3>Cart</h3>
        <h3 v-if="cartIsEmpty()" class="text-success">Your cart is empty!</h3>
        <div class="container">
            <b-list-group v-for="item in this.userStore.items" :key="item.id">
                <CartItem :item=item></CartItem>
            </b-list-group>
            <div v-if="!cartIsEmpty()">
                <p>
                    Cart total: {{getCartTotal}}$
                </p>
                <b-button @click="this.checkout" variant="dark" class="mb-2">
                    <b-icon icon="currency-dollar" aria-hidden="true" variant="success" class="p-1"></b-icon>
                    Checkout
                </b-button>
                <p v-if="this.insufficientFunds" class="text-danger">Insufficient funds, please remove some items from the cart</p>
            </div>
        </div>
    </div>
</template>
<script>
import CartItem from './CartItem.vue'
import { useUserStore } from '@/stores/UserStore';
import { useProductStore } from '@/stores/ProductStore';
import { useOrderStore } from '@/stores/OrderStore'
export default {
    components: {CartItem},
    setup() {
          const userStore = useUserStore();
          const productStore = useProductStore();
          const orderStore = useOrderStore();
          return { userStore, productStore, orderStore }
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
        },
        checkout(){
            if(this.getCartTotal > this.userStore.loggedInUser.balance){
                this.insufficientFunds = true;
            }
            else {
                this.$router.push('orders/new');
                this.userStore.loggedInUser.balance -= this.getCartTotal
                this.orderStore.orders.push({
                    buyer: this.userStore.loggedInUser.id,
                    items: this.userStore.items,
                    orderDate: this.getOrderDate(),
                    deliveryDate: this.getDeliveryDate(),
                    total: this.getCartTotal
                })
                this.userStore.items = [];
            }
        },
        getOrderDate(){
            const current = new Date();
            const date = `${current.getDate()}/${current.getMonth()}/${current.getFullYear()}`;
            return date;
        },
        getDeliveryDate() {
            const current = new Date();
            const date = `${current.getDate() + 7}/${current.getMonth()}/${current.getFullYear()}`;
            return date;
        }
    },
    data(){
        return{
            insufficientFunds: false
        }
    },
    name: 'CartComponent',
}
</script>
<style lang="">
    
</style>