package com.guilhermecastro.amafloraSpringApi.entities.user;

public record ResgisterDTO(String name, String familyName, String profilePicture, String email, String password, UserRole role) {

}
