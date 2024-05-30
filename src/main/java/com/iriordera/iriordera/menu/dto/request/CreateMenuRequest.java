package com.iriordera.iriordera.menu.dto.request;

public record CreateMenuRequest (
        Long store_id,
        String name,
        Integer price,
        String introduction
){
}
