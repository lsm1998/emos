import IUser from '@/types/user'
import service from '@/api/index'

export function login(data: IUser) {
  return service({
    url: '/user/login',
    method: 'POST',
    data
  })
}