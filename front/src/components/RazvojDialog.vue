<template>
  <v-dialog v-model="dValue" width="800" @input="onclose">
    <v-card class="pa-5" color="light-green lighten-4">

      <div class="text-h3 pb-5">
        Novi plan razvoja
      </div>
      <v-form @submit="submit">
        <v-row>
          <v-col cols="6">
            <v-card class="pa-5 mb-5" color="light-green lighten-3">
              <div class="text-h5 text-left">Osnovne informacije</div>
              <v-text-field id="naziv"  label="Naziv" v-model="razvoj.naziv"></v-text-field>
              <v-text-field id="budzet" label="Budžet" type="number" v-model="razvoj.budzet"></v-text-field>

            </v-card>
            <v-card class="pa-5" color="light-green lighten-3">
              <div class="text-h5 text-left">Podazi o vremenu</div>
              <v-text-field id="suncaniDani" type="number" label="Broj sunčanih dana"
                            v-model="razvoj.konfiguracija.vremenskiPodaci.brojSuncanihDana"></v-text-field>
              <v-text-field id="padavine" type="number" label="Padavine"
                            v-model="razvoj.konfiguracija.vremenskiPodaci.padavine"></v-text-field>
              <v-text-field id="temperatura" type="number" label="Prosečna godišnja temperatura"
                            v-model="razvoj.konfiguracija.vremenskiPodaci.prosecnaGodisnjaTemperatura"></v-text-field>

            </v-card>
          </v-col>

          <v-col cols="6">
            <v-card class="pa-5" color="light-green lighten-3">
              <div class="text-h5 text-left">Podazi o zemljištu</div>

              <v-text-field id="povrsina" type="number" label="Površina"
                            v-model="razvoj.konfiguracija.zemljiste.povrsina"></v-text-field>
              <v-text-field id="duzina" type="number" label="Dužina"
                            v-model="razvoj.konfiguracija.zemljiste.duzina"></v-text-field>
              <v-text-field id="sirina" type="number" label="Širina"
                            v-model="razvoj.konfiguracija.zemljiste.sirina"></v-text-field>
              <v-text-field id="nadmorksaVisina" type="number" label="Nadmorska visina"
                            v-model="razvoj.konfiguracija.zemljiste.nadmorskaVisina"></v-text-field>
              <v-text-field id="humus" type="number" label="Procenat humusa"
                            v-model="razvoj.konfiguracija.zemljiste.procenatHumusa"></v-text-field>
              <v-text-field id="karbonati" type="number" label="Procenat karbonata"
                            v-model="razvoj.konfiguracija.zemljiste.procenatKarbonata"></v-text-field>
              <v-text-field id="ph" type="number" label="pH vrednost"
                            v-model="razvoj.konfiguracija.zemljiste.phVrednost"></v-text-field>
              <v-text-field id="nagib" type="number" label="Nagib zemljišta"
                            v-model="razvoj.konfiguracija.zemljiste.nagibZemljista"></v-text-field>
              <v-select id="tipZemljista" label="Tip zemljišta" :items="tipoviZemljista"
                        v-model="razvoj.konfiguracija.zemljiste.tipZemljista"></v-select>


            </v-card>
          </v-col>

          <v-col cols="6">

          </v-col>
        </v-row>
        <v-btn type="submit" color="lime" :disabled="sending">Kreiraj</v-btn>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
import {Razvoj} from "@/model.js";

export default {
  name: "RazvojDialog",
  props: [
    "value"
  ],
  watch: {
    value(val) {
      this.dValue = val;
    }
  },
  data() {
    return {
      dValue: false,
      razvoj: new Razvoj(),
      tipoviZemljista: [
        "CRNICA", "GAJNJACA", "SMONICA", "ALUVIJALNO", "PEPELJUSA", "CRVENICA"
      ], stanjaZemljista: [
        "TVRDO", "IZORANO", "MEKO", "FINO"
      ],
      sending: false,
    }
  },
  methods: {
    submit(e) {
      e.preventDefault();

      this.sending = true;
      const dis = this;
      const onClose = this.onclose;
      this.$store.dispatch("addRazvoj", this.razvoj).then(() => {
        dis.sending = false;
        onClose()
      }).catch(() => {
        dis.sending = false;

      });

    },
    onclose() {
      console.log("CLOSED")
      this.dValue = false;
      this.$emit("input", false);
    }
  }
}
</script>

<style scoped>

</style>
