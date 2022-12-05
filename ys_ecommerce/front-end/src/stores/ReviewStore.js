
import VendorService from '@/services/VendorService'
import { defineStore } from 'pinia'


export const useReviewStore = defineStore('reviewStore', {
    state: () => ({ reviews: []}),
        actions: {
            addReview(review){
                this.reviews.push(review);
            },
            async setReviewsList(username){
                this.reviews = JSON.parse(JSON.stringify(await VendorService.getReviews(username)));
            }
        },
})