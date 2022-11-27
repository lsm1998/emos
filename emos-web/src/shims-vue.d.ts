/* eslint-disable */
declare module '*.vue' {
  import type { DefineComponent } from 'vue'
  declare module "mockjs";
  const component: DefineComponent<{}, {}, any>
  export default component
}
