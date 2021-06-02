<template>
  <v-row>
    <v-col cols="6" v-for="r in razvoji" :key="r.id">
      <v-card>
        <v-card-title>{{ r.name }}</v-card-title>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
export default {
  name: "RazvojiColumns",
  props: [
    "razvoji"
  ]
}
</script>

<style scoped>

</style>
