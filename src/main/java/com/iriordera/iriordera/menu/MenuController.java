package com.iriordera.iriordera.menu;

import com.iriordera.iriordera.menu.dto.request.CreateMenuRequest;
import com.iriordera.iriordera.menu.dto.response.CreateMenuResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping("/menu")
    public CreateMenuResponse createMenu(@RequestBody CreateMenuRequest createMenuRequest) {
        log.info("createMenu Controller");

        return menuService.createMenu(createMenuRequest);
    }
}
