import CustomerService from '@/services/CustomerService'
import VendorService from '@/services/VendorService'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('userStore', {
    state: () => ({ loggedInUser: {}, isLoggedIn: false, isVendor: false, items: []}), //if isVendor, items are the products he has for sale, if !isVendor, items are in his cart
        actions: {
            async login(user){
                if(user != null){
                    this.loggedInUser = user
                    this.isLoggedIn = true
                    this.isVendor = user.vendor
                    await this.setItemsList();
                    console.log(this.items)
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
                if(this.isVendor) this.items = JSON.parse(JSON.stringify(await VendorService.getVendorInventory(this.loggedInUser.id)))
                else this.items = JSON.parse(JSON.stringify(await CustomerService.getCartItems()))
            }
        },
})