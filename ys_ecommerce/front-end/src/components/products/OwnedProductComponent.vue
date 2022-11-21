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
           <!-- <b-form class="col-4 m-auto p-3 border border-warning bg-light" v-if="this.inEditMode">
                <b-form-group label="Username: " label-for="username">
                  <b-form-input
                    id="username"
                    v-model="form.username"
                    placeholder="Enter your username"
                    required
                  ></b-form-input>
                </b-form-group>
                <b-form-group label="Password: " label-for="password">
                  <b-form-input
                    type="password"
                    id="password"
                    v-model="form.password"
                    placeholder="Enter your password"
                    required
                  ></b-form-input>
                </b-form-group>
              </b-form>-->
        </b-card-text>
        <b-button @click="this.toggleEdit" href="#" variant='dark'>
            Edit product
            <b-icon icon="wrench" aria-hidden="true" variant="success" @click="toggleEdit" class="p-1"></b-icon>
        </b-button>
    </b-tab>
</template>
<script>
export default {
    name: "OwnedProduct",
    props:['item'],
    data() {
        return{
            inEditMode: false,
            form: {
                pName: '',
                pDesc: '',
                pPrice: '',
                pDeliveryFee: '',
                pStock: ''
            }
        }
    },
    methods: {
       /* toggleEdit(){
            if(this.inEditMode == true) this.inEditMode = false;
            else this.inEditMode = true;
        },
        saveProduct(event){
            newProduct = {id: this.item.id, }
        }*/
    }
}
</script>
<style lang="">
    
</style>