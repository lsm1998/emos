export interface MockParams {
    url: string
    type: string
    data?: any
    params?: string

    response(option?: any): Record<string, unknown>
}
