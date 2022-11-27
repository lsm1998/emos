// eslint-disable-next-line
const Mock = require("mockjs")

import { MockParams } from "./typing";

Mock.setup({
    timeout : "300"
})

import userApi from "./userApi";

const mocks = [...userApi];

export function mockXHR() {
    let i: MockParams;
    for (i of mocks) {
        Mock.mock(new RegExp(i.url), i.type || "get", i.response);
    }
}

export default Mock

