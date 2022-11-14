<template lang="">
    <div>
        <b-card
            :title="pName"
            :img-src="pImage"
            img-alt="Product image"
            img-top
            img-height="300"
            img-width="100"
            tag="product"
            style="max-width: 20rem;"
            class="mb-2"
        >
            <b-card-text>
                <p>{{description}}</p>
                <p>Price: {{price}}$</p>
                <p>Delivery fee: {{deliveryFee}}$</p>
                <p>Available stock: {{stock}}</p>
                <p>Seller: {{soldBy}}</p>
            </b-card-text>
            <b-button v-if="this.userStore.isLoggedIn" href="#" variant="primary">
                <b-icon @click="addToCart" icon="cart2" aria-hidden="true"></b-icon> Add to cart
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
    props: {id: String, pName: String, pImage: String, description: String, price: String, deliveryFee: String, stock: String, soldBy: String},
    methods: {
        addToCart(){
            this.userStore.items.push({id: this.$props.id, name: this.$props.pName, description: this.$props.description, image: this.$props.pImage, price: this.$props.price, deliveryFee: this.$props.deliveryFee, stock: this.$props.stock, soldBy: this.$props.soldBy})
            console.log(this.userStore.items)
        }
    },
}
</script>
<style lang="">
    
</style>