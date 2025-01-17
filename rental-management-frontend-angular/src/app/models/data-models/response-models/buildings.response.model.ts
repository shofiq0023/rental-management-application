import { FlatsResponseModel } from "./flats.response.model";

export interface BuildingsResponseModel {
    id?: number;
    name?: string;
    address?: string;
    flats?: [FlatsResponseModel];
}