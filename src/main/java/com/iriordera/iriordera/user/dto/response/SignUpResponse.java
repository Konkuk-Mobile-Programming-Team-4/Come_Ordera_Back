package com.iriordera.iriordera.user.dto.response;

import lombok.Builder;

@Builder
public record SignUpResponse (
    int code,
    String message
){
}
