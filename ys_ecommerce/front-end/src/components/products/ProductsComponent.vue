<template lang="">
    <div>
        <b-form>
            <b-form-select
                size="sm"
                id="type"
                v-model="selected"
                :options="options"
                required
            >
            </b-form-select>
            <b-form-input size="sm" placeholder="Search" v-model="text" required></b-form-input>
            <b-button size="sm" type="submit" @click="onClick">Search</b-button>
            <b-form-group id="searchBy" label-for="searchByOptions" size="sm" class="col-1">
            </b-form-group>
        </b-form>
        <div class="bg-warning">
            <ProductComponent
            v-for="product in products"
            :key="product.id"
            :pName="product.name"
            :description="product.description"
            :price="product.price"
            :deliveryFee="product.deliveryFee"
            :stock="product.stock"
            :soldBy="product.soldBy">
            </ProductComponent>
        </div>
    </div>
</template>
<script>
import ProductComponent from './ProductComponent.vue';
import CustomerService from '@/services/CustomerService';
export default {
    components: { ProductComponent },
    data(){
        return{ 
            products: [],
            text:'',
            selected: '',
            searchWasMade: false,
            options: [{ text: 'Select search criteria', value: null }, 'Search by price', 'Search by product name', 'Search by description', 'Search by vendor name', 'Products with free delivery'],
        }
    },
    methods: {
            async onClick(event){
                event.preventDefault();
                this.products = JSON.parse(JSON.stringify(await this.sendRequest(this.selected)))
                console.log(this.products)
                this.searchWasMade = true
                console.log(this.products)
                console.log(this.text)
                this.searchWasMade = false
            },
            sendRequest(selected){
                if(selected == this.options[0]) return false;
                if(selected == this.options[1]) return CustomerService.searchByPrice(this.text)
                if(selected == this.options[2]) return CustomerService.searchByName(this.text)
                if(selected == this.options[3]) return CustomerService.searchByDescription(this.text)
                if(selected == this.options[4]) return CustomerService.searchByVendor(this.text)
                if(selected == this.options[5]) return CustomerService.searchByFreeDelivery()
            }
        }
}
</script>
<style lang="">
    
</style>