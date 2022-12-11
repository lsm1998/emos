import {createStore, useStore as baseUseStore} from 'vuex'
import VuexPersistence from 'vuex-persist'
import type {InjectionKey} from 'vue'
import type {Store} from 'vuex'
import users from './modules/users'
import type {UsersState} from './modules/users'

// eslint-disable-next-line
export interface State {
}

export interface StateAll extends State {
    users: UsersState,
}

// 定义persist
const vuexLocal = new VuexPersistence<State>({
    storage: window.localStorage,
    reducer: (state) => ({users: {token: (state as StateAll).users.token}}) // 只对token持久化
})

export const key: InjectionKey<Store<StateAll>> = Symbol()

export function useStore() {
    return baseUseStore(key)
}

export default createStore({
    state: {},
    getters: {},
    mutations: {},
    actions: {},
    modules: {
        users
    },
    plugins: [vuexLocal.plugin]
})
