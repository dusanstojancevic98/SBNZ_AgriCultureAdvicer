<template>
  <Akcije :akcije="razvoj.trenutnaAkcija"></Akcije>
</template>

<script>
import {mapGetters} from "vuex";
import Akcije from "@/components/Akcije";

export default {
  name: "RazvojTrenutneAkcije",
  components: {Akcije},
  computed: {
    ...mapGetters({
          razvoj: "getTrenutniRazvoj"
        }
    )

  }
}
</script>

<style scoped>

</style>
