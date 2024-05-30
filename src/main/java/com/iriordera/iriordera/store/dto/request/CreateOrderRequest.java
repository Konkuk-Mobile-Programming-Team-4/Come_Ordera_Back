package com.iriordera.iriordera.store.dto.request;

import java.util.List;

public record CreateOrderRequest (
        List<Long> menu_id,
        Integer table,
        Long user_id,
        Long store_id
){
}
