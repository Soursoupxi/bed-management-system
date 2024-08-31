<script setup>
import {
  Document,
  Menu as IconMenu,
  Location,
  Setting,
} from '@element-plus/icons-vue'
import {userLogout} from "@/api/user.js";
import {ElMessage} from "element-plus";
import {router} from "@/router/index.js";


const logout=async ()=>{
  let res=await  userLogout()
  ElMessage.success(res.data.message)
  router.push("/login")
}
</script>

<template>
  <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      router
  >
    <el-sub-menu index="1">
      <template #title>
        <el-icon>
          <location/>
        </el-icon>
        <span>病床管理</span>
      </template>
      <el-menu-item index="/bed/floor">楼层管理</el-menu-item>
      <el-menu-item index="/bed/room">房间管理</el-menu-item>
      <el-menu-item index="/bed/bed">床铺管理</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="2">
      <template #title>
        <el-icon>
          <location/>
        </el-icon>
        <span>系统管理</span>
      </template>
      <el-menu-item @click="logout">登出系统</el-menu-item>
      <el-menu-item index="1-2">学生管理</el-menu-item>
      <el-menu-item index="1-2">成绩管理</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>

<style scoped>

</style>