package com.iriordera.iriordera.user;

import com.iriordera.iriordera.review.ReviewJpaEntity;
import com.iriordera.iriordera.review.ReviewRepository;
import com.iriordera.iriordera.store.StoreJpaEntity;
import com.iriordera.iriordera.store.StoreRepository;
import com.iriordera.iriordera.user.dto.request.CreateReviewRequest;
import com.iriordera.iriordera.user.dto.request.SignInRequest;
import com.iriordera.iriordera.user.dto.request.SignUpRequest;
import com.iriordera.iriordera.user.dto.response.CreateReviewResponse;
import com.iriordera.iriordera.user.dto.response.GetPointResponse;
import com.iriordera.iriordera.user.dto.response.SignInResponse;
import com.iriordera.iriordera.user.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {
        log.info("signup Service");

        UserJpaEntity user = userRepository.findByIdentifier(signUpRequest.id());

        if(user == null) {
            userRepository.save(UserJpaEntity.builder()
                .name(signUpRequest.name())
                .password(signUpRequest.password())
                .identifier(signUpRequest.id())
                .role(signUpRequest.role())
                .point(0L)
                .build()
            );

            return SignUpResponse.builder()
                    .code(500)
                    .message("정상적으로 생성되었습니다.")
                    .build();
        }

        return SignUpResponse.builder()
                .code(501)
                .message("존재하는 아이디입니다.")
                .build();

    }

    public SignInResponse signIn(SignInRequest signInRequest) {
        log.info("signin Service");

        UserJpaEntity user = userRepository.findByIdentifier(signInRequest.id());

        if(user == null || !Objects.equals(user.getIdentifier(), signInRequest.id()) || !Objects.equals(user.getPassword(), signInRequest.password())) {
            return SignInResponse.builder()
                    .code(504)
                    .message("존재하지 않는 회원입니다.")
                    .build();
        }

        if(Objects.equals(user.getRole(), "customer")) {
            return SignInResponse.builder()
                    .code(503)
                    .message("손님 환영합니다.")
                    .user_id(user.getId())
                    .build();
        }

        StoreJpaEntity storeJpaEntity = storeRepository.findByUserJpaEntityId(user.getId());

        if(Objects.equals(user.getRole(), "manager")) {
            return SignInResponse.builder()
                    .code(502)
                    .message("사장님 환영합니다.")
                    .store_id((storeJpaEntity == null) ? 0 : storeJpaEntity.getId())
                    .build();
        }

        return null;
    }

    public GetPointResponse getPoint(Long userId) {
        log.info("getPoint Service");

        return GetPointResponse.builder()
                .code(607)
                .message("성공적으로 유저 포인트 정보를 로드했습니다.")
                .point(userRepository.findById(userId).get().getPoint())
                .build();
    }


    public CreateReviewResponse createReview(CreateReviewRequest createReviewRequest) {
        log.info("createReview Service");

        Long reviewId = reviewRepository.save(ReviewJpaEntity.builder()
                .content(createReviewRequest.content())
                .star(createReviewRequest.star())
                .userJpaEntity(userRepository.findById(createReviewRequest.user_id()).get())
                .storeJpaEntity(storeRepository.findById(createReviewRequest.store_id()).get())
                .build()).getId();

        return CreateReviewResponse.builder()
                .code(611)
                .message("리뷰가 성공적으로 등록 되었습니다.")
                .review_id(reviewId)
                .build();
    }
}
