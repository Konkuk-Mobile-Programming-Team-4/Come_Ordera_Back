package com.iriordera.iriordera.store.dto.response;

import lombok.Builder;

@Builder
public record CreateStoreResponse (
        int code,
        String message,
        Long store_id
){
}
