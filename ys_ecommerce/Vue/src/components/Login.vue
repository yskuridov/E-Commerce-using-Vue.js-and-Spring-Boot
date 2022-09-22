<template>
    <b-form @submit="onSubmit">
      <b-form-group label="Username: " label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.username"
          type="text"
          placeholder="Enter your username"
          required
        ></b-form-input>
      </b-form-group>
      <b-form-group label="Password:" label-for="input-2">
        <b-form-input
          id="input-2"
          v-model="form.password"
          type="password"
          placeholder="Enter your password"
          required
        ></b-form-input>
      </b-form-group>
      <b-button type="submit" variant="primary">Login</b-button>
    </b-form>
</template>

<script>
  export default {
    name: 'LoginVue',
    data() {
      return {
        form: {
          username: '',
          password: '',
        }
      }
    },
    methods: {
      onSubmit(event) {
        event.preventDefault()
        alert(JSON.stringify(this.form))
      },
    }
  }
</script>