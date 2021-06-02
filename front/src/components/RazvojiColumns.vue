<template>
    <v-row>
        <v-col cols="6" v-for="r in razvoji" :key="r.id">
            <v-card color="light-green lighten-3" class="pa-5">
                <v-card-title>{{ r.naziv }}</v-card-title>
                <v-btn color="lime" v-if="r.stanjeRazvoja==='INICIJALNO'" :to="'/predloguseva/'+r.ponudaUseva.id">Vidi ponude useva</v-btn>
            </v-card>
        </v-col>
        <v-col v-if="razvoji.length===0">
            <v-card color="yellow lighten-2" class="pa-5">
                Ova lista razvoja je još uvek prazna.
            </v-card>
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
    }
}
</script>

<style scoped>

</style>
