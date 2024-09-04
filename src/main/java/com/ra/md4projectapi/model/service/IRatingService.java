package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.exception.DataExistException;
import com.ra.md4projectapi.model.dto.request.RatingRequest;
import com.ra.md4projectapi.model.entity.Rating;

public interface IRatingService {
    Rating addRating(RatingRequest ratingRequest, Long userId) throws DataExistException;
}
