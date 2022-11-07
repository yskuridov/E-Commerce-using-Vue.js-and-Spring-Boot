<template>
    <div>
      <h3 class="mt-5 font-weight-bold">Login</h3>
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
            <b-button @click="onSubmit" type="submit" class="bg-success m-2 border border-dark" v-if="this.$loggedInUser.value == null">Submit</b-button>
            <b-button @click="onReset" type="reset" class="bg-warning m-2 border border-dark">Reset</b-button>
          </b-form>
          <p>Don't have an account? 
            <router-link to="/register">Create one</router-link>
          </p>
    </div>
</template>
<script>

import Vue from 'vue';
import CommonService from '../../services/CommonService'

export default {
    name: 'LoginComponent',
    data() {
        return {
            form: {
                username: '',
                password: '',
            },
            loggedIn: false,
            user: {},
        };
    },
    methods: {
        async onSubmit(event) {
            event.preventDefault()
            this.user = await CommonService.login(this.form.username, this.form.password)
            if(this.user != null){
              Vue.prototype.$loggedInUser.value = this.user;
              this.$router.push('register')
            }
            
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