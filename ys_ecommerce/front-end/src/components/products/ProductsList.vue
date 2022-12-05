<template lang="">
    <div>
        <h3 class="mt-5 font-weight-bold">Search for products</h3>
        <b-form class="col-4 m-auto p-3 border border-warning bg-light">
            <b-form-group label="Search by:" label-for="type">
                <b-form-select
                    size="sm"
                    id="type"
                    v-model="selected"
                    :options="options"
                    required
                >
                </b-form-select>
            </b-form-group>
            <b-form-input size="sm" placeholder="Search" v-model="text" required></b-form-input>
            <b-button class="m-2 border border-dark" type="submit" @click="onClick">Search</b-button>
        </b-form>
        <div v-if="this.searchWasMade" class="mt-5 container">
            <h3>Results: </h3>
            <div class="row justify-content-around">
                <ProductComponent class="m-2"
                v-for="product in this.productStore.items"
                :key="product.id"
                :pName="product.name"
                :pImage="product.imageUrl"
                :description="product.description"
                :price="product.price"
                :deliveryFee="product.deliveryFee"
                :stock="product.stock"
                :soldBy="product.vendorUsername"
                :prodId="product.id">
                </ProductComponent>
            </div>
        </div>
    </div>
</template>
<script>
import ProductComponent from './Product.vue';
import CustomerService from '@/services/CustomerService';
import { useProductStore } from '@/stores/ProductStore';
export default {
    components: { ProductComponent },
    name: "ProductsComponent",
    setup() {
          const productStore = useProductStore();
          return { productStore }
    },
    data(){
        return{ 
            text:'',
            selected: '',
            options: ['Price', 'Product name', 'Description', 'Vendor name', 'Free delivery'],
            searchWasMade: false,
        }
    },
    methods: {
            async onClick(event){
                event.preventDefault();
                this.productStore.items = JSON.parse(JSON.stringify(await this.sendRequest(this.selected)))
                this.searchWasMade = true
            },
            sendRequest(selected){
                if(selected == this.options[0]) return CustomerService.searchByPrice(this.text)
                if(selected == this.options[1]) return CustomerService.searchByName(this.text)
                if(selected == this.options[2]) return CustomerService.searchByDescription(this.text)
                if(selected == this.options[3]) return CustomerService.searchByVendor(this.text)
                if(selected == this.options[4]) return CustomerService.searchByFreeDelivery()
            }
        }
}
</script>
<style lang="">
    
</style>