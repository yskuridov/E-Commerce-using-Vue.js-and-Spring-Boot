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
                <p>{{item.id}}</p>
                <b-button>
                    <b-icon icon="trash-fill" aria-hidden="true" @click="removeFromCart"></b-icon>
                </b-button>
            </b-card-text>
        </b-card>
    </div>
</template>
<script>
import { useUserStore } from '../../stores/UserStore'

export default {
    name: 'CartItem',
    props:['item'],
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
    methods: {
        removeFromCart(){
            if(this.userStore.items.length > 1) this.userStore.items.splice(this.getIndex(), 1)
            else this.userStore.items = [];
        },
        getIndex(){
            return this.userStore.items.indexOf(this.item)
        }
    }
}
</script>
<style lang="">
    
</style>