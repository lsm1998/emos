<template>
  <div>
    <h1>主页</h1>

    <p>欢迎你：{{ state.getters.user.realName }}</p>


  </div>
</template>

<script setup lang='ts'>
import { profile, bettingList } from '@/request/api'
import { useStore } from 'vuex'
import { ElMessage } from 'element-plus'

const state = useStore()

const getProfile = () => {
  profile().then(res => {
    state.commit('setUser', res.data)
    console.log('profile res:', res)
  }).catch(err => {
    ElMessage.error(err)
    console.log('err:', err)
  })
}

const getBettingList = () => {
  bettingList().then(res => {
    console.log(res.data)
  }).catch(err => {
    ElMessage.error(err)
    console.log('err:', err)
  })
}

const setup = () => {
  getProfile()
  getBettingList()
}

setup()
</script>

<style scoped>

</style>
