<template>
  <v-card :class="odabran?'pa-2 grower':'grower'" color="lime">
    <v-card  :color="'light-green lighten-4'" class="pa-3 " @click="odaberi()">
      <v-card-title>
        <v-row>
          {{ usev.naziv }}
          <v-spacer>
          </v-spacer>
          <v-chip class="mr-5" :color="'light-green lighten-3'">{{ usev.tipUseva }}</v-chip>
          <v-chip  :color="'light-green lighten-3'">{{ usev.podTipUseva }}</v-chip>
        </v-row>
      </v-card-title>
      <v-card class="pa-5" :color="'light-green lighten-3'">
        {{ usev.opis }}
      </v-card>
    </v-card>
  </v-card>
</template>

<script>
export default {
    name: "Usev",
    props: [
        "usev",
        "odabran"
    ],
    methods: {
        odaberi() {
            this.$emit('select', this.usev.id);
        }
    }
}
</script>

<style scoped>

</style>
