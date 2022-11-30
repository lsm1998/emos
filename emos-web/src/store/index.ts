import {createStore} from 'vuex'
import {IUserInfo, UserInfo} from "@/types/user";

export default createStore({
    state: new UserInfo(),
    getters: {
        token(state: UserInfo) {
            return state?.token
        },
        user(state: UserInfo) {
            return state?.user
        }
    },
    mutations: {
        setToken(state: UserInfo, token: string) {
            state.token = token
        },
        setUser(state: UserInfo, user: IUserInfo) {
            state.user = user
        }
    },
    actions: {},
    modules: {}
})
