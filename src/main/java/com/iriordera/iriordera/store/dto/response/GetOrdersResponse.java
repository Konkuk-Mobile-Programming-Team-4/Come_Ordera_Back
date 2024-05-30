package com.iriordera.iriordera.store.dto.response;

import lombok.Builder;

import java.util.List;

@Builder
public record GetOrdersResponse (
        int code,
        String message,
        List<Order> orders
){

    @Builder
    public record Order(
            Long order_id,
            int table_number,
            Long user_id,
            List<Menu> menu
    ) {

        @Builder
        public record Menu(
                String name,
                int price
        ){

        }
    }
}
