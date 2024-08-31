import './assets/base.css'
import { createApp } from 'vue'
import App from './App.vue'
const app = createApp(App)

//region Element-Plus
import ElementPlus from 'element-plus'
//导入ElementPlus中文国际化
import zhLocale from 'element-plus/es/locale/lang/zh-cn'
import 'element-plus/dist/index.css'
app.use(ElementPlus,{ locale: zhLocale })
//endregion

//region 使用Router路由
//导入路由
import {router} from "@/router/index.js";
//使用路由
app.use(router)
//endregion

//region 使用状态管理器pinia
//导入状态管理器pinia
import {createPinia} from "pinia";
//创建状态管理器
const pinia= createPinia()
//创建状态管理器的持久化
import {createPersistedState} from "pinia-persistedstate-plugin";
const persisted=createPersistedState()
//使用持久化
pinia.use(persisted)
//使用状态
app.use(pinia)
//endregion

app.mount('#app')