<template lang="">
    <div>
        <h4 class="m-3">My products</h4>
        <div class="container">
            <div>
                <b-card no-body>
                    <b-tabs pills card vertical>
                        <OwnedProduct v-for="item in this.userStore.items" :item=item :key="item.id"></OwnedProduct>
                    </b-tabs>
                </b-card>
            </div>
        </div>
    </div>
</template>
<script>
import OwnedProduct from './OwnedProductComponent.vue';
import { useUserStore } from '@/stores/UserStore';
export default {
    name: "ProductsInventory",
    components: {OwnedProduct},
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
}
</script>
<style lang="">
    
</style>