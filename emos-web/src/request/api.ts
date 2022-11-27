import service from "@/request/index";
import {ILoginData} from "@/types/login";

export function login(data: ILoginData) {
    return service({
        url: "/user/login",
        method: "POST",
        data,
    })
}
