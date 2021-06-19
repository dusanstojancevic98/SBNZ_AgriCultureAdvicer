<template>
  <div class="row-scroll pb-2">
    <v-card width="200" class="pa-5 mr-2" color="lime" @click="dialog=true">
      <v-icon large>mdi-plus</v-icon><br>
      Dodaj stanje zemljista
    </v-card>
    <template v-if="razvoj.konfiguracija.zemljiste.trenutnoStanjeZemljista">
      <StanjeZemljista :stanje="razvoj.konfiguracija.zemljiste.trenutnoStanjeZemljista" :trenutno="true"></StanjeZemljista>
      <StanjeZemljista v-for="s in razvoj.konfiguracija.zemljiste.stanjaZemljista" :stanje="s" :key="s.id" :trenutno="false"></StanjeZemljista>
    </template>
    <div v-else class="centered pa-5">
      Trenutno nema stanja zemljista za prikaz.
    </div>
    <StanjeZemljistaDialog v-model="dialog"/>
  </div>

</template>
<script>
import {mapGetters} from "vuex";
import StanjeZemljista from "@/components/blocks/StanjeZemljista";
import StanjeZemljistaDialog from "@/components/dialogs/StanjeZemljistaDialog";

export default {
  components: {StanjeZemljistaDialog, StanjeZemljista},

  name: "RazvojStanjaZemljista",
  data() {
    return {
      dialog: false,
      sending: false
    }
  },
  computed: {
    ...mapGetters({
          razvoj: "getTrenutniRazvoj"
        }
    )

  },
  methods: {

  }
}
</script>
