package com.iriordera.iriordera.store;

import com.iriordera.iriordera.menu.MenuJpaEntity;
import com.iriordera.iriordera.menu.MenuRepository;
import com.iriordera.iriordera.order.OrderJpaEntity;
import com.iriordera.iriordera.order.OrderRepository;
import com.iriordera.iriordera.review.ReviewJpaEntity;
import com.iriordera.iriordera.review.ReviewRepository;
import com.iriordera.iriordera.store.dto.request.CreateOrderRequest;
import com.iriordera.iriordera.store.dto.request.CreateStoreRequest;
import com.iriordera.iriordera.store.dto.request.DetailStoreQRRequest;
import com.iriordera.iriordera.store.dto.response.*;
import com.iriordera.iriordera.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final OrderRepository orderRepository;
    private final ReviewRepository reviewRepository;
    private final MenuRepository menuRepository;

    public CreateStoreResponse createStore(CreateStoreRequest createStoreRequest) {
        log.info("createStore Service");

        Long store_id =
            storeRepository.save(
                StoreJpaEntity.builder()
                    .name(createStoreRequest.name())
                    .location(createStoreRequest.location())
                    .table(createStoreRequest.table())
                    .latitude(createStoreRequest.latitude())
                    .longitude(createStoreRequest.longitude())
                        .userJpaEntity(userRepository.findById(createStoreRequest.user_id()).get())
                    .build()
            ).getId();

        return CreateStoreResponse.builder()
                .code(600)
                .message("가게 등록이 완료되었습니다.")
                .store_id(store_id)
                .build();
    }

    public GetOrdersResponse getOrders(Long storeId) {
        log.info("getOrders Service");

        Optional<List<OrderJpaEntity>> orderJpaEntityList = orderRepository.findAllByStoreJpaEntityId(storeId);

        if (orderJpaEntityList.isEmpty()) {
            return GetOrdersResponse.builder()
                    .code(404)
                    .message("가게를 찾을 수 없습니다.")
                    .build();
        }

        return mapToGetOrdersResponse(orderJpaEntityList.get());
    }

    private GetOrdersResponse.Order mapToOrderDto(OrderJpaEntity orderJpaEntity) {
        List<GetOrdersResponse.Order.Menu> menuDtos = orderJpaEntity.getMenuIdList().stream()
                .map(menu -> {
                    MenuJpaEntity menuJpaEntity = menuRepository.findById(menu).get();
                    return GetOrdersResponse.Order.Menu.builder()
                            .name(menuJpaEntity.getName())
                            .price(menuJpaEntity.getPrice())
                            .build();
                })
                .collect(Collectors.toList());

        return GetOrdersResponse.Order.builder()
                .order_id(orderJpaEntity.getId())
                .table_number(orderJpaEntity.getTable_number())
                .user_id(orderJpaEntity.getUserJpaEntity().getId())
                .menu(menuDtos)
                .build();
    }

    private GetOrdersResponse mapToGetOrdersResponse(List<OrderJpaEntity> orderJpaEntityList) {
        List<GetOrdersResponse.Order> orderDtos = orderJpaEntityList.stream()
                .map(this::mapToOrderDto)
                .collect(Collectors.toList());

        return GetOrdersResponse.builder()
                .code(601)
                .message("성공적으로 가게 주문 현황을 로드했습니다.")
                .orders(orderDtos)
                .build();
    }

    public GetReviewResponse getReview(Long storeId) {
        log.info("getReview Service");

        List<ReviewJpaEntity> reviewJpaEntityList =  reviewRepository.findAllByStoreJpaEntityId(storeId);

        List<GetReviewResponse.Review> reviewList =  reviewJpaEntityList.stream()
                .map(review -> GetReviewResponse.Review.builder()
                        .review_id(review.getId())
                        .user_name(review.getUserJpaEntity().getName())
                        .content(review.getContent())
                        .star(review.getStar())
                        .build())
                .toList();

        return GetReviewResponse.builder()
                .code(603)
                .message("성공적으로 리뷰를 로드했습니다.")
                .reviews(reviewList)
                .build();
    }

    public DeleteReviewResponse deleteReview(Long reviewId) {
        log.info("deleteReview Service");

        reviewRepository.findById(reviewId).ifPresent(reviewRepository::delete);

        return DeleteReviewResponse.builder()
                .code(604)
                .message("리뷰를 성공적으로 삭제 했습니다.")
                .build();
    }

    public SearchStoreResponse searchStore(String search) {
        log.info("searchStore Service");

        List<SearchStoreResponse.Store> storeList = storeRepository.findAllByName(search).stream()
                .map(store -> SearchStoreResponse.Store.builder()
                        .store_id(store.getId())
                        .name(store.getName())
                        .location(store.getLocation())
                        .latitude(store.getLatitude())
                        .longitude(store.getLongitude())
                        .build()
                )
                .toList();

        return SearchStoreResponse.builder()
                .code(605)
                .message("성공적으로 가게 검색을 했습니다.")
                .stores(storeList)
                .build();
    }

    public DetailStoreResponse detailStore(Long storeId) {
        log.info("detailStore Service");

        StoreJpaEntity storeJpaEntity = storeRepository.findById(storeId).get();
        List<DetailStoreResponse.Menu> menuList = menuRepository.findAllByStoreJpaEntityId(storeId).stream()
                .map(menu -> DetailStoreResponse.Menu.builder()
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .introduction(menu.getIntroduction())
                        .build())
                .toList();

        return DetailStoreResponse.builder()
                .code(606)
                .message("성공적으로 가게 상세 페이지 정보를 로드했습니다.")
                .name(storeJpaEntity.getName())
                .location(storeJpaEntity.getLocation())
                .table(storeJpaEntity.getTable())
                .latitude(storeJpaEntity.getLatitude())
                .longitude(storeJpaEntity.getLongitude())
                .menus(menuList)
                .build();
    }

    public DetailStoreResponse detailStoreQR(DetailStoreQRRequest detailStoreQRRequest) {
        log.info("detailStoreQR Service");

        StoreJpaEntity storeJpaEntity = storeRepository.findById(detailStoreQRRequest.store_id()).get();
        List<DetailStoreResponse.Menu> menuList = menuRepository.findAllByStoreJpaEntityId(detailStoreQRRequest.store_id()).stream()
                .map(menu -> DetailStoreResponse.Menu.builder()
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .introduction(menu.getIntroduction())
                        .menu_id(menu.getId())
                        .build())
                .toList();

        return DetailStoreResponse.builder()
                .code(606)
                .message("성공적으로 가게 상세 페이지 정보를 로드했습니다.")
                .name(storeJpaEntity.getName())
                .location(storeJpaEntity.getLocation())
                .menus(menuList)
                .build();
    }

    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest) {
        log.info("createOrder Service");

        Long order_id = orderRepository.save(OrderJpaEntity.builder()
                        .table_number(createOrderRequest.table())
                        .userJpaEntity(userRepository.findById(createOrderRequest.user_id()).get())
                        .storeJpaEntity(storeRepository.findById(createOrderRequest.store_id()).get())
                        .menuIdList(createOrderRequest.menu_id())
                .build()).getId();

        return CreateOrderResponse.builder()
                .code(609)
                .message("주문이 성공적으로 들어갔습니다.")
                .order_id(order_id)
                .build();
    }

    public GetUserOrderResponse getUserOrder(Long orderId, Long userId) {
        log.info("getUserOrder Service");

        OrderJpaEntity orderJpaEntity = orderRepository.findByIdAndUserJpaEntityId(orderId, userId);
        StoreJpaEntity storeJpaEntity = storeRepository.findById(orderJpaEntity.getStoreJpaEntity().getId()).get();
        List<GetUserOrderResponse.Order.Menu> menuList = orderJpaEntity.getMenuIdList().stream()
                .map(id -> {
                    MenuJpaEntity menuJpaEntity = menuRepository.findById(id).get();
                    return GetUserOrderResponse.Order.Menu.builder()
                            .menu_id(menuJpaEntity.getId())
                            .name(menuJpaEntity.getName())
                            .price(menuJpaEntity.getPrice())
                            .build();
                })
                .toList();

        return GetUserOrderResponse.builder()
                .code(610)
                .message("성공적으로 주문 정보를 로드했습니다.")
                .order(GetUserOrderResponse.Order.builder()
                        .name(storeJpaEntity.getName())
                        .table(storeJpaEntity.getTable())
                        .menus(menuList)
                        .build())
                .build();
    }

    public void deleteOrder(Long orderId) {
        log.info("deleteOrder Service");

        OrderJpaEntity orderJpaEntity = orderRepository.findById(orderId).get();

        orderRepository.delete(orderJpaEntity);
    }
}
