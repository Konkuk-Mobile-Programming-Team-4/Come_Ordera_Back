package com.iriordera.iriordera.store.dto.response;

import lombok.Builder;

@Builder
public record DeleteReviewResponse (
        int code,
        String message
){
}
