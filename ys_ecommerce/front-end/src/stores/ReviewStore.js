
import VendorService from '@/services/VendorService'
import { defineStore } from 'pinia'


export const useReviewStore = defineStore('reviewStore', {
    state: () => ({ reviews: []}),
        actions: {
            async addReview(){
                
            },
            async setReviewsList(id){
                this.reviews = JSON.parse(JSON.stringify(await VendorService.getReviews(id)));
                console.log(this.reviews)
            }
        },
})