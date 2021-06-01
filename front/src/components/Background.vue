<template>
  <div class="background" >
        <img class="image" :src="src">
        <div class="absolute-frame blur15 fill-parent" style="z-index: 1"></div>
  </div>
</template>

<script>
export default {
name: "Background",
  props:[
      "src"
  ]
}
</script>

<style scoped>

  div.background {
    /*background-size: cover;*/
    /*background-position: center;*/
    /*background-attachment: fixed;*/
    position: fixed;
    top: 0;
    left: 0;
    z-index: 0;
    height: 100vh;
    width: 100vw;
    /* filter: blur(1px); */
  }
</style>
