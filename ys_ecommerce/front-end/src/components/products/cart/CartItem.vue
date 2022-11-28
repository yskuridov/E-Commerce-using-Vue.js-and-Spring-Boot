<template lang="">
    <div class="m-2">
        <b-list-group-item class="d-flex justify-content-between align-items-center">
            <img :src="item.image" alt="Product image" width="100" height="100">
            <div>
                <p class="font-weight-bold">{{item.name}}</p>
                <p>Price: {{item.price}}$</p>
                <p>Delivery fee: {{item.deliveryFee}}$</p>
            </div>
            <b-button style="background-color:transparent">
                <b-icon icon="x-lg" aria-hidden="true" variant="danger" @click="removeFromCart"></b-icon>
            </b-button>
        </b-list-group-item>
    </div>
</template>
<script>
import { useUserStore } from '../../../stores/UserStore'

export default {
    name: 'CartItem',
    props:['item'],
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
    methods: {
        removeFromCart(){
            if(this.userStore.items.length > 1) this.userStore.items.splice(this.getIndex(), 1)
            else this.userStore.items = [];
        },
        getIndex(){
            return this.userStore.items.indexOf(this.item)
        }
    }
}
</script>
<style lang="">
    
</style>