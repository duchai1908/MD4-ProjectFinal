package com.ra.md4projectapi.controller.user;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.RatingRequest;
import com.ra.md4projectapi.model.dto.response.ResponseDtoSuccess;
import com.ra.md4projectapi.model.service.IRatingService;
import com.ra.md4projectapi.security.principle.UserDetailCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/rating")
@RequiredArgsConstructor
public class RatingUserController {
    private final IRatingService ratingService;
    @PostMapping
    public ResponseEntity<?> postComment(@RequestBody RatingRequest ratingRequest, @AuthenticationPrincipal UserDetailCustom userDetailCustom) throws DataExistException {
        return new ResponseEntity<>(new ResponseDtoSuccess<>(ratingService.addRating(ratingRequest,userDetailCustom.getUsers().getId()), HttpStatus.CREATED),HttpStatus.CREATED);
    }
}
