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
            <b-button @click="onSubmit" type="submit" class="bg-success m-2 border border-dark">Submit</b-button>
            <b-button @click="onReset" type="reset" class="bg-warning m-2 border border-dark">Reset</b-button>
          </b-form>
          <p>Don't have an account? 
            <a href="/register">Create one</a>
          </p>
    </div>
</template>
<script>
import axios from 'axios'
export default {
    name: 'LoginComponent',
    data() {
        return {
            form: {
                username: '',
                password: '',
            }
        };
    },
    methods: {
        onSubmit(event) {
            event.preventDefault()
            axios.post('http://localhost:8080/customers/add', {username: this.form.username, password: this.form.password})
            
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