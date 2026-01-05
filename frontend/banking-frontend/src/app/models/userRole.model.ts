export interface userRole{
    userId : number;
    roleId : number;
}

export interface CreateUserRoleRequest {
    userId : number;
    roleId? : number;
}