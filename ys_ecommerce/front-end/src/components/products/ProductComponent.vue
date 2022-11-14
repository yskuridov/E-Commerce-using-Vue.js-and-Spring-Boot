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
    props: {pName: String, pImage: String, description: String, price: String, deliveryFee: String, stock: String, soldBy: String},

}
</script>
<style lang="">
    
</style>