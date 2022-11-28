<template lang="">
    <b-tab :title="item.name">
        <b-card-text>
            <div v-if="!this.inEditMode">
                <p>Name: {{item.name}}</p>
                <p>Description: {{item.description}}</p>
                <p>Price: {{item.price}}$</p>
                <p>Delivery fee: {{item.deliveryFee}}$</p>
                <p>Stock: {{item.stock}}</p>
            </div>
           <b-form class="col bg-light p-2" v-if="this.inEditMode">
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
        </b-card-text>
        <b-button v-if="!inEditMode" @click="this.toggleEdit" href="#" variant='dark'>
            Edit product
            <b-icon icon="wrench" aria-hidden="true" variant="success" class="p-1"></b-icon>
        </b-button>
        <div v-if="inEditMode" class="d-flex justify-content-around">
            <b-button @click="this.saveProduct" variant="dark">
                <b-icon icon="check" aria-hidden="true" variant="success" class="p-1"></b-icon>
                Save
            </b-button>
            <b-button @click="this.cancelChanges" variant="dark">
                <b-icon icon="x" aria-hidden="true" variant="danger" class="p-1 "></b-icon>
                Revert
            </b-button>
        </div>
    </b-tab>
</template>
<script>
import VendorService from '@/services/VendorService';
import { useUserStore } from '@/stores/UserStore';
import Vue from 'vue';
export default {
    name: "OwnedProduct",
    props:['item'],
    setup() {
        const userStore = useUserStore();
        return { userStore }
    },
    mounted: async function (){
       await this.initializeForm();
    },
    data() {
        return{
            inEditMode: false,
            form: {
                pName: null,
                pDesc: null,
                pImage: null,
                pPrice: null,
                pStock: null, 
                pDeliveryFee: null
            },
            product: {}
        }
    },
    methods: {
       toggleEdit(){
            if(this.inEditMode == true) this.inEditMode = false;
            else this.inEditMode = true;
        },
        saveProduct(event){
            event.preventDefault();
            this.setProduct();
            Vue.set(this.userStore.items, this.userStore.items.indexOf(this.item), this.product);
            VendorService.updateProduct(this.product)
            this.inEditMode = false;
            //tried to change value directly in store - non reactive, had to use vue,set
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
            this.inEditMode = false;
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
        }
    }
}
</script>
<style lang="">
    
</style>