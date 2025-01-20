package com.user.auth.constant;

public class EnumConstant {

    public enum Role {
        ADMIN(5),
        USER(4),
        GUEST(6); // Added a value for GUEST

        private final int value;

        // Enum constructor
        Role(int value) {
            this.value = value;
        }

        // Getter for the integer value
        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }

        public static String valueOf(int value) {
            for (Role role : Role.values()) {
                if (role.getValue() == value) {
                    return role.toString();
                }
            }
            throw new IllegalArgumentException("No Role with value " + value + " found.");
        }
    }
}
