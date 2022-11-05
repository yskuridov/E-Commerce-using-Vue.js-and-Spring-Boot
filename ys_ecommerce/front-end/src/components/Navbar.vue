<template>
    <div>
        <b-navbar type="dark" variant="dark">
            <div class="d-flex flex-row w-100 bg-success justify-content-between">
            <b-navbar-brand href="#">
              <b-icon icon="list-nested" aria-hidden="true"></b-icon>
              Seller
            </b-navbar-brand>
            <b-navbar-nav>
                <b-nav-item>Products</b-nav-item>
                <b-nav-item>Profile</b-nav-item>
            </b-navbar-nav>
            <b-navbar-nav>
                    <b-nav-form>
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
                    </b-nav-form>
                    </b-navbar-nav>

                </div>
        </b-navbar>
    </div>
</template>
<script>
import CustomerService from '../services/CustomerService'

export default {
    name: 'NavBar',
    data() {
        return {
            text:'',
            selected: '',
            options: [{ text: 'Select search criteria', value: null }, 'Search by price', 'Search by product name', 'Search by description', 'Search by vendor name', 'Products with free delivery'],
            data : []
        }
      },
      methods: {
        async onClick(event){
            event.preventDefault();
            this.data = await this.sendRequest(this.selected)
            return JSON.parse(JSON.stringify(this.data))
        },
        sendRequest(selected){
            console.log(this.text)
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