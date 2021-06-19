<template>
    <v-card color="light-green lighten-5" class="pa-5" width="400">
        <v-form @submit.prevent="submit">
            <v-text-field id="username" name="username" label="Korisničko ime" v-model="user.username"></v-text-field>
            <v-text-field id="password" name="password" label="Lozinka" type="password" v-model="user.password"></v-text-field>
            <v-btn type="submit" color="primary" :disabled="sending">Prijavite se</v-btn>
        </v-form>
        Nemate nalog?
        <router-link to="/register">Registrujte se.</router-link>
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
            },
            sending: false,
        }
    },
    methods: {
        submit() {
            console.log("LMAO")
            const dis = this;
            dis.sending = true;
            this.$store.dispatch("login", this.user)
                .then(() => {
                    dis.sending = false;
                    dis.$router.push("/")
                })
                .catch(() => {
                    dis.sending = false;
                })
        }
    }
}
</script>

<style scoped>

</style>
