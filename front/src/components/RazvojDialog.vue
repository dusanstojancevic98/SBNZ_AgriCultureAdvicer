<template>
    <v-dialog v-model="value">
        <div class="text-h1">
            Novi plan razvoja
        </div>
        <v-form @submit="submit">
            <v-row>
                <v-col cols="12">
                    <v-card>
                        <div class="text-h3">Osnovne informacije</div>
                        <v-text-field label="Budžet" type="number" v-model="razvoj.budzet"></v-text-field>
                        <v-date-picker type="number" v-model="razvoj.pocetakSadjenja"></v-date-picker>
                    </v-card>
                </v-col>

                <v-col cols="6">
                    <v-card>
                        <div class="text-h3">Podazi o zemljištu</div>

                        <v-text-field type="number" v-model="razvoj.konfiguracija.zemljiste.povrsina"></v-text-field>
                        <v-text-field type="number" v-model="razvoj.konfiguracija.zemljiste.duzina"></v-text-field>
                        <v-text-field type="number" v-model="razvoj.konfiguracija.zemljiste.sirina"></v-text-field>
                        <v-text-field type="number"
                                      v-model="razvoj.konfiguracija.zemljiste.nadmorskaVisina"></v-text-field>
                        <v-text-field type="number"
                                      v-model="razvoj.konfiguracija.zemljiste.procenatHumusa"></v-text-field>
                        <v-text-field type="number"
                                      v-model="razvoj.konfiguracija.zemljiste.procenatKarbonata"></v-text-field>
                        <v-text-field type="number" v-model="razvoj.konfiguracija.zemljiste.phVrednost"></v-text-field>
                        <v-text-field type="number"
                                      v-model="razvoj.konfiguracija.zemljiste.nagibZemljista"></v-text-field>
                        <v-select v-model="razvoj.konfiguracija.zemljiste.tipZemljista"></v-select>
                    </v-card>
                </v-col>

                <v-col cols="6">
                    <v-card>
                        <div class="text-h3">Podazi o zemljištu</div>
                        <v-text-field type="number" v-model="razvoj.konfiguracija.vremenskiPodaci.brojSuncanihDana"></v-text-field>
                        <v-text-field type="number" v-model="razvoj.konfiguracija.vremenskiPodaci.padavine"></v-text-field>
                        <v-text-field type="number" v-model="razvoj.konfiguracija.vremenskiPodaci.prosecnaGodisnjaTemperatura"></v-text-field>

                    </v-card>
                </v-col>
            </v-row>
            <v-btn type="submit" color="primary">Kreiraj</v-btn>
        </v-form>
    </v-dialog>
</template>

<script>
import {Razvoj} from "@/model.ts";

export default {
    name: "RazvojDialog",
    props: [
        "value"
    ],
    data() {
        return {
            razvoj: new Razvoj(),

        }
    },
    methods:{
        submit(e){
            e.preventDefault();
            const dis = this;
            this.$store.dispatch("addRazvoj", this.razvoj).then(()=>{
                dis.$router.push("/razvoji")
            });

        }
    }
}
</script>

<style scoped>

</style>
