import type { MutationTree, ActionTree, GetterTree } from 'vuex'
import type { State } from '@/store'
import service from '@/api/index'

interface Infos {
  [index: string]: unknown
}

export interface UsersState {
  token: string
  infos: Infos
}

const state: UsersState = {
  token: '',
  infos: {}
}

const mutations: MutationTree<UsersState> = {
  updateToken(state, payload) {
    state.token = payload
  },
  updateInfos(state, payload) {
    state.infos = payload
  },
  clearToken(state) {
    state.token = ''
  }
}
const actions: ActionTree<UsersState, State> = {
  login(context, payload) {
    return service.post('/user/login', payload)
  },
  profile() {
    return service.get('/user/profile')
  }
}
const getters: GetterTree<UsersState, State> = {}

export default {
  namespaced: true,
  state,
  mutations,
  actions,
  getters
}