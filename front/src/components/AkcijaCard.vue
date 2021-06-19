<template>
  <v-card width="200" min-width="200" key="a.id" color="light-green lighten-5" class="pa-2 mr-2">
    <v-row class="ma-0 pa-0">
      <div class="centered">
        <div class="text-left">{{a.naziv}}</div>
      </div>
      <v-spacer></v-spacer>
      <v-btn color="yellow" icon><v-icon>mdi-information-variant</v-icon></v-btn>
      <v-btn @click="confirm=true" color="lime" icon><v-icon>mdi-check</v-icon></v-btn>
      <v-btn @click="cancel=true" color="red lighten-3" icon><v-icon>mdi-close</v-icon></v-btn>
    </v-row>
    <ConfirmAkcija :id="a.id" :razvojId="razvoj.id" @close="confirm=false" :show="confirm"></ConfirmAkcija>
    <CancelAkcija :id="a.id" :razvojId="razvoj.id" d="" @close="cancel=false" :show="cancel"></CancelAkcija>
  </v-card>
</template>

<script>
import CancelAkcija from "@/components/dialogs/CancelAkcija";
import ConfirmAkcija from "@/components/dialogs/ConfirmAkcija";
import {mapGetters} from "vuex";
export default {
name: "AkcijaCard",
  components: {ConfirmAkcija, CancelAkcija},
  props:[
      "a"
  ],
  computed:{
    ...mapGetters({
      razvoj:"getTrenutniRazvoj"
    }),
  },
  mounted() {
    console.log("AKCIJA", this.a)
  },
  data(){
    return{
      confirm:false,
      cancel:false
    }
  }
}
</script>

<style scoped>

</style>
