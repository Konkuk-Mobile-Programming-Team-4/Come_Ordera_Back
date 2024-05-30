package com.iriordera.iriordera.store.dto.request;

public record CreateStoreRequest (
        Long user_id,
        String name,
        String location,
        Integer table,
        Double latitude,
        Double longitude
){
}
