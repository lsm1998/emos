import { IBase } from '@/types/base'

export interface IBettingQuery extends IBase {
  userId?: number
  statusList?: Array<number>
  startTime?: string
  endTime?: string
}

export interface IBetting {
  id: number
  userId?: number
  realName?: string // 下注人
  content: string // 内容
  amount: number // 金额
  schedule: string // 场次
  status: number // 状态
  version: number // 乐观锁版本
  createdTime?: string // 创建时间
}