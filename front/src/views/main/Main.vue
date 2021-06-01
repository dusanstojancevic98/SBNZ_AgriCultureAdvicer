<template>
  <div>
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

export default {
name: "Main.vue",
  components:{
    Header
  }
}
</script>

<style scoped>

</style>
