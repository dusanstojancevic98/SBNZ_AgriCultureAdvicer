<template>
  <div class="row-scroll pb-2">
    <v-card width="200" class="pa-5 mr-2" color="lime" @click="dialog=true">
      <v-icon large>mdi-plus</v-icon><br>
      Dodaj stanje useva
    </v-card>
    <template v-if="razvoj.odabraniUsev.stanjaUseva">
      <StanjeUseva v-for="s in razvoj.konfiguracija.zemljiste.trenutanUsev.stanjaUseva" :stanje="s" :key="s.id" :trenutno="false"></StanjeUseva>
    </template>
    <div v-else class="centered pa-5">
      Trenutno nema stanja useva za prikaz.
    </div>

    <StanjeDialog v-model="dialog"/>
  </div>

</template>
<script>
import {mapGetters} from "vuex";
import StanjeUseva from "@/components/blocks/StanjeUseva";
import StanjeDialog from "@/components/dialogs/StanjeDialog";

export default {
  components: {StanjeDialog, StanjeUseva},

  name: "RazvojStanjaUseva",
  data() {
    return {
      dialog: false,
    }
  },
  computed: {
    ...mapGetters({
          razvoj: "getTrenutniRazvoj"
        }
    )

  },
  methods: {
    submit(e) {
      e.preventDefault();
      let dis = this;
      dis.sending = true;
      this.$store.dispatch("addStanjeZemljista", {
        rid: this.razvoj.id,
        stanje: this.stanje
      }).then(() => {
        dis.sending = false;
        dis.dialog = false;
      }).catch(() => {
        dis.sending = false;
        dis.dialog = false;
      })
    }
  }
}
</script>
