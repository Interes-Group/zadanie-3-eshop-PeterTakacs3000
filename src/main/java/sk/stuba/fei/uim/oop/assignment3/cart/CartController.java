package sk.stuba.fei.uim.oop.assignment3.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService service;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CartResponse createCart(){
        return new CartResponse(this.service.create());
    }

    @PostMapping("/{id}/add")
    public CartResponse addItemToCart(@PathVariable("id") Long id, @RequestBody ShoppingListItemRequest itemRequest){
        return new CartResponse(this.service.addItem(id, itemRequest));
    }

    @GetMapping("/{id}")
    public CartResponse getCartById (@PathVariable("id") Long id){
        return new CartResponse(this.service.getCart(id));
    }

    @GetMapping("/{id}/pay")
    public String payCart(@PathVariable("id") Long id){
        return this.service.pay(id);
    }

    @DeleteMapping("/{id}")
    void deleteCartById(@PathVariable("id") Long id){
        this.service.deleteCart(id);
    }
}
