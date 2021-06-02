<template>
    <v-card color="light-green lighten-5" class="pa-5" width="400">
      <v-form @submit="submit">
        <v-text-field name="username" label="Korisničko ime" v-model="register.username"></v-text-field>
        <v-text-field name="name" label="Ime" v-model="register.firstName"></v-text-field>
        <v-text-field name="lastname" label="Prezime" v-model="register.lastName"></v-text-field>
        <v-text-field name="email" label="E-mail" v-model="register.email"></v-text-field>
        <v-text-field name="password" label="Lozinka" type="password" v-model="register.password"></v-text-field>
        <v-btn type="submit" color="primary" :disabled="sending">Registrujte se</v-btn>
      </v-form>
      Registrovani ste?
      <router-link to="/login">Prijavite se.</router-link>
    </v-card>


</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      register: {
        username: "",
        password: "",
        firstName:"",
        lastName:"",
        email:""
      },
        sending:false
    }
  },
  methods:{
    submit(e){
      e.preventDefault();
      const dis = this;
      this.sending = true;
      this.$store.dispatch("register", this.register)
          .then(()=>{
              dis.sending = false;
            dis.$router.push("/login")
          })
        .catch(()=>{
            dis.sending = false;
        })
    }
  }
}
</script>

<style scoped>

</style>
