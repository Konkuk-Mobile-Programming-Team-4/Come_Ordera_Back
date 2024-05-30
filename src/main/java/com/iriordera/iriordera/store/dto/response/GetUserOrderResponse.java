package com.iriordera.iriordera.store.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GetUserOrderResponse(
        int code,
        String message,
        Order order
) {

    @Builder
    public record Order(
            String name,
            Integer table,
            List<Menu> menus
    ){

        @Builder
        public record Menu(
            Long menu_id,
            String name,
            Integer price
        ){

        }
    }
}
