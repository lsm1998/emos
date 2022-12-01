export interface IUserInfo {
    id: number
    username: string
    avatar: string
    realName: string
}

export class UserInfo {
    token = ""
    user: IUserInfo = {
        id: 0,
        username: "",
        avatar: "",
        realName: ""
    }
}