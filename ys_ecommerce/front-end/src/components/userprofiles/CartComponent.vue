<template lang="">
    <div>
        <h3 clas="m-5">Cart</h3>
        <div class="row justify-content-center">
            <div v-for="item in this.userStore.items" :key="item.id">
                <CartItem :item=item></CartItem>
            </div>
            <p>Cart total: </p>
        </div>
    </div>
</template>
<script>
import CartItem from './CartItem.vue'
import { useUserStore } from '@/stores/UserStore';

export default {
    components: {CartItem},
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
    name: 'CartComponent',
}
</script>
<style lang="">
    
</style>