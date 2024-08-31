<script setup>
import {onBeforeMount, onMounted, ref} from "vue";
import {addFloor, delFloor, editFloor, listFloor, listFloorById, listFloorBySearch} from "@/api/floor.js";
import {ElMessage, ElMessageBox} from "element-plus";

//region dialog参数
//定义一个dialog框的开关
const dialogFormVisible = ref(false)
//定义一个dialog框的标题
const dialogTitle = ref('')
//定义是否显示dialog中的Id输入框
const dialogFormIDInput = ref(false)
//dialog框的类型【1添加，2修改】
const dialogType = ref(1)
//endregion


//region 结果的参数-->
//定义一个floors结果集
const floors = ref([])
//定义一个floor参数对象
const floor = ref({})
//endregion-->

//region 分页和查询参数-->
const query = ref({
  //当前页
  pageNum: 1,
  //每页总行数
  pageSize: 5,
  //总页数
  pages: 0,
  //总行数
  total: 0
})
//endregion-->

//当前页改变事件
const handlePageChange = () => {
  if (isSearch.value) {
    //按条件查询
    getBySearch()
  } else {
    //全查询
    getAll()
  }
}


//定义查询状态
const isSearch = ref(false)
//组合模糊查询
const getBySearch = async () => {

  //修改查询状态为true
  isSearch.value = true

  let res = await listFloorBySearch(query.value)
  //给我数据集
  floors.value = res.data.data
  //设置总页数
  query.value.pages = res.data.pages
  //设置总行数
  query.value.total = res.data.total
}

//重置
const reset = () => {
  //修改查询状态为true
  isSearch.value = false
  getAll()
}

//查询全部
const getAll = async () => {

  let res = await listFloor(query.value)
  //给我数据集
  floors.value = res.data.data
  //设置总页数
  query.value.pages = res.data.pages
  //设置总行数
  query.value.total = res.data.total

}

//按照ID删除
const del = async (id) => {

  try {
    //弹出一个确定框
    await ElMessageBox.confirm("你是否确定要删除", "删除确认", {
      confirmButtonText: '确实',
      cancelButtonText: '取消',
      type: 'warning',
    })
  } catch (e) {
    //你点击取消了
    return
  }

  //请求删除
  let res = await delFloor(id)

  //弹框
  ElMessage({
    type: 'info',
    message: res.data.message
  })

  getAll()
}

//添加按钮【打开添加dialog框】
const addDialog = () => {
  //修改dialog标题
  dialogTitle.value = "添加楼层信息"
  //隐藏ID输入框
  dialogFormIDInput.value = false
  //确定dialog框的类型
  dialogType.value = 1
  //打开dialog
  dialogFormVisible.value = true

}

//修改按钮【打开dialog框】
const edit = async (id) => {
  //修改dialog标题
  dialogTitle.value = "修改楼层信息"
  //隐藏ID输入框
  dialogFormIDInput.value = true
  //确定dialog框的类型
  dialogType.value = 2
  //请求后端按照ID查询
  const res = await listFloorById(id)
  //floor填写数据
  floor.value = res.data.data

  //打开dialog
  dialogFormVisible.value = true

}


//dialog确定按照的执行方法【包含添加和修改】
const dialogAction = async () => {

  console.log("dialogType.value：" + dialogType.value)

  //定义好一个返回的结果
  let res = {}

  switch (dialogType.value) {
    case 1://添加
      //请求添加
      res = await addFloor(floor.value)
      //弹出消息框
      ElMessage({
        type: 'info',
        message: res.data.message
      })

      getAll()
      //清理信息
      //关闭dialog框
      dialogFormVisible.value = false
      //清理输入框数据
      floor.value = {}

      break
    case 2://修改
      //请求修改
      res = await editFloor(floor.value)
      //弹出消息框
      ElMessage({
        type: 'info',
        message: res.data.message
      })
      getAll()
      //清理信息
      //关闭dialog框
      dialogFormVisible.value = false
      //清理输入框数据
      floor.value = {}
      break
  }

}


//开始就是执行全查询
getAll()


</script>
<template>
  <!--  卡片
  包含
  1——标题
  2——表格
  3——脚注
  -->
  <el-card style="max-width: 100%">
    <!--    头信息-->
    <template #header>
      <div class="card-header">
        <span>楼层信息列表</span>
        <br>
        <el-button type="primary" @click="addDialog">添加</el-button>
        <hr>
        <el-form>
          <el-form-item label="名称：">
            <el-input v-model="query.name" clearable/>
          </el-form-item>
        </el-form>
        <el-button type="primary" @click="getBySearch">查询</el-button>
        <el-button type="primary" @click="reset">重置</el-button>

      </div>
    </template>
    <!--    表格信息-->
    <template #default>
      <el-table :data="floors" border style="width: 100%">
        <el-table-column prop="id" label="主键" width="180"/>
        <el-table-column prop="name" label="楼层名称"/>
        <el-table-column label="操作">

          <template #default="scope">
            <el-button type="primary" @click="edit(scope.row.id)">修改</el-button>
            &nbsp;&nbsp;
            <el-button type="primary" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </template>
    <!--    脚注【分页】-->
    <template #footer>
      <el-pagination
          background layout="prev, pager, next,jumper,sizes,total"
          :total="query.total"
          :page-count="query.pages"
          :page-sizes="[5,10,15,20,30]"
          v-model:current-page="query.pageNum"
          v-model:page-size="query.pageSize"
          @change="handlePageChange"
      />
    </template>
  </el-card>


  <!--  dialog弹出框-->
  <el-dialog v-model="dialogFormVisible" :title="dialogTitle" width="500">
    <el-form>
      <el-form-item label="主键：" v-show="dialogFormIDInput">
        <el-input v-model="floor.id" disabled/>
      </el-form-item>
      <el-form-item label="名称：">
        <el-input v-model="floor.name"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible=false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogAction">
          确定
        </el-button>
      </div>
    </template>
  </el-dialog>

</template>

<style scoped>

</style>