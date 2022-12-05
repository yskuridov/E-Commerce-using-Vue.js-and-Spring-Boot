<template lang="">
    <b-tab :title="review.customerName">
        <b-card-text>
            <div>
                <h2 class="border-bottom border-dark p-0">
                    {{review.rating}}
                    <b-icon icon="star-fill" class="text-warning p-1"></b-icon>
                </h2>
                <div class="bg-light">
                    <p>{{review.comment}}</p>
                </div>
            </div>
        </b-card-text>
    </b-tab>
</template>
<script>
export default {
    name: 'ReviewComponent',
    props: ['review'],
    data() {
        return {
            
        }
    }
}
</script>
<style lang="">
    
</style>