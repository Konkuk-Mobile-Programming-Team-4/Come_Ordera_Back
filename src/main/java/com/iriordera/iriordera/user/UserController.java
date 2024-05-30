package com.iriordera.iriordera.user;

import com.iriordera.iriordera.user.dto.request.CreateReviewRequest;
import com.iriordera.iriordera.user.dto.request.SignInRequest;
import com.iriordera.iriordera.user.dto.request.SignUpRequest;
import com.iriordera.iriordera.user.dto.response.CreateReviewResponse;
import com.iriordera.iriordera.user.dto.response.GetPointResponse;
import com.iriordera.iriordera.user.dto.response.SignInResponse;
import com.iriordera.iriordera.user.dto.response.SignUpResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signUp")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("signUp Controller");

        return userService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) {
        log.info("signIn Controller");

        return userService.signIn(signInRequest);
    }

    @GetMapping("/user/point")
    public GetPointResponse getPoint(@RequestParam Long userId){
        log.info("getPoint Controller");

        return userService.getPoint(userId);
    }

    @PostMapping("/review")
    public CreateReviewResponse createReview(@RequestBody CreateReviewRequest createReviewRequest){
        log.info("createReview Controller");

        return userService.createReview(createReviewRequest);
    }
}
