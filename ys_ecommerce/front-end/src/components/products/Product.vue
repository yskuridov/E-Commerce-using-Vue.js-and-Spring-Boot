<template lang="">
    <div style="width: 18rem;">
        <b-card
            :title="pName"
            :img-src="pImage"
            img-alt="Product image"
            img-bottom
            img-height="300"
            tag="product"
            style="max-width: 20rem;"
            class="mb-2"
        >
            <b-card-text>
                <p>{{description}}</p>
                <p>Price: {{price}}$</p>
                <p>Delivery fee: {{deliveryFee}}$</p>
                <p>Available stock: {{stock}}</p>
                <router-link :to="sellerLink">Seller: {{soldBy}}</router-link>
            </b-card-text>
            <b-button v-if="this.userStore.isLoggedIn" @click="addToCart" href="#" variant="primary">
                <b-icon icon="cart2" aria-hidden="true"></b-icon> Add to cart
            </b-button>
        </b-card>
    </div>
</template>
<script>
import { useUserStore } from '../../stores/UserStore'
export default {
    name: 'ProductComponent',
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
    props: {pName: String, pImage: String, description: String, price: String, deliveryFee: String, stock: String, soldBy: String, prodId: String},
    methods: {
        addToCart(){
            this.userStore.items.push({id: this.userStore.items.length, name: this.$props.pName, description: this.$props.description, image: this.$props.pImage, price: this.$props.price, deliveryFee: this.$props.deliveryFee, stock: this.$props.stock, soldBy: this.$props.soldBy, prodId: this.$props.prodId})
        }
    },
    data(){
        return{
            sellerLink: '/vendors/' + this.$props.soldBy
        }
    }
}
</script>
<style lang="">
    
</style>