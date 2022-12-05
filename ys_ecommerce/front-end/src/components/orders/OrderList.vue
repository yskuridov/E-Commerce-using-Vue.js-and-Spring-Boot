<template lang="">
    <div class="m-5">
        <h4>My orders</h4>
        <div>
            <b-card no-body class="mb-4">
                <b-tabs pills card vertical>
                    <OrderComponent v-for="order in this.orderStore.orders" :key="order.id" :order='order'></OrderComponent>
                </b-tabs>
            </b-card>
        </div>
    </div>
</template>
<script>
import OrderComponent from './OrderComponent.vue'
import { useOrderStore } from '@/stores/OrderStore'
import { useUserStore } from '@/stores/UserStore';
export default {
    name: 'OrdersComponent',
    components: {OrderComponent},
    data(){
        return{

        }
    },
    methods: {

    },
    setup() {
          const orderStore = useOrderStore();
          const userStore = useUserStore();
          return { orderStore, userStore }
    },
}
</script>
<style lang="">
    
</style>