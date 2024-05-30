package com.iriordera.iriordera.store.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record SearchStoreResponse (
        int code,
        String message,
        List<Store> stores
){

    @Builder
    public record Store(
            Long store_id,
            String name,
            String location,
            Double latitude,
            Double longitude
    ){

    }
}
