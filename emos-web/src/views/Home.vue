<template>
  <div>
    <h1>主页</h1>
  </div>
</template>

<script setup lang="ts">
import {profile} from "@/request/api";

const setup = () => {
  profile().then(res => {
    // eslint-disable-next-line
    debugger
    console.log("profile res:", res)
  }).catch(err => {
    console.log("err:", err)
  })
}

setup()
</script>

<style scoped>

</style>
