<template>
  <v-card :flat="!trenutno" width="400" min-width="400"  class="pa-3 mr-2" color="light-green lighten-5">
    <v-row class="pa-0 ma-0 mb-2">
      <v-card color="light-green lighten-4" :flat="!trenutno" class="pa-2 mr-2">
        Datum: {{stanje.datum}}
      </v-card>
      <v-card color="light-green lighten-4" :flat="!trenutno" class="pa-2">
        Stanje: {{stanje.stanje}}
      </v-card>
    </v-row>
    <v-progress-linear
        class="mb-2"
        :value="stanje.procenatVlage"
        :color="trenutno?'green':'light-green lighten-3'"
        height="25"
    >
      <strong>Procenat vlage: {{ Math.ceil(stanje.procenatVlage) }}%</strong>
    </v-progress-linear>
    <v-row class="pa-0 ma-0">
      <v-spacer>

      </v-spacer>
      <v-card color="lime lighten-2" v-if="trenutno" class="pa-0">
        Trenutno stanje zemljista
      </v-card>
    </v-row>
  </v-card>
</template>

<script>
export default {
name: "StanjeZemljista",
  props:[
      "stanje",
      "trenutno"
  ]
}
</script>

<style scoped>

</style>
