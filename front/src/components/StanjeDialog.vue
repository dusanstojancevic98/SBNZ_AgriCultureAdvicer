<template>
    <v-dialog v-model="dValue" width="800" @input="onclose">
        <v-card class="pa-5" color="light-green lighten-4">

            <div class="text-h3 pb-5">
                Novo stanje
            </div>
            <v-form @submit="submit">
                <v-row>
                    <v-select
                        v-model="stanje.stanja"
                        :items="uslovi"
                        label="Select"
                        multiple
                        chips
                        hint="Izaberi stanja koja se nalaze na zemljistu"
                        persistent-hint
                    ></v-select>
                </v-row>
                <v-btn type="submit" color="lime" :disabled="sending">Kreiraj</v-btn>
            </v-form>
        </v-card>
    </v-dialog>
</template>

<script>
export default {
    name: "StanjeDialog",
    props: [
        "value"
    ],
    watch:{
      value(val){
          this.dValue = val;
      }
    },
    data() {
        return {
            dValue:false,
            stanje: {
                stanja: [],
                date: new Date()
            },
            sending: false,
            uslovi: [
                "ZeleniList", "ZutiList", "BraonList", "PojedenList", "IskrivljenList",
                "PrisustvoLarvi", "PrisustvoMuva", "PrisustvoStonoga",
                "PrisustvoVisokogKorova", "PisustvoBrsljena", "PrisustvoPalamide"
            ],
        }
    },
    methods: {
        submit(e) {
            e.preventDefault();
            this.sending = true;
            const onClose = this.onclose;
            this.$store.dispatch("addStanje", this.stanje).then(() => {
                this.sending = false;
                onClose()
            }).catch(()=>{
                this.sending = false;

            });

        },
        onclose() {
            this.dValue = false;
            this.$emit("input", false);
        }
    }
}
</script>

<style scoped>

</style>
