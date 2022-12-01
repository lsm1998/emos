import service from '@/request/index'
import {ILoginData} from '@/types/login'
import {IBetting, IBettingQuery} from "@/types/betting";

export function login(data: ILoginData) {
    return service({
        url: '/user/login',
        method: 'POST',
        data
    })
}

export function profile() {
    return service({
        url: '/user/profile',
        method: 'GET'
    })
}

export function bettingList(param: IBettingQuery) {
    return service({
        url: '/betting',
        method: 'GET',
        params: param
    })
}

export function saveBetting(data: IBetting) {
    return service({
        url: '/betting',
        method: 'POST',
        data
    })
}

export function updateBetting(data: IBetting) {
    return service({
        url: '/betting',
        method: 'PUT',
        data
    })
}

export function deleteBetting(id: number) {
    return service({
        url: `'/betting/${id}`,
        method: 'DELETE',
    })
}