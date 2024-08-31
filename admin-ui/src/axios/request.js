import axios from "axios";
import {userToken} from "@/stores/token.js";
import {ElMessage} from "element-plus";
import {router} from "@/router/index.js";

 const instance=   axios.create({
     //设置基本URL
     baseURL: "/api"
 })

//创建【请求】拦截器interceptors.request.use

instance.interceptors.request.use(
    (config)=>{

        console.log("instance.interceptors.request已经执行")

        const tokenStore= userToken()
        if(tokenStore.token){
            config.headers.token=tokenStore.token
        }
        return config;
    },
    (error)=>{
        //这里直接设置异步错误即可
        //因为本身axios就是异步基础设计的
        return Promise.reject(error);
    }
)

//创建【响应】拦截器interceptors.response.use
instance.interceptors.response.use(
    (res)=>{
        //统一处理返回正确的信息
        if (res.data.success) {
            return res;
        }
        //如果错误则，统一弹框
        //弹出消息框
        ElMessage({
            type: 'error',
            message: res.data.message
        })
        //设置为响应失败
        return Promise.reject(res);
    },
    (error)=>{
        //在没有登录的情况下，会直接跳转到/login页面
        if(error.response.status === 444){
            ElMessage.error("你还没有登录，请登录")
            router.push('/login')
        }
        return Promise.reject(error);
    }
)

export {
    instance
}