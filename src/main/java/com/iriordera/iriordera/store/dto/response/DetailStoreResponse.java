package com.iriordera.iriordera.store.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;

@Builder
public record DetailStoreResponse(
        int code,
        String message,
        String name,
        String location,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Integer table,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Double latitude,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Double longitude,

        List<Menu> menus
) {

    @Builder
    public record Menu(
            Long menu_id,
            String name,
            Integer price,
            String introduction
    ){

    }
}
