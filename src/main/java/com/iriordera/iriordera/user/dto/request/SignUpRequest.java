package com.iriordera.iriordera.user.dto.request;

public record SignUpRequest(
        String name,
        String id,
        String password,
        String role
){
}
