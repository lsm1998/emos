<template>
  <div>
    <el-row>
      <el-col :span='8'>
        <div class='grid-content ep-bg-purple-dark' />
        <h1 style='left: auto'>主页</h1>
      </el-col>
    </el-row>

    <el-divider />

    <el-row>
      <el-col :span='3'>
        <p>欢迎你：{{ state.getters.user.realName }}</p>
      </el-col>

      <el-col :span='8'>
        <div class='block'>
          <span>日期：</span>
          <el-date-picker
            v-model='selectDate'
            type='daterange'
            value-format='YYYY-MM-DD HH:mm:ss'
            format='YYYY-MM-DD HH:mm:ss'
            range-separator='-'
            start-placeholder='开始日期'
            end-placeholder='结束日期'
            @change='setSelectDate'
          />
        </div>
      </el-col>

      <el-col :span='6'>
        状态：
        <el-select
          v-model='statusList'
          multiple
          placeholder='状态选择'
        >
          <el-option
            v-for='item in statusOptions'
            :key='item.key'
            :label='item.value'
            :value='item.key'
          />
        </el-select>
      </el-col>

      <el-col :span='6'>
        <el-button @click='getBettingList'>查询</el-button>
        <el-button @click='pageMode = !pageMode'>投注</el-button>
      </el-col>
    </el-row>

    <el-divider />

    <el-row>
      <el-col :span='24'>
        <div v-if='pageMode' style='width: 100%'>
          <h3>列表页面</h3>

          <el-table :data='tableData' style='width: 100%'>
            <el-table-column prop='realName' label='投注人' width='180' />
            <el-table-column prop='content' label='内容' width='180' />
            <el-table-column prop='amount' label='金额' width='180' />
            <el-table-column prop='schedule' label='场次' width='180' />
            <el-table-column prop='createdTime' label='投注时间' width='180' />
            <el-table-column label='状态' width='180'>
              <template #default='scope'>
                {{ statusMap[scope.row.status] }}
              </template>
            </el-table-column>
            <el-table-column fixed='right' label='操作' width='180'>
              <template #default='scope'>
                <el-button type='primary' size='small' v-if='scope.row.status === 1' @click='updateRow(scope.row,2)'>下注
                </el-button>
                <el-button type='primary' size='small' v-if='scope.row.status === 2' @click='updateRow(scope.row,3)'>结算
                </el-button>
                <el-button type='danger' size='small' v-if='scope.row.userId === state.getters.user.id'
                           @click='deleteRow(scope.row.id)'>
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>

          <el-pagination layout='prev, pager, next'
                         :total='total' v-model:current-page='page'
                         :page-size='size'
                         @current-change='getBettingList'
          />
        </div>
        <div v-else>
          <h3>投注页面</h3>

          <el-form
            ref='form'
            :model='formData'
            label-width='auto'
            :label-position="'top'"
          >
            <el-form-item label='场次'>
              <el-input v-model='formData.schedule' />
            </el-form-item>
            <el-form-item label='内容'>
              <el-input type='textarea' v-model='formData.content' />
            </el-form-item>
            <el-form-item label='金额'>
              <el-input type='number' v-model='formData.amount' />
            </el-form-item>

            <el-form-item>
              <el-button type='primary' @click='submit'>提交</el-button>
              <el-button @click='cancel'>返回</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
    </el-row>


    <el-divider />

    <p>
      <el-button @click='loginOut'>退出登录</el-button>
    </p>
  </div>
</template>

<script setup lang='ts'>
import { profile, bettingList, saveBetting, deleteBetting, updateBetting } from '@/request/api'
import { useStore } from 'vuex'
import { IBetting, IBettingQuery } from '@/types/betting'
import { reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import router from '@/router'

const state = useStore()

const statusMap = {
  '1': '初始化',
  '2': '待结算',
  '3': '已结算'
}

const statusOptions = [{
  key: 1,
  value: '初始化'
}, {
  key: 2,
  value: '待结算'
}, {
  key: 3,
  value: '已结算'
}]

const statusList = ref([])

const selectDate = ref(['',''])


const pageMode = ref(true)

const total = ref(0)

const page = ref(1)
const size = ref(10)

const setSelectDate = () => {
  if (selectDate.value[1].length > 0) {
    selectDate.value[1] = selectDate.value[1].replace("00:00:00","23:59:59")
  }
}

const getProfile = async () => {
  profile().then(res => {
    state.commit('setUser', res.data)
  }).catch(err => {
    console.log('err:', err)
  })
}

const getBettingList = async () => {
  const query: IBettingQuery = { page: page.value, size: size.value, statusList: statusList.value }
  if (selectDate.value && selectDate.value.length === 2) {
    if (selectDate.value[0].length > 0) {
      query.startTime = selectDate.value[0]
    }
    if (selectDate.value[1].length > 0) {
      query.endTime = selectDate.value[1]
    }
  }
  bettingList(query).then(res => {
    tableData.splice(0, tableData.length)
    res.data?.records.forEach((e: IBetting) => {
      tableData.push(e)
    })
    total.value = res.data?.total
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
  version: 0
})

const submit = () => {
  saveBetting(formData).then(_ => {
    ElMessage.success('提交成功')
    cancel()
  }).catch(err => {
    ElMessage.error('提交失败')
    console.log(err)
  })
}

const cancel = () => {
  formData.schedule = formData.content = ''
  formData.amount = 0
  pageMode.value = !pageMode.value
}

const deleteRow = (id: number) => {
  ElMessageBox.confirm(
    '是否确认删除?',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  )
    .then(() => {
      deleteBetting(id).then(_ => {
        ElMessage.success('删除成功')
        getBettingList()
      }).catch(err => {
        ElMessage.error('提交失败')
        console.log(err)
      })
    })
}

const updateRow = (row: IBetting, status: number) => {
  row.status = status
  ElMessageBox.confirm(
    '是否确认投注/结算?',
    '提示',
    {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }
  )
    .then(() => {
      updateBetting(row).then(_ => {
        ElMessage.success('修改成功')
        getBettingList()
      }).catch(err => {
        ElMessage.error('修改失败')
        console.log(err)
      })
    })
}

const loginOut = () => {
  localStorage.removeItem('token')
  ElMessage.success('退出成功')
  router.push('/login')
}

setup()
</script>

<style scoped>

</style>
