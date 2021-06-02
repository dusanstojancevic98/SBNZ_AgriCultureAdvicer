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
                    Spremni za pokretanje
                </v-expansion-panel-header>
                <v-expansion-panel-content eager color="light-green lighten-4" class="px-5 pt-5">
                    <RazvojiColumns :razvoji="razvojiSpremni"></RazvojiColumns>
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
    mounted() {
        const dis = this;
        this.$store.dispatch("fetchAll")
        .then(()=>{
            dis.osvezi()
        });
    },
    // beforeUpdate() {
    //     this.osvezi();
    // },
    updated() {
        // this.osvezi();
    },
    data() {
        return {
            dialog: false,
        }
    },
    methods:{
        osvezi(){

        }
    },

    computed: {
        exp(){
            let exp = []
            if(this.razvojiInicijalni.length>0){
                exp.push(0)
            }
            if(this.razvojiSpremni.length>0){
                exp.push(1)
            }
            if(this.razvojiUToku.length>0){
                exp.push(2)
            }
            if(this.razvojiPauzirani.length>0){
                exp.push(3)
            }
            if(this.razvojiZaustavljeni.length>0){
                exp.push(4)
            }
            if(exp.length>0){
                return exp
            }
            return [0, 1]
        },
        ...mapGetters({
            razvojiInicijalni: "getRazvojiInicijalni",
            razvojiUToku: "getRazvojiUToku",
            razvojiPauzirani: "getRazvojiPauzirani",
            razvojiZaustavljeni: "getRazvojiZaustavljeni",
            razvojiSpremni: "getRazvojiSpremni",
        })
    },

}
</script>

<style scoped>

</style>
