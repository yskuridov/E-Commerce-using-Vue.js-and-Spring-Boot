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
            <b-button @click="onSubmit" type="submit" variant="primary">Submit</b-button>
            <b-button @click="onReset" type="reset" variant="danger">Reset</b-button>
          </b-form>
          <p>Don't have an account? 
            <a href="/register">Create one</a>
          </p>
    </div>
</template>
<script>
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