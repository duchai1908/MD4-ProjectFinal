package com.ra.md4projectapi.model.service;

import com.ra.md4projectapi.model.entity.Product;

import java.util.Set;

public interface IWishlistService {
    Set<Product> getWishlist(Long userId);
    Set<Product> addToWishlist(Long userId, Long productId);
    Set<Product> removeFromWishlist(Long userId, Long productId);
}
