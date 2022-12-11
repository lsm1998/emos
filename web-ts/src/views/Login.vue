<template>
  <div class='login'>
    <div class='header'>
      <span class='header-logo'>
        <i class='iconfont icon-vue'></i>
        <i class='iconfont icon-icon-test'></i>
        <i class='iconfont icon-typescript'></i>
      </span>
      <span class='header-title'>在线考勤系统</span>
    </div>
    <div class='desc'>
      零基础从入门到进阶，系统掌握前端三大热门技术(Vue、React、TypeScript)
    </div>
    <el-form
      ref='ruleFormRef'
      :model='ruleForm'
      :rules='rules'
      label-width='120px'
      class='main'
    >
      <el-form-item label='账号' prop='username'>
        <el-input
          v-model='ruleForm.username'
          type='text'
          placeholder='请输入邮箱'
        />
      </el-form-item>
      <el-form-item label='密码' prop='password'>
        <el-input
          v-model='ruleForm.password'
          type='password'
          placeholder='请输入密码'
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type='primary'
          @click='submitForm(ruleFormRef)'
          auto-insert-space
        >登录
        </el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup lang='ts'>
import { defineComponent, reactive, ref } from 'vue'
import type { FormInstance, FormRules } from 'element-plus'
import { ElMessage } from 'element-plus'
import IUser from '@/types/user'
import { useStore } from '@/store'
import { useRouter } from 'vue-router'

const store = useStore()
const router = useRouter()

defineComponent({
  name: 'LoginView'
})

const ruleFormRef = ref<FormInstance>()

const ruleForm = reactive<IUser>({
  username: '',
  password: ''
})

const rules = reactive<FormRules>({
  username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
  password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
})

const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      store.dispatch('users/login', ruleForm).then((res) => {
        if (res.data.errcode === 0) {
          store.commit('users/updateToken', res.data.token)
          ElMessage.success('登录成功')
          router.push('/')
        } else {
          ElMessage.error('登录失败')
        }
      }).catch(err => {
        console.log(err)
        ElMessage.error('登录失败')
      })
    }
  })
}
</script>

<style scoped lang='scss'>
.login {
  width: 100vw;
  height: 100vh;
  background: url('@/assets/images/login-bg.svg') no-repeat center 110px;
  background-size: 100%;

  .header {
    height: 44px;
    line-height: 44px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 34px;
    padding-top: 100px;

    .header-logo {
      .icon-vue,
      .icon-icon-test,
      .icon-typescript {
        margin-right: 5px;
        font-size: inherit;
      }

      .icon-vue {
        color: green;
      }

      .icon-icon-test {
        color: #deb887;
      }

      .icon-typescript {
        color: blue;
      }
    }

    .header-title {
      margin-left: 30px;
      font-weight: 700;
      font-size: 30px;
    }
  }

  .desc {
    text-align: center;
    padding-top: 30px;
    color: rgba(0, 0, 0, 0.45);
    font-size: 16px;
  }

  .main {
    width: 500px;
    margin: 0 auto;
    padding-top: 50px;
  }

  .users {
    width: 500px;
    margin: 60px auto;
    color: rgba(0, 0, 0, .65);

    h3 {
      font-size: 16px;
    }

    p {
      margin: 20px;
    }
  }
}
</style>
