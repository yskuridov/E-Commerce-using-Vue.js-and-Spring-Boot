<template lang="">
    <div>
        <h4 class="m-3">Reviews</h4>
        <div class="container">
            <div>
                <div class="d-flex justify-content-start" v-if="this.userStore.isVendor">
                    <b-form-checkbox v-model="hasOpenComments" name="check-button" switch>
                      <p v-if="!this.hasOpenComments">{{this.open}}</p>
                      <p v-if="this.hasOpenComments">{{this.closed}}</p>
                    </b-form-checkbox>               
                </div>
                <b-card no-body class="mb-4">
                    <b-tabs pills card vertical>
                        <ReviewComponent v-for="review in this.reviewStore.reviews" :key="review.id" :review="review"></ReviewComponent>
                    </b-tabs>
                    <ReviewForm></ReviewForm>
                </b-card>
            </div>
        </div>
    </div>
</template>
<script>
import { useUserStore } from '@/stores/UserStore';
import  ReviewComponent from './Review.vue'
import ReviewForm from './AddReview.vue'
import { useReviewStore } from '@/stores/ReviewStore'
export default {
    name: 'ReviewsList',
    components: {ReviewComponent, ReviewForm},
    setup() {
          const userStore = useUserStore();
          const reviewStore = useReviewStore();
          return { userStore, reviewStore }
    },
    data() {
      return {
        open: "Disable new reviews",
        closed: "Customers can't leave new reviews on your profile",
        hasOpenComments: this.userStore.loggedInUser.hasOpenComments,
      }
    },
    methods: {
        isCustomer(){
            if(this.userStore.isLoggedIn && !this.userStore.isVendor) return true;
            return false;
        }
    }
}
</script>
<style lang="">
    
</style>