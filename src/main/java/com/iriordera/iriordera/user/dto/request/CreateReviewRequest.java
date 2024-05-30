package com.iriordera.iriordera.user.dto.request;

public record CreateReviewRequest (
        Long user_id,
        Long store_id,
        String content,
        Integer star
){
}
