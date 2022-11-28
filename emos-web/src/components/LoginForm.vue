<template>
  <!-- model 在校验以及重置输入的时候必填 -->
  <el-form
    ref="loginForm"
    label-width="120px"
    class="loginForm sign-in-form"
  >
    <el-form-item label="邮箱" prop="email">
      <el-input
        placeholder="输入邮箱"
      />
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input
        type="password"
        placeholder="输入密码"
      />
    </el-form-item>
    <el-form-item>
      <!-- 提交前进行校验 -->
      <el-button type="primary" class="submit-btn"
        >登陆</el-button>
    </el-form-item>
    <!-- 找回密码 -->
    <div class="tiparea">
      <p>忘记密码？ <button>立即找回</button></p>
    </div>
  </el-form>
</template>

<script lang="ts" setup>
</script>

<style scoped>
/* form */
.loginForm {
  margin-top: 20px;
  background-color: #fff;
  padding: 20px 40px 20px 20px;
  border-radius: 5px;
  box-shadow: 0px 5px 10px #cccc;
}

.submit-btn {
  width: 100%;
}
.tiparea {
  text-align: right;
  font-size: 12px;
  color: #333;
}
.tiparea p button {
  color: #409eff;
  background-color: #fff;
  border: inherit;
  cursor: pointer;
}

</style>