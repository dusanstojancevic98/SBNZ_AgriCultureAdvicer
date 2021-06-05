<template>
  <v-card>
    <v-img height="200" :src="require('../../public/usevi.jpg')">
      <div class="blur15 fill-parent between-column">
        <div class="row-stretch  pa-5">
          <v-chip class="text-body-1" color="light-green lighten-3">{{ razvoj.naziv }}</v-chip>
        </div>
        <div class="light-green lighten-3  pa-5">
          <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn class="mr-2" color="yellow lighten-4" :to="'/razvoj/pregled/'+razvoj.id">Vidi detalje
            </v-btn>
            <v-btn class="mr-2" color="lime" v-if="razvoj.stanjeRazvoja==='INICIJALNO'"
                   :to="'/predloguseva/'+razvoj.ponudaUseva.id">Vidi ponude useva
            </v-btn>
            <v-btn class="mr-2" color="lime" v-if="razvoj.stanjeRazvoja==='SPREMNO'"
                   @click="pokreni(razvoj.id)">Pokreni razvoj
            </v-btn>
            <v-btn class="mr-2" color="error" @click="obrisi(razvoj.id)">Obriši razvoj</v-btn>
          </v-row>
        </div>
      </div>
    </v-img>
  </v-card>

</template>

<script>
export default {
name: "RazvojCard",
  props:[
      "razvoj"
  ]
}
</script>

<style scoped>

</style>
