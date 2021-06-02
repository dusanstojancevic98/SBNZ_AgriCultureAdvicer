<template>
    <v-card class="pa-5" width="400">
      <v-form @submit="submit">
        <v-text-field name="username" label="Korisničko ime" v-model="user.username"></v-text-field>
        <v-text-field name="password" label="Lozinka" type="password" v-model="user.password"></v-text-field>
        <v-btn type="submit" color="primary">Prijavite se</v-btn>
      </v-form>
      Nemate nalog?<router-link to="/register">Registrujte se.</router-link>
    </v-card>

</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {
        username: "",
        password: ""
      }
    }
  },
  methods:{
    submit(e){
      e.preventDefault();
      const dis = this;
      this.$store.dispatch("login", this.user)
        .then(()=>{
          dis.$router.push("/")
        })
    }
  }
}
</script>

<style scoped>

</style>
