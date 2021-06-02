<template>
    <v-row>
        <v-col cols="6" v-for="r in razvoji" :key="r.id">
            <v-card>
                <v-img height="200" :src="require('../../public/usevi.jpg')">
                    <div class="blur15 fill-parent between-column">
                        <div class="row-stretch  pa-5">
                            <v-chip class="text-body-1" color="light-green lighten-3">{{ r.naziv }}</v-chip>
                        </div>
                        <div class="light-green lighten-3  pa-5">
                            <v-row class="ma-0 pa-0">
                                <v-spacer></v-spacer>
                                <v-btn class="mr-2" color="lime" v-if="r.stanjeRazvoja==='INICIJALNO'"
                                       :to="'/predloguseva/'+r.ponudaUseva.id">Vidi ponude useva
                                </v-btn>
                                <v-btn class="mr-2" color="lime" v-if="r.stanjeRazvoja==='SPREMNO'"
                                       @click="pokreni(r.id)">Pokreni razvoj
                                </v-btn>
                                <v-btn class="mr-2" color="error" @click="obrisi(r.id)">Obriši razvoj</v-btn>
                            </v-row>
                        </div>
                    </div>
                </v-img>
            </v-card>
        </v-col>
        <v-col v-if="razvoji.length===0">
            Ova lista razvoja je još uvek prazna.
        </v-col>
    </v-row>
</template>

<script>
export default {
    name: "RazvojiColumns",
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
