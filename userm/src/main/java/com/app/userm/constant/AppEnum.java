package com.app.userm.constant;

public class AppEnum {

    // First Enum: Role
    public enum Role {
        ADMIN(1, "Admin", "Admin has full access"),
        ASSOCIATE(2, "Associate", "Associate can view and add members"),
        USER(3, "User", "Regular user with limited access");

        private final int roleId;
        private final String roleName;
        private final String description;

        // Enum constructor
        Role(int roleId, String roleName, String description) {
            this.roleId = roleId;
            this.roleName = roleName;
            this.description = description;
        }

        // Getter for role ID
        public int getRoleId() {
            return roleId;
        }

        // Getter for role name
        public String getRoleName() {
            return roleName;
        }

        // Getter for description
        public String getDescription() {
            return description;
        }
    }

    // Second Enum: Status
    public enum Status {
        ACTIVE("Active"),
        INACTIVE("Inactive"),
        PENDING("Pending");

        private final String status;

        // Enum constructor
        Status(String status) {
            this.status = status;
        }

        // Getter for status
        public String getStatus() {
            return status;
        }
    }
    public static AppEnum.Role getRoleByRoleId(int roleId) {
        for (AppEnum.Role role : AppEnum.Role.values()) {
            if (role.getRoleId() == roleId) {
                return role;
            }
        }
        return null; // Or handle this case as needed
    }


    // Third Enum: LoginType
    public enum LoginType {
        STANDARD(1, "Standard Login"),
        GOOGLE(2, "Google Login"),
        FACEBOOK(3, "Facebook Login");

        private final int typeId;
        private final String typeName;

        // Enum constructor
        LoginType(int typeId, String typeName) {
            this.typeId = typeId;
            this.typeName = typeName;
        }

        // Getter for type ID
        public int getTypeId() {
            return typeId;
        }

        // Getter for type name
        public String getTypeName() {
            return typeName;
        }
    }
}
