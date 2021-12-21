package com.example.helpdesk.entity;

public enum UserRole {
    ROLE_USER("Пользователь"),
    ROLE_ADMIN("Администратор");

    private final String label;

    UserRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
