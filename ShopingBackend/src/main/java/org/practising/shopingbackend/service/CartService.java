package org.practising.shopingbackend.service;

import org.practising.shopingbackend.model.CartModel;
import org.practising.shopingbackend.model.ProductsModel;
import org.practising.shopingbackend.repository.CartRepository;
import org.practising.shopingbackend.repository.ProductsRepository;
import org.practising.shopingbackend.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductsRepository productRepository;

    @Autowired
    private AuthRepository authRepository;

    // Get user's cart with product details
    public List<Map<String, Object>> getCartWithDetails(Integer userId) {
        List<CartModel> cartItems = cartRepository.findByUserId(userId);

        return cartItems.stream().map(cartItem -> {
            Map<String, Object> item = new HashMap<>();
            item.put("id", cartItem.getId());
            item.put("quantity", cartItem.getQuantity());

            // Get product details
            ProductsModel product = productRepository.findById(cartItem.getProductId())
                    .orElse(null);

            if (product != null) {
                Map<String, Object> productMap = new HashMap<>();
                productMap.put("id", product.getId());
                productMap.put("name", product.getName());
                productMap.put("price", product.getPrice());
                productMap.put("price_min", product.getPriceMin());
                productMap.put("price_max", product.getPriceMax());
                productMap.put("image_url", product.getImageUrl());
                productMap.put("badge", product.getBadge());
                productMap.put("rating", product.getRating());
                productMap.put("description", product.getDescription());
                productMap.put("sku", product.getSku());
                productMap.put("brand", product.getBrand());
                productMap.put("material", product.getMaterial());
                productMap.put("stock", product.getStock());
                item.put("product", productMap);
            }

            return item;
        }).toList();
    }

    // Add item to cart
    public CartModel addToCart(Integer userId, Integer productId, Integer quantity) {
        // Check if product exists
        ProductsModel product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if item already in cart
        java.util.Optional<CartModel> existingCartItem = cartRepository.findByUserIdAndProductId(userId, productId);

        if (existingCartItem.isPresent()) {
            // Update existing cart item
            CartModel cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartRepository.save(cartItem);
        } else {
            // Create new cart item
            CartModel newCartItem = new CartModel();
            newCartItem.setUserId(userId);
            newCartItem.setProductId(productId);
            newCartItem.setQuantity(quantity);
            newCartItem.setCreatedAt(LocalDateTime.now());
            return cartRepository.save(newCartItem);
        }
    }

    // Update cart item quantity
    public CartModel updateQuantity(Integer cartItemId, Integer userId, Integer quantity) {
        CartModel cartItem = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Verify ownership
        if (!cartItem.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        cartItem.setQuantity(quantity);
        return cartRepository.save(cartItem);
    }

    // Remove item from cart
    public void removeFromCart(Integer cartItemId, Integer userId) {
        CartModel cartItem = cartRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Verify ownership
        if (!cartItem.getUserId().equals(userId)) {
            throw new RuntimeException("Unauthorized");
        }

        cartRepository.delete(cartItem);
    }

    // Clear entire cart (after checkout)
    public void clearCart(Integer userId) {
        cartRepository.deleteByUserId(userId);
    }

    public List<CartModel> getCartByUser(String username) {
        return List.of();
    }
}