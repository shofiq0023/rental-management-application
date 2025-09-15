import { UserModel } from "../user.model";
import { RentersResponseModel } from "./renters.response.model";


export interface RentPaymentResponseModel {
    id: string;
    createdAt: string;
    updatedAt: string;
    amount: number;
    paymentDate: string;
    monthName: string;
    yearStr: string;
    utilityBill: number;
    othersBill: number;
    paymentStatus: string;
    renter: RentersResponseModel;
    user: UserModel;
}


