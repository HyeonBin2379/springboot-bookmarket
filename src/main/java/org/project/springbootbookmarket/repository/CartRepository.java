package org.project.springbootbookmarket.repository;

import org.project.springbootbookmarket.domain.Cart;

public interface CartRepository {

    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
}
