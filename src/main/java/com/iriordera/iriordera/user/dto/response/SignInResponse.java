package com.iriordera.iriordera.user.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record SignInResponse (
        int code,
        String message,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long store_id,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Long user_id
){
}
