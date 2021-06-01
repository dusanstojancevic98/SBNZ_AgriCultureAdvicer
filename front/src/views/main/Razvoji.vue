<template>
  <v-row>
    <v-col cols="12">
      <v-card class="pa-2 link" color="lime" >
        <v-icon large>
          mdi-plus
        </v-icon>
      </v-card>
    </v-col>
    <v-col cols="6" v-for="r in razvoji" :key="r.id">
      <v-card>
        <v-card-title>{{ r.name }}</v-card-title>
      </v-card>
    </v-col>
  </v-row>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "Razvoji",
  created() {

    this.$store.dispatch("fetchRazvoji");
  },
  computed: {
    ...mapGetters({
      razvoji: "getRazvoji"
    })
  },

}
</script>

<style scoped>

</style>
