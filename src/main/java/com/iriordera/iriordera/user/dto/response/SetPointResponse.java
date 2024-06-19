package com.iriordera.iriordera.user.dto.response;

import lombok.Builder;

@Builder
public record SetPointResponse (
        int code,
        String message
){
}
