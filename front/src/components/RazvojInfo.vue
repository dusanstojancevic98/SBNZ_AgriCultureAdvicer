<template>
  <div>
    <v-card color="light-green lighten-5" class="pa-5 mb-5">
      <div class="text-h5 text-left">Osnovne informacije</div>
      <v-text-field readonly label="Naziv" v-model="razvoj.naziv"></v-text-field>
      <v-text-field readonly label="Budžet" type="number" v-model="razvoj.budzet"></v-text-field>
    </v-card>

    <v-card flat tile color="light-green lighten-4 pa-5" >
      <div class="text-h5 text-left pa-2">Trenutne akcije</div>
      <div class="row-scroll pb-2">
        <AkcijaCard  v-for="a in razvoj.trenutnaAkcija" :a="a" :key="a.id"></AkcijaCard>
      </div>
    </v-card>

    <v-card flat tile color="light-green lighten-3 pa-5" >
      <div class="text-h5 text-left pa-2">Stanja zemljista</div>
      <RazvojStanjaZemljista/>
    </v-card>


    <v-card flat tile color="light-green lighten-4 pa-5" >
      <div class="text-h5 text-left pa-2">Stanja useva</div>
      <RazvojStanjaUseva/>
    </v-card>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import AkcijaCard from "@/components/AkcijaCard";
import RazvojStanjaZemljista from "@/components/RazvojStanjaZemljista";
import RazvojStanjaUseva from "@/components/RazvojStanjaUseva";

export default {
  name: "RazvojInfo",
  components: {RazvojStanjaZemljista, AkcijaCard, RazvojStanjaUseva},
  computed: {
    ...mapGetters({
          razvoj: "getTrenutniRazvoj"
        }
    )

  }

}
</script>

<style scoped>

</style>
