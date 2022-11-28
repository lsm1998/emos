<template>
  <el-form
    ref="registerForm"
    label-width="120px"
    class="registerForm sign-up-form"
  >
  <el-form-item label="用户名" prop="name">
    <el-input
      placeholder="输入用户名"
    />
  </el-form-item>
  <el-form-item label="邮箱" prop="email">
    <el-input
      placeholder="输入邮箱"
    />
  </el-form-item>
  <el-form-item label="确认密码" prop="password">
    <el-input
      type="password"
      placeholder="输入密码"
    />
  </el-form-item>
  <el-form-item label="密码" prop="password2">
    <el-input
      type="password"
      placeholder="再次输入密码"
    />
  </el-form-item>
  <el-form-item label="身份">
    <el-select placeholder="请选择身份">
      <el-option label="用户" value="user">用户</el-option>
      <el-option label="游客" value="visitor">游客</el-option>
    </el-select>
  </el-form-item>
  <el-form-item>
    <!-- 提交前进行校验 -->
    <el-button type="primary" class="submit-btn"
      >注册</el-button>
  </el-form-item>
</el-form>
</template>

<script lang="ts" setup>
</script>

<style scoped>

</style>