export enum Status {
    ACTIVE = "ACTIVE",
    LOCKED = "LOCKED",
    INACTIVE = "INACTIVE",
    SUSPENDED = "SUSPENDED"
}

export interface User {
    userId?: number;
    userName: string;
    fullName: string;
    email?: string;
    phone?: string;
    status?: Status;
    createdTime?: Date;
}

export interface UserCert {
    userId: number;
    userName: string;
    fullName: string;
    email?: string;
    phone?: string;
    role: string;
    status: Status;
}

export interface CreateUserRequest {
    userName: string;
    fullName: string;
    email?: string;
    phone?: string;
}

export interface UpdateUserRequest {
    userName: string;
    email?: string;
    phone?: string;
}

export interface loginRequest {
    userName: string;
    password: string;
}

export interface loginResponse {
    token : string;
    userCert : UserCert;
}