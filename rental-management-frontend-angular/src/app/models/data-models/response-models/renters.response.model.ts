
export interface RentersResponseModel {
    renterId?: number | null | undefined;
    nidNo?: string;
    deal?: string;
    userId?: number | null | undefined;
    renterName?: string;
    renterUsername?: string;
    renterEmail?: string;
    renterPhone?: string;
    renterAddress?: string;
    renterDateOfBirth?: string;
    buildingFlatId?: number | null | undefined;
    flatNo?: string;
    buildingId?: number | null | undefined;
    buildingName?: string;
    buildingAddress?: string;
}