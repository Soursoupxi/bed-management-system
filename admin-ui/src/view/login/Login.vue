<script setup>
import {ref} from "vue";
import {userToken} from "@/stores/token.js";
import {router} from "@/router/index.js";
import {userLogin} from "@/api/user.js";

//前端需要请求的参数对象
const pram=ref({
  name:'',
  password:''
})


//登录封装【也是登录按钮的点击事件】
const login= async ()=>{

  //调用登录
 let res= await userLogin(pram.value)
  //获取token
  const token=   res.data.data
  //设置token到pinia
  userToken().setToken(token)
  //跳转到admin界面
  router.push("/")
}



</script>

<template>
  <el-row type="flex" justify="center" align="middle" style="height: 100vh;">
    <el-col :xs="8" :sm="8" :md="8" :lg="8" :xl="8">
      <el-card  >
        <template #header>
          <div class="card-header">
            <span>登录</span>
          </div>
        </template>
        <template #default>
          <el-form>
            <el-form-item label="账号：">
              <el-input  v-model="pram.name" />
            </el-form-item>
            <el-form-item label="密码：">
              <el-input  v-model="pram.password"  />
            </el-form-item>
          </el-form>
        </template>

        <template #footer><el-button type="primary" @click="login">登录</el-button></template>
      </el-card>
    </el-col>
  </el-row>

</template>

<style scoped>
</style>