package org.practising.shopingbackend.controller;

import org.practising.shopingbackend.model.AuthModel;
import org.practising.shopingbackend.model.CartModel;
import org.practising.shopingbackend.service.CartService;
import org.practising.shopingbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5500", allowCredentials = "true")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private AuthService authService;

    // Helper method to get logged-in user from session
    private AuthModel getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new RuntimeException("Not logged in");
        }

        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("Not logged in");
        }

        return authService.getUserById(userId);
    }

    // GET /api/cart - Get user's cart
    @GetMapping
    public ResponseEntity<?> getCart(HttpServletRequest request) {
        try {
            AuthModel user = getLoggedInUser(request);
            List<Map<String, Object>> cartItems = cartService.getCartWithDetails(user.getId());
            return ResponseEntity.ok(cartItems);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(401).body(error);
        }
    }

    // POST /api/cart - Add item to cart
    @PostMapping
    public ResponseEntity<?> addToCart(
            HttpServletRequest request,
            @RequestBody Map<String, Object> cartRequest) {
        try {
            AuthModel user = getLoggedInUser(request);
            Integer productId = (Integer) cartRequest.get("productId");
            Integer quantity = cartRequest.containsKey("quantity") ? (Integer) cartRequest.get("quantity") : 1;

            CartModel cartItem = cartService.addToCart(user.getId(), productId, quantity);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Added to cart");
            response.put("cartItem", Map.of(
                    "id", cartItem.getId(),
                    "quantity", cartItem.getQuantity()
            ));

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(401).body(error);
        }
    }

    // PUT /api/cart/{cartItemId} - Update quantity
    @PutMapping("/{cartItemId}")
    public ResponseEntity<?> updateCartItem(
            HttpServletRequest request,
            @PathVariable Integer cartItemId,
            @RequestBody Map<String, Integer> updateRequest) {
        try {
            AuthModel user = getLoggedInUser(request);
            Integer quantity = updateRequest.get("quantity");

            CartModel updatedItem = cartService.updateQuantity(cartItemId, user.getId(), quantity);

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Updated");
            response.put("cartItem", updatedItem);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(401).body(error);
        }
    }

    // DELETE /api/cart/{cartItemId} - Remove from cart
    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<?> removeFromCart(
            HttpServletRequest request,
            @PathVariable Integer cartItemId) {
        try {
            AuthModel user = getLoggedInUser(request);
            cartService.removeFromCart(cartItemId, user.getId());

            Map<String, String> response = new HashMap<>();
            response.put("message", "Removed from cart");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("message", e.getMessage());
            return ResponseEntity.status(401).body(error);
        }
    }
}