<template lang="">
    <b-tab :title="order.deliveryDate">
        <b-card-text>
            <OrderItem v-for="item in order.items" :item="item" :key="item.id"></OrderItem>
            <div class="mt-3">
                <p>Order date: {{order.orderDate}}</p>
                <p>Delivery date: {{order.deliveryDate}}</p>
                <p>Order total: {{order.total}}$</p>
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
}
</script>
<style lang="">
    
</style>