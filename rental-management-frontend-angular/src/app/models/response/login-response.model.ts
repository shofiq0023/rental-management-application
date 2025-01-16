import { LoginModel } from "../data-models/login-response.model";

export interface LoginReponseModel {
    data?: LoginModel;
    message?: string;
    responseCode?: string;
}