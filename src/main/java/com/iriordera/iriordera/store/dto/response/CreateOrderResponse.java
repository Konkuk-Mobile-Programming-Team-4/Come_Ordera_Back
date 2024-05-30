package com.iriordera.iriordera.store.dto.response;

import lombok.Builder;

@Builder
public record CreateOrderResponse(
        int code,
        String message,
        Long order_id
) {
}
