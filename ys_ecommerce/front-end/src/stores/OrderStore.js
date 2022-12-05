import { defineStore } from 'pinia'


export const useOrderStore = defineStore('orderStore', {
    state: () => ({ orders: []}),
        actions: {
            setOrders(){
                this.orders = [];
            }
        },
})