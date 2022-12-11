import axios from 'axios'

const service = axios.create({
  baseURL: 'http://127.0.0.1:8888/api/v1',
  timeout: 5000,
  headers: { 'X-Form-Header': 'vue-ts-app' }
})

service.interceptors.request.use(config => {
  return config
}, err => {
  return Promise.reject(err)
})

service.interceptors.response.use(res => {
  return res
}, err => {
  return Promise.reject(err)
})

export default service
