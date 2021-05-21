package sk.stuba.fei.uim.oop.assignment3.cart;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
public class ShoppingListItem {
    @Id
    private Long productId;
    private int amount;

    public ShoppingListItem(ShoppingListItemRequest itemRequest) {
        this.productId = itemRequest.getProductId();
        this.amount = itemRequest.getAmount();
    }
}
