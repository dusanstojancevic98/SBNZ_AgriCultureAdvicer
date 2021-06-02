<template>
    <v-card :color="odabran?'green':'light-green lighten-4'" class="pa-3 grower" @click="odaberi()">
        <v-card-title>
            <v-row>
                {{ usev.naziv }}
                <v-spacer>
                </v-spacer>
                <v-chip class="mr-5" :color="odabran?'green darken-2':'light-green lighten-3'">{{ usev.tipUseva }}</v-chip>
                <v-chip  :color="odabran?'green darken-2':'light-green lighten-3'">{{ usev.podTipUseva }}</v-chip>
            </v-row>
        </v-card-title>
        <v-card class="pa-5" :color="odabran?'green darken-2':'light-green lighten-3'">
            {{ usev.opis }}
        </v-card>
    </v-card>
</template>

<script>
export default {
    name: "Usev",
    props: [
        "usev",
        "odabran"
    ],
    methods: {
        odaberi() {
            this.$emit('select', this.usev.id);
        }
    }
}
</script>

<style scoped>

</style>
