<template>
  <div class="home">
    <v-row>
      <v-col cols="6" v-for="r in mainRoutes" :key="r.id">
        <v-card :to="r.path" :color="r.color">
          <v-card-title>{{r.name}}</v-card-title>
          <v-img height="400" :src="r.img"></v-img>
        </v-card>
      </v-col>
    </v-row>
  </div>
</template>

<script>

import {mapGetters} from "vuex";

export default {
  name: 'Home',
  computed:{
    ...mapGetters({
      mainRoutes:"getMainRoutes"
    })
  },
  data() {
    return {

    }
  },
  components: {}
}
</script>
