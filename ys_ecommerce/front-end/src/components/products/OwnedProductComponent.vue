<template lang="">
              <b-tab :title="item.name">
                    <b-card-text>
                        <p>Name: {{item.name}}</p>
                        <p>Description: {{item.description}}</p>
                        <p>Price: {{item.price}}</p>
                        <p>Delivery fee: {{item.deliveryFee}}</p>
                        <p>Stock: {{item.stock}}</p>
                    </b-card-text>
                    <b-button style="background-color:transparent">
                        <b-icon icon="x-lg" aria-hidden="true" variant="danger" @click="edit"></b-icon>
                    </b-button>
               </b-tab>
</template>
<script>
export default {
    name: "OwnedProduct",
    props:['item'],
    methods: {
        edit(){

        }
    }
}
</script>
<style lang="">
    
</style>