import axios from 'axios'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://127.0.0.1:9876/api/v1',
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
  if (res.status != 200) {
    return Promise.reject(res.data)
  }
  return res.data
}, err => {
  console.log(err)
})

export default service
