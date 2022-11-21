<template lang="">
    <b-tab :title="item.name">
        <b-card-text>
            <p>Name: {{item.name}}</p>
            <p>Description: {{item.description}}</p>
            <p>Price: {{item.price}}</p>
            <p>Delivery fee: {{item.deliveryFee}}</p>
            <p>Stock: {{item.stock}}</p>
        </b-card-text>
        <b-button class="bg-dark">
            <b-icon icon="wrench" aria-hidden="true" variant="success" class="p-1" @click="edit"></b-icon>
            <router-link :to="'/products/edit/' + item.key">Edit product</router-link>
        </b-button>
    </b-tab>
</template>
<script>
export default {
    name: "OwnedProduct",
    props:['item'],
    methods: {
        edit(){
            console.log(this.$props.item.key)
        }
    }
}
</script>
<style lang="">
    
</style>