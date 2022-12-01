import axios from 'axios'
import router from '@/router'
import {ElMessage} from "element-plus";

// 创建axios实例
const service = axios.create({
    baseURL: 'http://football-api.lsm1998.com/api/v1',
    timeout: 5000,
    headers: {
        'content-type': 'application/json; charset=utf-8'
    }
})

// 请求拦截
service.interceptors.request.use((config) => {
    if (!config.headers) {
        config.headers = {}
    }
    if (config.url === '/user/login') {
        return config
    }
    const token = localStorage.getItem('token')
    if (token) {
        config.headers.authorization = token
    }
    return config
})

// 响应拦截
service.interceptors.response.use((res) => {
    // 请求code
    console.log("请求拦截")
    if (res.status != 200) {
        return Promise.reject(res.data)
    }
    return res.data
}, res => {
    if (res.request.status == 401) {
        router.push("/login")
        return
    }
    ElMessage.error("请求错误")
    console.log("response res=", res)
})

export default service
