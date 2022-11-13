<template>
    <div>
      <h3 class="mt-5 font-weight-bold">Login</h3>
        <b-form class="col-4 m-auto p-3 border border-warning bg-light" v-if="this.userStore.isLoggedIn == false">
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
            <b-button v-b-modal.modal-success @click="onSubmit" type="submit" class="bg-success m-2 border border-dark">Submit</b-button>
            <b-button @click="onReset" type="reset" class="bg-warning m-2 border border-dark">Reset</b-button>
            <p class="mt-3">Don't have an account? 
              <router-link to="/register">Create one</router-link>
            </p>
          </b-form>
          <h3 v-if="this.userStore.isLoggedIn == true" class="text-success m-5">You are logged in!</h3>
    </div>
</template>
<script>

import CommonService from '../../services/CommonService'
import { useUserStore } from '../../stores/UserStore'

export default {
    name: 'LoginComponent',
    setup() {
          const userStore = useUserStore();
          return { userStore }
    },
    data() {
        return {
            form: {
                username: '',
                password: '',
            }
        };
    },
    methods: {
        async onSubmit(event) {
            event.preventDefault()
            this.userStore.login(JSON.parse(JSON.stringify(await CommonService.login(this.form.username, this.form.password))))
            console.log(this.userStore.isLoggedIn)
            console.log(this.userStore.loggedInUser)
        },
        onReset(event){
            event.preventDefault()
            this.form.username = ''
            this.form.password = ''
        }
    }
}
</script>
<style>
    
</style>