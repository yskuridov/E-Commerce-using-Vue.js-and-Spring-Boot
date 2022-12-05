import CustomerService from '@/services/CustomerService'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('userStore', {
    setup() {
        const userStore = useUserStore();
        return { userStore }
    },
    state: () => ({ loggedInUser: {}, isLoggedIn: false, isVendor: false, items: []}), //if isVendor, items are the products he has for sale, if !isVendor, items are in his cart
        actions: {
            async login(user){
                if(user != null){
                    this.loggedInUser = user
                    this.isLoggedIn = true
                    this.isVendor = user.vendor
                    await this.setItemsList();
                }
                else {
                    throw new Error("Incorrect username or password")
                }
            },
            logout() {
                this.loggedInUser = {}
                this.isLoggedIn = false
                this.items = []
                this.isVendor = false
            },
            async setItemsList(){
                if(!this.isVendor && this.isLoggedIn) this.items = JSON.parse(JSON.stringify(await CustomerService.getCartItems(this.loggedInUser.id)))
            }
            
        },
})