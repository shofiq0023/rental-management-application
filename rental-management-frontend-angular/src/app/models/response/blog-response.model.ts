import { BlogModel } from "../data-models/blog.model";

export interface BlogResponseModel {
    data: BlogModel[]
    msg: string
    success: boolean
}