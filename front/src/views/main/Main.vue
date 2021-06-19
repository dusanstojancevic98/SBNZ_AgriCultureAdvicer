<template>
  <div>
    <Loading/>
    <Header/>
    <v-main style="margin-top: 30px">
      <v-container>
        <router-view></router-view>
      </v-container>
    </v-main>
  </div>
</template>

<script>


import Header from "@/components/Header";
import Loading from "@/components/widgets/Loading";

export default {
name: "Main.vue",
  components:{
    Loading,
    Header
  }
}
</script>

<style scoped>

</style>
