import { defineStore } from 'pinia'

export const useUserStore = defineStore('userStore', {
    state: () => ({ loggedInUser: {}, isLoggedIn: false }), 
        actions: {
            login(user){
                if(user != null){
                    this.loggedInUser = user
                    this.isLoggedIn = true
                }
                else {
                    throw new Error("Incorrect username or password")
                }
            },
            logout() {
                this.loggedInUser = {}
                this.isLoggedIn = false
            },
        },
})