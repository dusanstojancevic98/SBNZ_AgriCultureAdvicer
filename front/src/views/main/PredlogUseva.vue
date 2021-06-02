<template>
    <v-card color="light-green lighten-5" class="pa-5">
        <v-row>
            <v-col cols="4" v-for="usev in ponuda.ponude" :key="usev.id">
                <Usev :odabran="usev.id===usev_id" @select="odaberi(usev.id)" :usev="usev"></Usev>
            </v-col>
        </v-row>
        <v-row class="ma-0 mt-5 pa-0">
            <v-spacer>

            </v-spacer>
            <v-btn color="lime" @click="potvrdi">
                Potvrdi odabir useva
            </v-btn>
        </v-row>
    </v-card>
</template>

<script>
import {mapGetters} from "vuex";
import Usev from "@/components/Usev";

export default {
    name: "PredlogUseva",
    props:[
      "id"
    ],
    data(){
      return{
          usev_id:""
      }
    },
    created() {
        this.$store.dispatch("fetchPonudeUseva", this.id)
    },
    computed: {
        ...mapGetters({
                ponuda: "getPonudeUseva"
            }
        )
    },
    methods:{
      potvrdi(){

      },
        odaberi(id) {
          if(this.usev_id===id){
              this.usev_id = ""
          }else{
              this.usev_id = id;
          }
        }
    },
    components:{
        Usev
    }
}
</script>

<style scoped>

</style>
