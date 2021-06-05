<template>
  <Akcije :akcije="razvoj.istorijaAkcija"></Akcije>
</template>

<script>
import {mapGetters} from "vuex";
import Akcije from "@/components/Akcije";

export default {
  name: "RazvojIstorija",
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
