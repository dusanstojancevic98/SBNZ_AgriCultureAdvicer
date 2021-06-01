<template>
  <v-dialog>
    <v-form>
      <v-row>
        <v-col cols="6">

        </v-col>
      </v-row>
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
      razvoj: Razvoj
    }
  }
}
</script>

<style scoped>

</style>
