import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const Login = () => import('@/views/Login.vue')
const Home = () => import('@/views/user/Home.vue')
const NotFind = () => import('@/views/404.vue')
const Error = () => import('@/views/500.vue')

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/login',
    name: 'about',
    component: Login
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
