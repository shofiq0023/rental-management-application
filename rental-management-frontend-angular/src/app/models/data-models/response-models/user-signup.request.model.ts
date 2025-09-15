import { UserModel } from "../user.model";

export interface LoginModel {
    token?: string;
    userInfo?: UserModel
}