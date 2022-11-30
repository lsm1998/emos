<template>
  <div>
    <h1>主页</h1>

    <button @click="getProfile">个人信息</button>
  </div>
</template>

<script setup lang="ts">
import {profile} from "@/request/api";

const getProfile = () => {
  profile().then(res => {
    console.log("profile res:", res)
  }).catch(err => {
    console.log("err:", err)
  })
}

// setup()
</script>

<style scoped>

</style>
