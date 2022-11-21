<template lang="">
    <div>
        <!-- <img :src="item.image" alt="Product image" height="100" width="100"> -->
        <b-card
        :title="item.name"
        style="max-width: 20rem;"
        class="mb-2"
        >
            <b-card-text>
                <p>Price: {{ item.price }}$</p>
                <b-button>
                    <b-icon icon="trash-fill" aria-hidden="true"></b-icon>
                </b-button>
            </b-card-text>
        </b-card>
    </div>
</template>
<script>
export default {
    name: 'CartItem',
    props:['item'],
    methods: {
        removeFromCart(){

        }
    }
}
</script>
<style lang="">
    
</style>