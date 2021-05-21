package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sk.stuba.fei.uim.oop.assignment3.product.IProductService;
import sk.stuba.fei.uim.oop.assignment3.product.Product;

@Service
public class CartService implements ICartService{

    private CartRepository repository;

    @Autowired
    private IProductService productService;

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

    @Override
    public Cart addItem(Long id, ShoppingListItemRequest itemRequest) {
        Cart cart = this.repository.findById(id).orElseThrow();
        Product product = this.productService.getProductsById(itemRequest.getProductId());
        if((cart.isPayed()) || (itemRequest.getAmount() > product.getAmount())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else{
            product.setAmount(product.getAmount() - itemRequest.getAmount());
            if(!cart.getShoppingList().contains(new ShoppingListItem(itemRequest))) {
                cart.getShoppingList().add(new ShoppingListItem(itemRequest));
            }
        }
        return cart;
    }

    @Override
    public String pay(Long id) {
        Cart cart = this.repository.findById(id).orElseThrow();
        if(cart.isPayed()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        else{
            cart.setPayed(true);
            int sum = 0;
            for (var item : cart.getShoppingList()){
                Product product = this.productService.getProductsById(item.getProductId());
                sum += product.getPrice();
            }
            return ""+sum;
        }
    }
}
