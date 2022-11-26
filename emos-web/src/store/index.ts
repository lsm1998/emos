import {createStore} from 'vuex'

import {UserInfo} from "@/type/user";

export default createStore({
    state: new UserInfo(),
    getters: {
        token(state?: UserInfo) {
            return state?.token
        },
        user(state?: UserInfo) {
            return state?.user
        }
    },
    mutations: {},
    actions: {},
    modules: {}
})