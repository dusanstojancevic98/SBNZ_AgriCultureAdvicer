<template>
  <v-dialog width="800" v-model="dialog" @input="onclose">
    <v-card class="pa-5" color="light-green lighten-4">
      <div class="text-h5 pb-5">
        Novo stanje zemljista
      </div>
      <v-form @submit="submit">
        <v-select class="mb-5" id="tipZemljista" label="Stanje zemljišta" :items="stanjaZemljista"
                  v-model="stanje.stanje"></v-select>
        <v-progress-linear
            class="mb-5"
            id="procenatVlage"
            v-model="stanje.procenatVlage"
            color="green"
            height="25"
        >
          <strong>Procenat vlage: {{ Math.ceil(stanje.procenatVlage) }}%</strong>
        </v-progress-linear>

        <v-btn type="submit" color="lime" :disabled="sending">Kreiraj</v-btn>
      </v-form>
    </v-card>
  </v-dialog>
</template>

<script>
import {StanjeZemljista} from "@/model";
import {mapGetters} from "vuex";

export default {
  name: "StanjeDialog",
  props: [
    "value",
  ],
  computed:{
    ...mapGetters({
      razvoj:"getTrenutniRazvoj"
    })
  },
  watch: {
    value(val) {
      this.dialog = val;
    }
  },
  data() {
    return {
      dialog: false,
      stanje: new StanjeZemljista(),

      stanjaZemljista: [
        "TVRDO", "IZORANO", "MEKO", "FINO", "ISPUCALO"
      ],
      sending: false,
    }
  },
  methods: {
    submit(e) {
      e.preventDefault();
      const dis = this;
      dis.sending = true;
      this.$store.dispatch("addStanjeZemljista", {
        rid: this.razvoj.id,
        stanje: this.stanje
      }).then(() => {
        dis.sending = false;
        dis.onclose();
      }).catch(() => {
        dis.sending = false;
        dis.onclose();
      })
    },
    onclose() {
      this.dialog = false;
      this.$emit("input", false);
    }
  }
}
</script>

<style scoped>

</style>
