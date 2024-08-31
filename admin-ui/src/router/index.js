import Login from "@/view/login/Login.vue";
import Admin from "@/view/admin/Admin.vue";
import Floor from "@/view/admin/floor/Floor.vue";
import Room from "@/view/admin/room/Room.vue";
import Bed from "@/view/admin/bed/Bed.vue";
import {createRouter, createWebHistory} from "vue-router";

//定义路由关系
const routers = [
    //登录路由
    {path: "/login", component: Login},
    //后台界面路由，采用了子路由
    {
        path: "/",
        component: Admin,
        children:[
            {path:'/bed/floor',component: Floor},
            {path:'/bed/room',component: Room},
            {path:'/bed/bed',component: Bed}
        ]
    },
]
//创建路由器
const router = createRouter({
    history: createWebHistory(),
    routes: routers
})

export {
    router
}
