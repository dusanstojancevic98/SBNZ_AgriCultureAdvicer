<template>
  <v-card :color="lightColor" class="mb-5" max-width="1000" min-width="300" width="800">
    <v-card :tile="true" :color="darkColor" class="pa-2">
      <v-row class="ma-0 pa-0">
        <div class="centered">
          <div class="text-h5 text-left">{{ akcija.naziv }}</div>
        </div>
        <v-spacer>
        </v-spacer>
        <div class="centered-row">
          <v-card :color="lightColor" class="pa-2 text-left mr-2">START: {{ datumKreiranja }}</v-card>
          <v-card v-if="akcija.datumOdluke" :color="lightColor" class="pa-2 text-left mr-2">END: {{ datumOdluke }}</v-card>
          <v-card :color="tagColor" class="pa-2 text-left">{{ akcija.stanjeAkcije }}</v-card>
        </div>
      </v-row>
    </v-card>
    <div class="pa-5 text-left">{{ akcija.opisAkcije }}</div>
    <v-card v-if="!historic" tile flat color="light-green lighten-3" class="pa-2">
      <v-row class="ma-0 pa-0">
        <v-spacer>
        </v-spacer>
        <v-btn @click="confirm=true" small class="mr-2" color="lime lighten-3">
          <v-icon>mdi-check</v-icon>
          Odradjena
        </v-btn>
        <v-btn @click="cancel=true" small color="lime lighten-4">
          <v-icon>mdi-close</v-icon>
          Otkazana
        </v-btn>
      </v-row>
    </v-card>
    <ConfirmAkcija :id="akcija.id" :razvojId="akcija.razvojId" @close="confirm=false" :show="confirm"></ConfirmAkcija>
    <CancelAkcija :id="akcija.id" :razvojId="akcija.razvojId"  @close="cancel=false" :show="cancel"></CancelAkcija>
  </v-card>

</template>

<script>
import CancelAkcija from "@/components/dialogs/CancelAkcija";
import ConfirmAkcija from "@/components/dialogs/ConfirmAkcija";
import moment from "moment";

export default {
  name: "Akcija",
  components:{
    ConfirmAkcija, CancelAkcija
  },
  props: [
    "akcija"
  ],
  data() {
    return {
      confirm: false,
      cancel: false,
    }
  },
  computed: {
    historic() {
      let stanje = this.akcija.stanjeAkcije;
      return stanje === "OBAVLJENA" || stanje === "NEOBAVLJENA";

    },
    datumOdluke(){
      return moment(this.akcija.datumOdluke).format("DD-MM-YYYY")
    },
    datumKreiranja(){
      return moment(this.akcija.datumKreiranja).format("DD-MM-YYYY")

    },
    darkColor() {
      if (this.historic) {
        return "light-green lighten-4"
      }
      return "light-green lighten-2";
    },
    lightColor() {
      if (this.historic) {
        return "light-green lighten-5"
      }
      return "light-green lighten-4";

      // let stanje = this.akcija.stanjeAkcije;
      // return stanje === "NOVA" ? "teal lighten-4" : stanje === "OBAVLJENA" ? "grey lighten-3" : stanje === "NEOBAVLJENA" ? "red lighten-5" : "";

    },
    tagColor() {
      // return ""
      let stanje = this.akcija.stanjeAkcije;
      return stanje === "NOVA" ? "lime" : stanje === "OBAVLJENA" ? "yellow lighten-3" : stanje === "NEOBAVLJENA" ? "red lighten-3" : "";
    }
  }
}
</script>

<style scoped>

</style>
