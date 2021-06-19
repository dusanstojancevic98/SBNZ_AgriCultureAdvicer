<template>
  <v-overlay
    v-model="loading"
    color="light-green"
  >
    <v-progress-circular color="green" indeterminate>
    </v-progress-circular>
  </v-overlay>
</template>

<script>
import {mapGetters} from "vuex";

export default {
name: "Loading",
  computed:{
    ...mapGetters({
      loading:"getLoading"
    })
  }

}
</script>

<style scoped>

</style>
