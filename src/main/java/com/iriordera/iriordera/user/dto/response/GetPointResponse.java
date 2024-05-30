package com.iriordera.iriordera.user.dto.response;

import lombok.Builder;

@Builder
public record GetPointResponse (
        int code,
        String message,
        Long point
){
}
