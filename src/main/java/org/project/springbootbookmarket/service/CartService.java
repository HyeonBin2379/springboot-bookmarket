package org.project.springbootbookmarket.service;

import org.project.springbootbookmarket.domain.Cart;

public interface CartService {

    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
}
