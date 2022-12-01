<template>
  <div>
    <h1>主页</h1>

    <p>欢迎你：{{ state.getters.user.realName }}</p>

    <div v-if="pageMode">
      <h3>列表页面</h3>
      <el-button @click="pageMode = !pageMode">投注</el-button>
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="realName" label="投注人" width="180"/>
        <el-table-column prop="content" label="内容" width="180"/>
        <el-table-column prop="schedule" label="场次" width="180"/>
        <el-table-column prop="createdTime" label="投注时间" width="180"/>
        <el-table-column label="状态" width="180">
          <template #default="scope">
            {{ statusMap[scope.row.status] }}
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="操作" width="180">
          <template #default="scope">
            <el-button type="primary" size="small" v-if="scope.row.status === 1">下注</el-button>
            <el-button type="primary" size="small" v-if="scope.row.status === 2">结算</el-button>
            <el-button type="danger" size="small" v-if="scope.row.userId === state.getters.user.id"
                       @click="deleteRow(scope.row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div v-else>
      <h3>投注页面</h3>

      <el-form
          ref="form"
          :model="formData"
          label-width="auto"
          :label-position="'top'"
      >
        <el-form-item label="场次">
          <el-input v-model="formData.schedule"/>
        </el-form-item>
        <el-form-item label="内容">
          <el-input type="textarea" v-model="formData.content"/>
        </el-form-item>
        <el-form-item label="金额">
          <el-input type="number" v-model="formData.amount"/>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submit">提交</el-button>
          <el-button @click="cancel">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script setup lang='ts'>
import {profile, bettingList, saveBetting, deleteBetting} from '@/request/api'
import {useStore} from 'vuex'
import {IBetting} from "@/types/betting";
import {reactive, ref} from "vue";
import {ElMessage} from 'element-plus'

const state = useStore()

const statusMap = {
  "1": "初始化",
  "2": "待结算",
  "3": "已结算",
}

const pageMode = ref(true)

const getProfile = async () => {
  profile().then(res => {
    state.commit('setUser', res.data)
  }).catch(err => {
    console.log('err:', err)
  })
}

const getBettingList = async () => {
  bettingList({page: 1, size: 10}).then(res => {
    tableData.splice(0, tableData.length)
    res.data?.records.forEach((e: IBetting) => {
      tableData.push(e)
    })
  }).catch(err => {
    console.log('err:', err)
  })
}

const setup = () => {
  getProfile()
  getBettingList()
}

const tableData: Array<IBetting> = reactive([])

const formData: IBetting = reactive({
  id: 0,
  content: '',
  amount: 0,
  schedule: '',
  status: 1,
  version: 0,
})

const submit = () => {
  saveBetting(formData).then(_ => {
    ElMessage.success("提交成功")
    cancel()
  }).catch(err => {
    ElMessage.error("提交失败")
    console.log(err)
  })
}

const cancel = () => {
  formData.schedule = formData.content = ''
  formData.amount = 0
  pageMode.value = !pageMode.value
}

const deleteRow = (id: number) => {
  deleteBetting(id).then(_ => {
    ElMessage.success("删除成功")
    getBettingList()
  }).catch(err => {
    ElMessage.error("提交失败")
    console.log(err)
  })
}

setup()
</script>

<style scoped>

</style>
