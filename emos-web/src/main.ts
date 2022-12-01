import { createApp } from 'vue'
import 'element-plus/dist/index.css'
import ElementPlus from 'element-plus'
import App from './App.vue'
import router from './router'
import store from './store'

import zhCn from "element-plus/lib/locale/lang/zh-cn"

const app = createApp(App)
app.use(ElementPlus, {
  locale: zhCn
})
app.use(store)
app.use(router)
app.mount('#app')








