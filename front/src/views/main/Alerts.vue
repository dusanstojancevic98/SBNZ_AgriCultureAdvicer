<template>
  <v-card color="light-green lighten-5">
    <v-card :tile="true" color="light-green lighten-3" class="pa-5 text-h5 text-left">
      Obaveštenja
    </v-card>
    <div class="pa-5">
      <div class="list-center" v-if="alerts&&alerts.length>0">
        <Alert v-for="alert in alerts" :key="alert.id" :alert="alert"/>
      </div>
      <div v-else class="centered">
        Trenutno nema obaveštenja za prikaz.
      </div>
    </div>
  </v-card>
</template>

<script>
import {mapGetters} from "vuex";
import Alert from "@/components/Alert";

export default {
  name: "Alerts",
  components: {Alert},
  computed: {
    ...mapGetters({
      alerts: "getAlerts"
    })

  },
  created() {
    this.$store.dispatch("fetchAlerts")
  },


}
</script>

<style scoped>

</style>
