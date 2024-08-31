import {defineStore} from "pinia";
import {ref} from "vue";
//使用defineStore函数
//defineStore函数：
//第一个参数为状态值【字符串】【具备唯一性】
//第二个参数【函数】，后续可以使用这些函数
//第二个参数的函数一般有三个，
// 1：获取状态【一般会使用ref应用】
// 2：设置状态
// 3：删除状态【直接将状态值设置为''】
//第三个参数【对象】：是否需要持久化
const userToken = defineStore(
    "token",
    () => {
        //获取状态
        const token = ref('')
        //设置状态
        const setToken = (newToken) => {
            token.value=newToken
        }
        //删除状态
        const removeToken = () => {
            token.value=""
        }
        //返回函数内部的属性
        return{token,setToken,removeToken}
    },
    {
        persist:true
    }
)
//导出
export {
    userToken
}