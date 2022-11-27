import Mock from "@/mock/index";

export default [
    // userInfo
    {
        url: "api/v1/user/userInfo",
        type: "get",
        response: () => {
            return Mock.mock({
                id: "@id", //得到随机id
                username: "@cname()", //得到随机姓名
                date: "@date()", //得到随机日期
                avatar: "@image('200*200','red','#fff','avatar')", //得到随机图片
                description: "@paragraph", //得到随机描述
                ip: "@ip()", //得到随机ip
                email: "@email()", //得到随机邮箱
                extend: "extend", //自定义
            });
        },
    },
    // login
    {
        url: "/api/v1/user/login",
        type: "post",
        response: (option: any) => {
            const $name = JSON.parse(option.body).name;
            const $pass = JSON.parse(option.body).pass;
            if ($name && $pass) {
                return Mock.mock({
                    code: 200,
                    message: "成功",
                    data: {
                        accessToken: "testToken",
                    },
                });
            } else {
                return Mock.mock({
                    code: 400,
                    message: "未提交参数",
                });
            }
        },
    },
];
