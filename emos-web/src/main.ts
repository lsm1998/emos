import {createApp} from 'vue'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import store from './store'

import {mockXHR} from "@/mock";

// 判断开发环境
if (process.env.NODE_ENV == "development") {
    mockXHR();
}

const app = createApp(App)
app.use(ElementPlus)
app.use(store)
app.use(router)
app.mount('#app')








