export interface UserSignupRequestModel {
    name: string;
    username: string;
    email: string;
    phone: string;
    password: string;
    address: string;
    dateOfBirth?: string;
    userType?: number;
}