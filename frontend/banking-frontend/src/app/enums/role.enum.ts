export enum UserRoleEnum {
    Admin = 1,
    Employee = 2,
    Customer = 3,
    Guest = 4
}

export const UserRoleMap = {
    "Admin": UserRoleEnum.Admin,
    "Employee": UserRoleEnum.Employee,
    "Customer": UserRoleEnum.Customer,
    "Guest": UserRoleEnum.Guest
};