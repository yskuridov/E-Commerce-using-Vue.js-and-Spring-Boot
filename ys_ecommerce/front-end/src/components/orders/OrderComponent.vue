<template lang="">
    <b-tab :title="order.deliveryDate">
        <b-card-text>
            <OrderItem v-for="item in order.items" :item="item" :key="item.id"></OrderItem>
            <div class="mt-3">
                <p>Order date: {{order.orderDate}}</p>
                <p>Delivery date: {{order.deliveryDate}}</p>
                <p>Order total: {{order.total}}$</p>
                <p class="text-success" v-if="this.hasBeenDelivered">Item has been delivered</p>
                <p class="text-danger" v-if="!this.hasBeenDelivered">Item has not been delivered yet</p>
            </div>
        </b-card-text>
    </b-tab>
</template>
<script>
import OrderItem from './OrderItem.vue';

export default {
    name: "OrderComponent",
    components: {OrderItem},
    props: ['order'],
    data(){
        return{
            hasBeenDelivered: false
        }
    },
    methods: {
        isDelivered(){
            if(this.order.deliveryDate.split < this.order.orderDate.split){
                this.hasBeenDelivered = true;
            }
            else{
                this.hasBeenDelivered = false;
            }
        }
    }
}
</script>
<style lang="">
    
</style>