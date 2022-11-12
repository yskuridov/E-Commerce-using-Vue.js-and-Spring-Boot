<template lang="">
    <div>
        <b-card
            :title="pName"
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
        </b-card>
    </div>
</template>
<script>
export default {
    name: 'ProductComponent',
    props: {pName: String, description: String, price: String, deliveryFee: String, stock: String, soldBy: String},

}
</script>
<style lang="">
    
</style>