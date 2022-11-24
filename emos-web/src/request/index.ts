import axios from "axios";

import store from "@/store";

// 创建axios实例
const service = axios.create({
    baseURL: "http://oa.lsm1998.com/api/v1",
    timeout: 5000,
    headers: {
        "content-type": "application/json; charset=utf-8"
    }
})

// 请求拦截
service.interceptors.request.use((config) => {
    if (!config.headers) {
        config.headers = {}
    }
    const token = store.getters.token()
    if (token) {
        config.headers.token = token
    }
    return config
})

// 响应拦截
service.interceptors.response.use((res) => {
    // 请求code
    if (res.status != 200) {
        return Promise.reject(res.data)
    }
    return res.data
}, err => {
    console.log(err)
})

export default service