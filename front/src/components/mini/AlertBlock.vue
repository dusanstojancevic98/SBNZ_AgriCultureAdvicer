<template>
  <div class="alert-container pa-0">
    <slot>
    </slot>
    <div v-if="alertNum&&alertNum>0" class="alert-circle error pa-1">
      {{alertNum}}
    </div>
  </div>
</template>

<script>
import {mapGetters} from "vuex";

export default {
    name: "AlertBlock",
    computed: {
        ...mapGetters({
            alertNum:"getAlertNum"
        })
    }
}
</script>

<style scoped>
.alert-container {
  position: relative;
}
.alert-circle{
  z-index: 2;
  border-radius: 50%;
  position: absolute;
  top: 0;
  right: 0;
  transform: translate(50%, -50%);
}
</style>
