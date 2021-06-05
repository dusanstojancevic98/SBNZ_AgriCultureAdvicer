<template>
  <v-card width="200" key="a.id" color="light-green lighten-4" class="pa-2 mr-2">
    <v-row class="ma-0 pa-0">
      <div class="centered">
        <div class="text-left">{{a.naziv}}</div>
      </div>
      <v-spacer></v-spacer>
      <v-btn color="lime" icon><v-icon>mdi-check</v-icon></v-btn>
      <v-btn color="red lighten-3" icon><v-icon>mdi-close</v-icon></v-btn>
    </v-row>
  </v-card>
</template>

<script>
export default {
name: "AkcijaCard",
  props:[
      "a"
  ]
}
</script>

<style scoped>

</style>
