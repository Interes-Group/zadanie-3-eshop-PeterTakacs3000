package sk.stuba.fei.uim.oop.assignment3.cart;

import antlr.collections.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

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

    @Override
    public Cart getCart(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCart(Long id) {
        Cart cToDelete = this.repository.findById(id).orElseThrow();
        this.repository.delete(cToDelete);
    }
}
