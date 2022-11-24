export interface ILoginData {
    username: string
    password: string
}

export class LoginData {
    ruleForm: ILoginData = {
        username: "",
        password: ""
    }
}