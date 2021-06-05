<template>
    <v-row>
        <v-col cols="6" v-for="r in razvoji" :key="r.id">
          <RazvojCard :razvoj="r"></RazvojCard>
        </v-col>
        <v-col v-if="razvoji.length===0">
            Ova lista razvoja je još uvek prazna.
        </v-col>
    </v-row>
</template>

<script>
import RazvojCard from "@/components/RazvojCard";
export default {
    name: "RazvojiColumns",
  components: {RazvojCard},
  props: [
        "razvoji"
    ],
    mounted() {
        console.log(this.razvoji)
    },
    methods: {
        pokreni(id) {
            this.$store.dispatch("pokreniRazvoj", id);

        },
        obrisi(id) {
            this.$store.dispatch("obrisiRazvoj", id);

        }
    }
}
</script>

<style scoped>

</style>
