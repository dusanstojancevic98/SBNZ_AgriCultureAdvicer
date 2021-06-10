<template>
  <div>

    <v-app-bar
        app
        color="green darken-4"
        dark
    >
      <v-row class="mx-2">

        <v-btn class="mr-2" v-for="r in routes" :to="r.path" color="green darken-2" :key="r.id">
          <v-icon>
            {{r.icon}}
          </v-icon>
          {{r.name}}
        </v-btn>
        <v-spacer></v-spacer>
        <AlertBlock class="mr-2" >
          <v-btn to="/obavestenja" color="light-green">
            Obaveštenja
          </v-btn>
        </AlertBlock>
        <v-btn class="mr-2" color="light-green" @click="logout">
          <v-icon>
            mdi-account
          </v-icon>
          Odjavi se
        </v-btn>
      </v-row>
    </v-app-bar>

  </div>
</template>

<script>

import {mapGetters} from "vuex";
import AlertBlock from "@/components/mini/AlertBlock";
export default {
name: "Header",
  components: {AlertBlock},
  computed:{
    ...mapGetters({
      routes:"getRoutes"
    })
  },
  methods:{
    logout(){
      localStorage.clear()
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>

</style>
