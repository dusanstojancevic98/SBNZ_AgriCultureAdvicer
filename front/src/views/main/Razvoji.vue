<template>
    <div>
        <v-card class="pa-2 mb-5 link" color="lime" @click="dialog=true;">
            <v-icon large>
                mdi-plus
            </v-icon>
        </v-card>
        <RazvojDialog v-model="dialog"></RazvojDialog>
        <v-expansion-panels accordion   multiple v-model="exp">
            <v-expansion-panel>
                <v-expansion-panel-header color="light-green lighten-3">
                    Novi
                </v-expansion-panel-header>
                <v-expansion-panel-content eager color="light-green lighten-4" class="px-5 pt-5">
                    <RazvojiColumns :razvoji="razvojiInicijalni"></RazvojiColumns>
                </v-expansion-panel-content>
            </v-expansion-panel>

            <v-expansion-panel>
                <v-expansion-panel-header color="light-green lighten-3">
                    U Toku
                </v-expansion-panel-header>
                <v-expansion-panel-content eager color="light-green lighten-4" class="px-5 pt-5">
                    <RazvojiColumns :razvoji="razvojiUToku"></RazvojiColumns>
                </v-expansion-panel-content>
            </v-expansion-panel>

            <v-expansion-panel>
                <v-expansion-panel-header color="light-green lighten-3">
                    Pauzirani
                </v-expansion-panel-header>
                <v-expansion-panel-content eager color="light-green lighten-4" class="px-5 pt-5">
                    <RazvojiColumns :razvoji="razvojiPauzirani"></RazvojiColumns>
                </v-expansion-panel-content>
            </v-expansion-panel>

            <v-expansion-panel>
                <v-expansion-panel-header color="light-green lighten-3">
                    Zaustavljeni
                </v-expansion-panel-header>
                <v-expansion-panel-content eager color="light-green lighten-4" class="px-5 pt-5">
                    <RazvojiColumns :razvoji="razvojiZaustavljeni"></RazvojiColumns>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-expansion-panels>
    </div>


</template>

<script>
import {mapGetters} from "vuex";
import RazvojiColumns from "@/components/RazvojiColumns";
import RazvojDialog from "@/components/RazvojDialog";

export default {
    name: "Razvoji",
    components: {RazvojDialog, RazvojiColumns},
    created() {
        this.$store.dispatch("fetchRazvojiInicijalni");
        this.$store.dispatch("fetchRazvojiPauzirani");
        this.$store.dispatch("fetchRazvojiUToku");
        this.$store.dispatch("fetchRazvojiZaustavljeni");
    },
    data() {
        return {
            dialog: false,
            exp:[0,1]
        }
    },

    computed: {
        ...mapGetters({
            razvojiInicijalni: "getRazvojiInicijalni",
            razvojiUToku: "getRazvojiUToku",
            razvojiPauzirani: "getRazvojiPauzirani",
            razvojiZaustavljeni: "getRazvojiZaustavljeni",
        })
    },

}
</script>

<style scoped>

</style>
