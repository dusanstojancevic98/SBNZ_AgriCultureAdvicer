<template>
  <v-card :flat="!trenutno" width="400" min-width="400" class="pa-3 mr-2" color="light-green lighten-5">
    <v-card color="light-green lighten-4" :flat="!trenutno" class="pa-2 mb-2">
      Datum: {{ stanje.date }}
    </v-card>
    <div class="row-scroll pb-2">
      <v-card :flat="!trenutno" class="pa-2 mr-2" v-for="s in stanje.stanja" :key="s" color="light-green lighten-3">
        {{ s }}
      </v-card>
    </div>
    <v-row class="pa-0 ma-0">
      <v-spacer>
      </v-spacer>
      <v-card color="lime lighten-2" v-if="trenutno" class="pa-0">
        Trenutno stanje useva
      </v-card>
    </v-row>
  </v-card>
</template>
d<script>
export default {
  name: "StanjeUseva",
  props: [
    "stanje",
    "trenutno"
  ]
}
</script>

<style scoped>

</style>
