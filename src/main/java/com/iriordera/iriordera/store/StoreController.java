package com.iriordera.iriordera.store;

import com.iriordera.iriordera.store.dto.request.CreateOrderRequest;
import com.iriordera.iriordera.store.dto.request.CreateStoreRequest;
import com.iriordera.iriordera.store.dto.request.DetailStoreQRRequest;
import com.iriordera.iriordera.store.dto.response.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/store")
    public CreateStoreResponse createStore(@RequestBody CreateStoreRequest createStoreRequest) {
        log.info("createStore Controller");

        return storeService.createStore(createStoreRequest);
    }

    @GetMapping("/order")
    public GetOrdersResponse getOrders(@RequestParam Long storeId){
        log.info("getOrders Controller");

        return storeService.getOrders(storeId);
    }

    @GetMapping("/store/review")
    public GetReviewResponse getReview(@RequestParam Long storeId){
        log.info("getReview Controller");

        return storeService.getReview(storeId);
    }

    @DeleteMapping("/store/review")
    public DeleteReviewResponse deleteReview(@RequestParam Long reviewId){
        log.info("deleteReview Controller");

        return storeService.deleteReview(reviewId);
    }

    @GetMapping("/store")
    public SearchStoreResponse searchStore(@RequestParam String search){
        log.info("searchStore Controller");

        return storeService.searchStore(search);
    }

    @GetMapping("/store/detail")
    public DetailStoreResponse detailStore(@RequestParam Long storeId){
        log.info("detailStore Controller");

        return storeService.detailStore(storeId);
    }

    @PostMapping("/store/detail")
    public DetailStoreResponse detailStoreQR(@RequestBody DetailStoreQRRequest detailStoreQRRequest){
        log.info("detailStoreQR Controller");

        return storeService.detailStoreQR(detailStoreQRRequest);
    }

    @PostMapping("/store/order")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest createOrderRequest){
        log.info("createOrder Controller");

        return storeService.createOrder(createOrderRequest);
    }

    @GetMapping("/store/order")
    public GetUserOrderResponse getUserOrder(@RequestParam Long orderId, @RequestParam Long userId){
        log.info("getUserOrder Controller");

        return storeService.getUserOrder(orderId, userId);
    }

    @GetMapping("order/delete")
    public void deleteOrder(@RequestParam Long orderId){
        log.info("deleteOrder Controller");

        storeService.deleteOrder(orderId);
    }
}
