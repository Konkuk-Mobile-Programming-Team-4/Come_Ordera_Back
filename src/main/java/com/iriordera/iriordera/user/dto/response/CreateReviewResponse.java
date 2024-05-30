package com.iriordera.iriordera.user.dto.response;

import lombok.Builder;

@Builder
public record CreateReviewResponse(
        int code,
        String message,
        Long review_id
) {
}
