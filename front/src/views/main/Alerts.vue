<template>
  <v-card color="light-green lighten-5" class="pa-5">
    <div class="list-center" v-if="akcije&&akcije.length>0">
      <Akcija v-for="akcija in akcije" :key="akcija.id" :akcija="akcija"/>
    </div>
    <div v-else class="centered">
      Trenutno nema akcija za prikaz.
    </div>
  </v-card>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "Alerts",
  computed: {
    ...mapGetters({
      alerts: "getAlerts"
    })

  }

}
</script>

<style scoped>

</style>
