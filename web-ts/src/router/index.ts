import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const Login = () => import('@/views/Login.vue')
const Home = () => import('@/views/user/Home.vue')
const Sign = () => import('@/views/user/Sign.vue')
const NotFind = () => import('@/views/404.vue')
const Error = () => import('@/views/500.vue')

declare module 'vue-router' {
  interface RouteMeta {
    menu?: boolean
    title?: string
    icon?: string
    auth?: boolean
  }
}

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    redirect: '/sign',
    component: Home,
    meta: {
      menu: true,
      title: '考勤管理',
      icon: '<el-icon><DocumentCopy /></el-icon>',
      auth: true
    },
    children: [
      {
        path: 'sign',
        name: 'sign',
        component: Sign,
        meta: {
          menu: true,
          title: '签到',
          icon: '<el-icon><Finished /></el-icon>',
          auth: true
        },
      }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/404',
    name: 'notFind',
    component: NotFind
  },
  {
    path: '/500',
    name: 'error',
    component: Error
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
