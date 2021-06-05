<template>
  <v-card class="pa-5" color="light-green lighten-3 mb-5" max-width="1000">
    <v-row class="ma-0 pa-0">
      <div class="centered">
        <div class="text-h5 mb-2 text-left">{{ akcija.naziv }}</div>
      </div>
      <v-spacer>
      </v-spacer>
      <v-card :color="color" class="pa-2 mb-2 text-left">{{ akcija.stanjeAkcije }}</v-card>
    </v-row>
    <v-card color="light-green lighten-4" class="pa-2 mb-2 text-left" tile flat>{{ akcija.opisAkcije }}</v-card>
  </v-card>
</template>

<script>
export default {
  name: "Akcija",
  props: [
    "akcija"
  ],
  computed: {
    color() {
      let stanje = this.akcija.stanjeAkcije;
      return stanje === "NOVA" ? "lime" : stanje === "OBAVLJENA" ? "yellow lighten 4" : stanje === "NEOBAVLJENA" ? "red lighten-3" : "";
    }
  }
}
</script>

<style scoped>

</style>
