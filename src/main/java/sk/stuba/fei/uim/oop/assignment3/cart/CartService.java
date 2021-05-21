package sk.stuba.fei.uim.oop.assignment3.cart;

import antlr.collections.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService implements ICartService{

    private CartRepository repository;

    @Autowired
    public CartService(CartRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cart create() {
        Cart cart = new Cart();
        return this.repository.save(cart);
    }
}
