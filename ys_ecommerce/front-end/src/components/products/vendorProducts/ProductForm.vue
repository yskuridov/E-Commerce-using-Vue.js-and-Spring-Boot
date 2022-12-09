<template lang="">
    <div v-if="this.userStore.isLoggedIn">
        <b-form class="col bg-light p-2">
            <b-form-group label="Name: " label-for="name">
              <b-form-input
                id="name"
                v-model="form.pName"
                placeholder="Name"
                required
                size="sm"
              ></b-form-input>
            </b-form-group>
            <b-form-group label="Description:" label-for="desc">
                <b-form-textarea
                    id="desc"
                    v-model="form.pDesc"
                    placeholder="Description"
                    rows="2"
                    max-rows="4"
                    size="sm"
                ></b-form-textarea>
            </b-form-group>
            <b-form-group label="Price:" label-for="price">
                <b-form-input type="number" size="sm" id="price" v-model="form.pPrice" placeholder="Price"></b-form-input>
            </b-form-group>
            <b-form-group label="Delivery fee:" label-for="deliveryFee">
                <b-form-input type="number" size="sm" id="deliveryFee" v-model="form.pDeliveryFee" placeholder="Delivery Fee"></b-form-input>
            </b-form-group>
            <b-form-group label="Image URL:" label-for="imageUrl">
                <b-form-input type="image" size="sm" id="imageUrl" v-model="form.pImage" placeholder="URL"></b-form-input>
            </b-form-group>
            <b-form-group label="Stock:" label-for="stock">
                <b-form-input type="number" size="sm" id="stock" v-model="form.pStock" placeholder="Stock"></b-form-input>
            </b-form-group>
          </b-form>
          <div class="d-flex justify-content-around" v-if="this.userStore.isVendor">
            <b-button @click="this.saveProduct" variant="dark">
                <b-icon icon="check" aria-hidden="true" variant="success" class="p-1"></b-icon>
                Save
            </b-button>
            <b-button @click="this.cancelChanges" variant="dark">
                <b-icon icon="x" aria-hidden="true" variant="danger" class="p-1 "></b-icon>
                Cancel
            </b-button>
        </div>
    </div>
</template>
<script>
import { useProductStore } from '@/stores/ProductStore';
import Vue from 'vue';
import VendorService from '@/services/VendorService';
import { useUserStore } from '@/stores/UserStore';
export default {
    name: "ProductForm",
    props: ['item', 'toggleParentMode'],
    setup() {
        const productStore = useProductStore();
        const userStore = useUserStore();
        return { productStore, userStore }
    },
    mounted: async function (){
       await this.initializeForm();
       if(this.form.pName === undefined) this.inAddMode = true;
    },
    data() {
        return{
            form: {
                pName: null,
                pDesc: null,
                pImage: null,
                pPrice: null,
                pStock: null, 
                pDeliveryFee: null
            },
            product: {},
            inAddMode: false,
        }
    },
    methods: {
        saveProduct(event){
            event.preventDefault();
            this.setProduct();
            if(!this.inAddMode){
                Vue.set(this.productStore.items, this.productStore.items.indexOf(this.item), this.product);
                VendorService.updateProduct(this.product)
            }
            else {
                this.productStore.items.push(this.product);
                VendorService.addProduct(this.product);
            }
            this.toggleParentMode();
        },
        async initializeForm(){
            this.form.pName = this.item.name;
            this.form.pImage = this.item.imageUrl;
            this.form.pDesc = this.item.description;
            this.form.pPrice = this.item.price;
            this.form.pDeliveryFee = this.item.deliveryFee;
            this.form.pStock = this.item.stock
        },
        cancelChanges(){
            this.initializeForm();
            this.toggleParentMode();
        },
        setProduct(){
            this.product = {
                id: this.item.id,
                imageUrl: this.form.pImage, 
                soldBy: this.item.soldBy, 
                vendorUsername: this.item.vendorUsername,
                stock: this.form.pStock,
                name: this.form.pName,
                description: this.form.pDesc,
                price: this.form.pPrice,
                deliveryFee: this.form.pDeliveryFee
            }
            if(this.inAddMode){
                this.product.id = 0;
                this.product.soldBy = this.userStore.loggedInUser.id;
                this.product.vendorUsername = this.userStore.loggedInUser.username;
            }
        },
    }
}
</script>
<style lang="">
    
</style>