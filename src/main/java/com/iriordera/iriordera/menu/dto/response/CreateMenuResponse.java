package com.iriordera.iriordera.menu.dto.response;

import lombok.Builder;

@Builder
public record CreateMenuResponse(
        int code,
        String message
) {
}
