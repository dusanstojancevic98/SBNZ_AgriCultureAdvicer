<template>
  <div>
    <v-row>
      <v-col cols="4" v-for="u in usevi" :key="u.id">
        <v-card dark color="green" class="pa-2">
          <v-card-title>{{ u.naziv }}
            <v-chip class="mr-2" color="green lighten-1">{{ u.tipUseva }}</v-chip>
            <v-chip color="green lighten-1">{{ u.podTipUseva }}</v-chip>
          </v-card-title>
          <v-card class="pa-2" color="green lighten-1">
            {{ u.opis }}
          </v-card>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: "PredlogUseva",
  created() {
    this.$store.dispatch("fetchPonudeUseva")
  },
  computed: {
    ...mapGetters({
          usevi: "getPonudeUseva"
        }
    )
  }
}
</script>

<style scoped>

</style>
