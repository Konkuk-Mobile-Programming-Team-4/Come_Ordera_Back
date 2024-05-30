package com.iriordera.iriordera.menu;

import com.iriordera.iriordera.menu.dto.request.CreateMenuRequest;
import com.iriordera.iriordera.menu.dto.response.CreateMenuResponse;
import com.iriordera.iriordera.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final StoreRepository storeRepository;

    public CreateMenuResponse createMenu(CreateMenuRequest createMenuRequest) {
        log.info("createMenu Service");

        menuRepository.save(MenuJpaEntity.builder()
                        .name(createMenuRequest.name())
                        .price(createMenuRequest.price())
                        .introduction(createMenuRequest.introduction())
                        .storeJpaEntity(storeRepository.findById(createMenuRequest.store_id()).get())
                        .build()
        );

        return CreateMenuResponse.builder()
                .code(602)
                .message("메뉴 등록이 완료되었습니다.")
                .build();
    }
}
