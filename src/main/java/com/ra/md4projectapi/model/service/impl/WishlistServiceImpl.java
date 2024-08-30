package com.ra.md4projectapi.model.service.impl;

import com.ra.md4projectapi.model.entity.Product;
import com.ra.md4projectapi.model.entity.User;
import com.ra.md4projectapi.model.service.IProductService;
import com.ra.md4projectapi.model.service.IUserService;
import com.ra.md4projectapi.model.service.IWishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements IWishlistService {
    private final IUserService userService;
    private final IProductService productService;
    @Override
    public Set<Product> getWishlist(Long userId) {
        User user = userService.getUserById(userId);
        return user.getProducts();
    }

    @Override
    public Set<Product> addToWishlist(Long userId, Long productId) {
        Product product = productService.findByProductId(productId);
        User user = userService.getUserById(userId);
        boolean check = user.getProducts().contains(product);
        if (check) {
            user.getProducts().remove(product);
        }else{
            Set<Product> products = user.getProducts();
            products.add(product);
            user.setProducts(products);
        }
        userService.save(user);
        return user.getProducts();
    }

    @Override
    public Set<Product> removeFromWishlist(Long userId, Long productId) {
        Product product = productService.findByProductId(productId);
        User user = userService.getUserById(userId);
        Set<Product> products = user.getProducts();
        products.remove(product);
        user.setProducts(products);
        userService.save(user);
        return user.getProducts();
    }
}
