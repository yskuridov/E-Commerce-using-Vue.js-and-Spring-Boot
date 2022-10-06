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

            <b-button @click="onSubmit" type="submit" variant="primary">Submit</b-button>
            <b-button @click="onReset" type="reset" variant="danger">Reset</b-button>
          </b-form>
    </div>
</template>
<script>
export default {
    name: 'RegisterComponent',
    data() {
        return {
            form: {
                username: '',
                password: '',
                name: '',
                lastName: '',
                address: ''
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