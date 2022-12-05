<template lang="">
    <div class="d-flex justify-content-center m-1 p-0 border border-dark">
        <img :src="item.image" alt="Product image" width="150" height="150" class="mt-4">
        <div class="mt-4 w-50">
            <p>Name: {{item.name}}</p>
            <p>Description: {{item.description}}</p>
            <p>Price: {{item.price}}$</p>
            <p>Delivery fee: {{item.deliveryFee}}$</p>
        </div>
    </div>
</template>
<script>
export default {
    name: 'OrderItem',
    props: ['item'],
}
</script>
<style lang="">
    
</style>