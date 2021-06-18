<template>
  <div>
    <v-tabs
        v-model="tab"
        background-color="light-green lighten-3"
        color="green"
        grow
        slider-size="5"
    >
      <v-tab
          v-for="item in tabs"
          :key="item"
      >
        {{ item }}
      </v-tab>
    </v-tabs>

    <v-tabs-items v-model="tab">
      <v-tab-item key="Informacije">
        <RazvojInfo/>
      </v-tab-item>
      <v-tab-item key="Trenutne akcije">
        <RazvojTrenutneAkcije/>
      </v-tab-item>
      <v-tab-item key="Istorija akcija">
        <RazvojIstorija/>
      </v-tab-item>
      <v-tab-item key="Stanja">
        <Stanja/>
      </v-tab-item>
    </v-tabs-items>
  </div>
</template>

<script>
import {mapGetters} from "vuex";
import RazvojInfo from "@/components/RazvojInfo";
import RazvojIstorija from "@/components/RazvojIstorija";
import RazvojTrenutneAkcije from "@/components/RazvojTrenutneAkcije";
import Stanja from "@/components/Stanja"
export default {
  name: "PregledRazvoja",
  components: {RazvojIstorija, RazvojInfo, RazvojTrenutneAkcije, Stanja},
  props: [
    "id"
  ],
  created() {
    this.$store.dispatch("fetchTrenutniRazvoj", this.id)
  },
  computed: {

    ...mapGetters({
      razvoj: "getTrenutniRazvoj"
    })
  },
  data() {
    return {
      tab: "",
      tabs: [
        "Informacije",
        "Trenutne akcije",
        "Istorija akcija",
        "Stanja"
      ]
    }
  }
}
</script>

<style scoped>

</style>
