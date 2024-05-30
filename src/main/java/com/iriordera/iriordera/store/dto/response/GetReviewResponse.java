package com.iriordera.iriordera.store.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GetReviewResponse(
        int code,
        String message,
        List<Review> reviews
) {
    @Builder
    public static record Review(
            Long review_id,
            String user_name,
            String content,
            Integer star
    ){

    }
}
