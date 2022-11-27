<template>
  <div class="login-box">
    <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        status-icon
        :rules="rules"
        label-width="80px"
        class="demo-ruleForm"
    >
      <h2>后台管理系统</h2>
      <el-form-item label="账号" prop="username">
        <el-input v-model="ruleForm.username" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
            v-model="ruleForm.password"
            type="password"
            autocomplete="off"
        />
      </el-form-item>
      <el-form-item>
        <el-button class="loginBtn" type="primary" @click="submitForm(ruleFormRef)">
          Submit
        </el-button>
        <el-button class="loginBtn" @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, reactive, ref, toRefs} from 'vue';

import {LoginData} from "@/types/login";
import {FormInstance} from "element-plus";
import {login} from '@/request/api';

export default defineComponent({
  setup() {
    const data: LoginData = reactive(new LoginData())

    const rules = {
      username: [{trigger: 'blur', required: true, message: "请输入账号"}],
      password: [{trigger: 'blur', required: true, message: "请输入密码"}],
    }

    // 登录
    const ruleFormRef = ref<FormInstance>()

    const submitForm = (formE: FormInstance | undefined) => {
      if (!formE) return
      formE.validate((isValid) => {
        if (isValid) {
          login(data.ruleForm)
        } else {
          return false
        }
      })
    }

    // 重置
    const resetForm = () => {
      data.ruleForm.password = ""
      data.ruleForm.username = ""
    }
    return {...toRefs(data), rules, resetForm, ruleFormRef, submitForm}
  }
})
</script>

<style lang="scss" scoped>
.login-box {
  width: 100%;
  height: 100%;
  background: url("../assets/bg.jpg");
  text-align: center;
  overflow: hidden;

  .demo-ruleForm {
    width: 500px;
    margin: 200px auto;
    background-color: white;
    padding: 30px;
    border-radius: 40px;
  }

  .loginBtn {
    width: 48%;
  }

  h2 {
    margin-bottom: 10px;
  }
}
</style>
