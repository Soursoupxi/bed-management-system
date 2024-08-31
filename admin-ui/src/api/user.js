import {instance} from "@/axios/request.js";

//登录
const userLogin=(pram)=>{
    //前端采用了x-www-form-urlencoded【表单传参法】
    const pramForm= new URLSearchParams()
    //转换JSON参数为URLSearchParams参数
    for (let item in pram){

        pramForm.append(item,pram[item])
    }
    return instance.post("/user/login",pramForm)
}

//登出
const userLogout=()=>{
    return instance.get("/user/logout")
}
export {
    userLogin,userLogout
}