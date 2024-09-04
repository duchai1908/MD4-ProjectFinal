package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.RatingRequest;
import com.ra.md4projectapi.model.entity.Rating;
import com.ra.md4projectapi.model.repository.IRatingRepository;
import com.ra.md4projectapi.model.service.IProductService;
import com.ra.md4projectapi.model.service.IRatingService;
import com.ra.md4projectapi.model.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements IRatingService {
    private final IUserService userService;
    private final IRatingRepository ratingRepository;
    private final IProductService productService;
    @Override
    public Rating addRating(RatingRequest ratingRequest, Long userId) throws DataExistException {
        List<Rating> list = ratingRepository.findAll();
        if(list.stream().anyMatch(r -> r.getUser().getId().equals(userId) && r.getProduct().getId().equals(ratingRequest.getProduct()))){
            throw new DataExistException("You have rated this product","rating");
        }
        Rating rating = Rating.builder()
                .user(userService.getUserById(userId))
                .comment(ratingRequest.getComment())
                .rating(ratingRequest.getRating())
                .product(productService.findByProductId(ratingRequest.getProduct()))
                .build();
        return ratingRepository.save(rating);
    }
}
