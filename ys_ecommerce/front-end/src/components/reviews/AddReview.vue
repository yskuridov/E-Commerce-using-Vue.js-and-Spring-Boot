<template lang="">
    <div class="mt-5 bg-light mb-0 border border-dark" v-if="this.userStore.isLoggedIn && !this.userStore.isVendor">
        <h5 class="mt-3">Leave a review</h5>
        <b-form class="pr-5 pl-5">
            <b-form-group label="Rating" label-for="rating">
            <b-form-input
                id="rating"
                type="number"
                min="0" max="10"
                placeholder="Rating"
                v-model="rating"
                required
                size="sm"
            ></b-form-input>
            </b-form-group>
            <b-form-group label="Comment" label-for="comment">
                <b-form-textarea
                    id="comment"
                    placeholder="Comment"
                    v-model="comment"
                    rows="3"
                    max-rows="5"
                    size="sm"
                ></b-form-textarea>
            </b-form-group>
            <b-button @click="this.saveReview" variant="dark" class="mb-2">
                <b-icon icon="check" aria-hidden="true" variant="success" class="p-1"></b-icon>
                Save
            </b-button>
        </b-form>
    </div>
</template>
<script>
import { useUserStore } from '@/stores/UserStore';
import { useReviewStore } from '@/stores/ReviewStore';
export default {
    name: 'ReviewForm',
    setup() {
          const userStore = useUserStore();
          const reviewStore = useReviewStore();
          return { userStore, reviewStore }
    },
    data() {
      return {
        rating: '',
        comment: '',
      }
    },
    methods: {
        saveReview(){
            this.reviewStore.addReview({
                rating: this.rating,
                comment: this.comment,
                customer: this.userStore.loggedInUser.id,
                customerName: this.userStore.loggedInUser.username,
                vendor: 2,
                id: this.reviewStore.size
            });
        }
    }
}
</script>
<style lang="">
    
</style>