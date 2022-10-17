<template>
    <div>
        <b-form>
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

            <b-form-group label="Name: " label-for="name">
                <b-form-input
                  id="name"
                  v-model="form.name"
                  placeholder="Enter your first name"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group label="Last name: " label-for="lastName">
                <b-form-input
                  id="lastName"
                  v-model="form.lastName"
                  placeholder="Enter your last name"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group label="Address: " label-for="address">
                <b-form-input
                  id="address"
                  v-model="form.address"
                  placeholder="Enter your address"
                  required
                ></b-form-input>
              </b-form-group>

              <b-form-group label="Select your account type: " label-for="type">
                <b-form-select
                  id="type"
                  v-model="form.accountType"
                  :options="types"
                  required
                ></b-form-select>
              </b-form-group>

            <b-button @click="onSubmit" type="submit" variant="primary">Submit</b-button>
            <b-button @click="onReset" type="reset" variant="danger">Reset</b-button>
          </b-form>
    </div>
</template>
<script>
    import axios from 'axios'
export default {
    name: 'RegisterComponent',
    data() {
        return {
            form: {
                username: '',
                password: '',
                name: '',
                lastName: '',
                address: '',
                accountType: ''
            },
            types: ['Vendor', 'Customer']
        }
    },
    methods: {
        onSubmit(event) {
            event.preventDefault()
            if(this.form.accountType == 'Vendor') axios.post('http://localhost:8080/vendors/add', {username: this.form.username, password: this.form.password, firstName: this.form.firstName, lastName: this.form.lastName, address: this.form.address})
            else axios.post('http://localhost:8080/customers/add', {username: this.form.username, password: this.form.password, firstName: this.form.firstName, lastName: this.form.lastName, address: this.form.address})
        },
        onReset(event){
            event.preventDefault()
            this.form.username = ''
            this.form.password = ''
        }
    },
}
</script>
<style>
    
</style>