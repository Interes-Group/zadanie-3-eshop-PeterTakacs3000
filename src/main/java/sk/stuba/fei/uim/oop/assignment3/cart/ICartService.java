package sk.stuba.fei.uim.oop.assignment3.cart;

public interface ICartService {
    Cart create();
    Cart getCart(Long id);
    void deleteCart(Long id);
    Cart addItem(Long id, ShoppingListItemRequest itemRequest);
    String pay(Long id);
}
