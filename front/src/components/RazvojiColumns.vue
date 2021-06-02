<template>
    <v-row>
        <v-col cols="6" v-for="r in razvoji" :key="r.id">
            <v-card>
                <v-card-title>{{ r.name }}</v-card-title>
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
    ]
}
</script>

<style scoped>

</style>
