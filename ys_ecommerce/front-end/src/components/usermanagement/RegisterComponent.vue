<template>
    <div>
      <h3 class="mt-5 font-weight-bold">Create a new account</h3>
        <b-form class="col-4 m-auto p-3 border border-warning bg-light">
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
              <div class="modal fade" id="confirmation" tabindex="-1" role="dialog" aria-labelledby="confirmationLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                  <div class="modal-content">
                    <div class="modal-header">
                      <h5 class="modal-title" id="confirmationLabel">Modal title</h5>
                      <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                      </button>
                    </div>
                    <div class="modal-body">
                      ...
                    </div>
                    <div class="modal-footer">
                      <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                  </div>
                </div>
              </div>
            <b-modal id="bv-confirmation" hide-footer hide-header>
                <div class="d-block text-center">
                  <h2>Your account has been created!</h2>
                </div>
                <b-button class="mt-3" block @click="$bvModal.hide('bv-confirmation')">Ok</b-button>
            </b-modal>
            <b-button @click="onSubmit, $bvModal.show('bv-confirmation')" type="submit" variant="success" class="m-2 border border-dark" data-toggle="modal" data-target="#confirmation">Submit</b-button>
            <b-button @click="onReset" type="reset" variant="warning" class="m-2 border border-dark">Reset</b-button>
          </b-form>
          <p>Already have an account? 
            <router-link to="/login">Login</router-link>
          </p>
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
            this.form.name = ''
            this.form.lastName = ''
            this.form.address = ''
            this.form.accountType = ''
        }
    },
}
</script>
<style>
    
</style>