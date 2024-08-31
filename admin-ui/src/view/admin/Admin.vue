<script setup>

import Aside from "@/view/admin/aside/Aside.vue";
import Foot from "@/view/admin/foot/Foot.vue";
import Main from "@/view/admin/main/Main.vue";
import Head from "@/view/admin/head/Head.vue";
import {router} from "@/router/index.js";


router.push("/bed/floor")
</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-header class="my-head">
        <Head></Head>

      </el-header>
      <el-container>
        <el-aside class="my-aside" width="200px">
          <Aside></Aside>
        </el-aside>
        <el-container>
          <el-main class="my-main">


            <Main></Main>

          </el-main>
          <el-footer class="my-head">

            <Foot></Foot>

          </el-footer>
        </el-container>
      </el-container>
    </el-container>
  </div>
</template>

<style scoped>

.my-head{
  background-color: #c6e2ff;
}
.my-main{
  background-color: #ecf5ff;

}

.my-aside{
  background-color:#d9ecff;
  height: 100vh;
}

</style>