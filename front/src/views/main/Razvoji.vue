<template>
  <div>
    <v-card class="pa-2 link" color="lime" @click="dialog=true;">
      <v-icon large>
        mdi-plus
      </v-icon>
    </v-card>
      <RazvojDialog v-model="dialog"></RazvojDialog>
    <v-expansion-panels>
      <v-expansion-panel>
        <v-expansion-panel-header>
          Novi
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <RazvojiColumns :razvoji="razvojiInicijalni"></RazvojiColumns>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header>
          U Toku
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <RazvojiColumns :razvoji="razvojiUToku"></RazvojiColumns>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header>
          Pauzirani
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <RazvojiColumns :razvoji="razvojiPauzirani"></RazvojiColumns>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <v-expansion-panel>
        <v-expansion-panel-header>
          Zaustavljeni
        </v-expansion-panel-header>
        <v-expansion-panel-content>
          <RazvojiColumns :razvoji="razvojiZaustavljeni"></RazvojiColumns>
        </v-expansion-panel-content>
      </v-expansion-panel>
    </v-expansion-panels>
  </div>


</template>

<script>
import {mapGetters} from "vuex";
import RazvojiColumns from "@/components/RazvojiColumns";
import RazvojDialog from "@/components/RazvojDialog";

export default {
  name: "Razvoji",
  components: {RazvojDialog, RazvojiColumns},
  created() {
    this.$store.dispatch("fetchRazvojiInicijalni");
    this.$store.dispatch("fetchRazvojiPauzirani");
    this.$store.dispatch("fetchRazvojiUToku");
    this.$store.dispatch("fetchRazvojiZaustavljeni");
  },
  data() {
    return {
        dialog:false
    }
  },

  computed: {
    ...mapGetters({
      razvojiInicijalni: "getRazvojiInicijalni",
      razvojiUToku: "getRazvojiUToku",
      razvojiPauzirani: "getRazvojiPauzirani",
      razvojiZaustavljeni: "getRazvojiZaustavljeni",
    })
  },

}
</script>

<style scoped>

</style>
