<template lang="">
    <div class="container">
        <h2 class="m-4">{{ $route.params.username }}'s profile</h2>
        <p>
            Average rating: {{getAvgRating}}
            <b-icon icon="star-fill" class="text-warning"></b-icon>
        </p>
        <div class="row">
            <div class="col border border-dark m-2">
                <ReviewsList></ReviewsList>
            </div>
            <div class="col border border-warning m-2">
                <ProductsInventory></ProductsInventory>
            </div>
        </div>
    </div>
</template>
<script>
import ProductsInventory from '../products/vendorProducts/VendorInventory.vue';
import ReviewsList from '../reviews/ReviewsList.vue';
import { useReviewStore } from '@/stores/ReviewStore';
import { useUserStore } from '@/stores/UserStore';
import { useProductStore } from '@/stores/ProductStore';
export default {
    name: "VendorProfile",
    components: {ProductsInventory, ReviewsList},
    mounted: function(){
        this.setReviewsList();
        this.setInventory();
    },
    methods: {
        setReviewsList(){
            this.reviewStore.setReviewsList(this.$router.currentRoute.path.split('/').pop());
        },
        setInventory(){
            this.productStore.getInventory(this.$router.currentRoute.path.split('/').pop())
        }
    },
    computed: {
        getAvgRating(){
            let avg = 0;
            this.reviewStore.reviews.forEach((review) => {
                avg += parseInt(review.rating)
            })
            return avg/this.reviewStore.reviews.length;
        }
    },
    setup() {
          const reviewStore = useReviewStore();
          const userStore = useUserStore();
          const productStore = useProductStore();
          return {reviewStore, userStore, productStore}
    },
}
</script>
<style lang="">
    
</style>