<template lang="">
    <div class="m-5 p-5">
        <p>{{pName}}</p>
        <p>{{description}}</p>
        <p>{{price}}</p>
        <p>{{deliveryFee}}</p>
        <p>{{stock}}</p>
        <p>{{soldBy}}</p>
    </div>
</template>
<script>
export default {
    name: 'ProductComponent',
    props: {pName: String, description: String, price: String, deliveryFee: String, stock: String, soldBy: String},
    data(){
        return {
            text:'',
            selected: '',
            options: [{ text: 'Select search criteria', value: null }, 'Search by price', 'Search by product name', 'Search by description', 'Search by vendor name', 'Products with free delivery'],
        }
    }
}
</script>
<style lang="">
    
</style>