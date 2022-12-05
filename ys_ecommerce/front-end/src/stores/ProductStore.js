
import VendorService from '@/services/VendorService'
import { defineStore } from 'pinia'


export const useProductStore = defineStore('productStore', {
    state: () => ({ items: []}),
        actions: {
            async getInventory(username){
                this.items = JSON.parse(JSON.stringify(await VendorService.getVendorInventory(username)));
            },
            
        },
})