package com.ra.md4projectapi.model.repository;

import com.ra.md4projectapi.model.entity.CartItem;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartUserRepository extends JpaRepository<CartItem,Long> {
    CartItem findCartItemByUserIdAndProductId(Long userId, Long productId);
    List<CartItem> findCartItemByUserId(Long userId);
    @Transactional
    void deleteAllByUserId(Long userId);
}
