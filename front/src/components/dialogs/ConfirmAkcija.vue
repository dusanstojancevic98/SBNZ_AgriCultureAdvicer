<template>
  <v-dialog v-model="show" width="500">
    <v-card color="light-green lighten-5">
      <v-card color="light-green lighten-3" tile class="pa-5">Da li ste sigurni da želite da potvrdite obavljanje ove
        akcije?
      </v-card>
      <div class="pa-5">
        <v-row class="pa-0 ma-0">
          <v-spacer></v-spacer>
          <v-btn small class="mr-2" color="lime lighten-3" @click="confirm">
            <v-icon>mdi-check</v-icon>
            Potvrdi
          </v-btn>
          <v-btn small color="lime lighten-4" @click="close">
            <v-icon>mdi-close</v-icon>
            Otkaži
          </v-btn>
        </v-row>
      </div>
    </v-card>
  </v-dialog>
</template>

<script>
export default {
  name: "ConfirmAkcija",
  props: [
    "show",
    "id",
    "razvojId"
  ],
  methods: {
    confirm() {
      this.$store.dispatch("odradiAkciju", {id:this.id, rid:this.razvojId});
      this.close();
    },
    close() {
      this.$emit("close")
    }
  }

}
</script>

<style scoped>

</style>
