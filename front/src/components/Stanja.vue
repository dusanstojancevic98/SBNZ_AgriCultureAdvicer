<template>
  <div>
      <v-card  color="light-green lighten-5">
          <v-card class="pa-2 mb-5 link" color="lime" @click="dialog=true;">
            Dodaj stanje
            <v-icon large>
                mdi-plus
            </v-icon>
          </v-card>
          <StanjeDialog  v-model="dialog"/>
          <v-row class="ml-4 mr-4" v-for="stanje in stanja" :key="stanje.id">
            <v-col>
           <v-text-field
            :value="new Date(stanje.date).toLocaleDateString('sr-RS')"
            label="Datum"
            readonly
          ></v-text-field>
            </v-col>
            <v-col>

              <v-select
                :items="stanje.stanja"
                label="Stanja"
                readonly
                multiple
                chips
                v-model="stanje.stanja"
              ></v-select>
            </v-col>
          </v-row>
      </v-card>
  </div>
</template>

<script>
import StanjeDialog from "@/components/StanjeDialog"
import {mapGetters} from "vuex";
export default {
  components: {StanjeDialog},

  computed: {
    ...mapGetters({
      stanja:"getStanja"
    })
  },

  data() {
    return {
      dialog: false
    }
  },
  mounted(){
    const idRazvoj = this.$route.params.id;
    this.$store.dispatch("fetchStanja", idRazvoj);
  }
}
</script>

<style>

</style>