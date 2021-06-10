<template>
  <v-card color="light-green lighten-5" class="mb-5" max-width="1000" min-width="300" width="800">
    <v-card :tile="true" color="light-green lighten-3" class="pa-2">
      <v-row class="ma-0 pa-0">
        <div class="centered">
          <div class="text-h5 text-left">{{ alert.naziv }}</div>
        </div>
        <v-spacer>
        </v-spacer>
        <div class="centered-row">
          <v-card :color="tagColor" class="pa-2 text-left">{{ alert.uticaj }}</v-card>
        </div>
      </v-row>
    </v-card>
    <div class="pa-5 text-left">{{ alert.opis }}</div>
    <v-card v-if="alert.akcije && alert.akcije.length>0" tile flat color="light-green lighten-3" class="pa-2">
      <div class="row-scroll pb-2">
        <AkcijaCard v-for="a in alert.akcije" :a="a" :key="a.id"/>
      </div>
    </v-card>
  </v-card>
</template>

<script>
import AkcijaCard from "@/components/AkcijaCard";

export default {
  name: "Alert",
  components: {AkcijaCard},
  props: [
    "alert"
  ],
  computed: {
    tagColor() {
      return this.alert.uticaj === "NORMALAN" ? "green lighten-3" : this.alert.uticaj === "JAK" ? "red lighten-3" : "yellow lighten-3";
    }
  }
}
</script>

<style scoped>

</style>
