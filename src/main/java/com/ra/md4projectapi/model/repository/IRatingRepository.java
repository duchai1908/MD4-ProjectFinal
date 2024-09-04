package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.model.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRatingRepository extends JpaRepository<Rating, Long> {
}
