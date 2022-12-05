<template lang="">
    <div>
        <h4 class="m-3">My reviews</h4>
        <div class="container">
            <div v-if="this.userStore.isLoggedIn">
                <div class="d-flex justify-content-start">
                    <b-form-checkbox v-model="hasOpenComments" name="check-button" switch>
                      <p v-if="!this.hasOpenComments">{{this.open}}</p>
                      <p v-if="this.hasOpenComments">{{this.closed}}</p>
                    </b-form-checkbox>               
                </div>
                <div v-for="review in this.reviewStore.reviews" :key="review.id">
                    <ReviewComponent :review="review"></ReviewComponent>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
import { useUserStore } from '@/stores/UserStore';
import { ReviewComponent} from './Review.vue'
import { useReviewStore } from '@/stores/ReviewStore'
export default {
    name: 'ReviewsList',
    components: {ReviewComponent},
    setup() {
          const userStore = useUserStore();
          const reviewStore = useReviewStore();
          return { userStore, reviewStore }
    },
    data() {
      return {
        open: "Disable new reviews",
        closed: "Customers can't leave reviews on your profile",
        hasOpenComments: this.userStore.loggedInUser.hasOpenComments,
      }
    },
    mounted: function(){
        this.setReviewsList();
    },
    methods: {
        setReviewsList(){
            console.log(this.userStore.loggedInUser.id)
            this.reviewStore.setReviewsList(this.userStore.loggedInUser.id.toString());
        }
    }
}
</script>
<style lang="">
    
</style>