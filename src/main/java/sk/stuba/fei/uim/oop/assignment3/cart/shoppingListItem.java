package sk.stuba.fei.uim.oop.assignment3.cart;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class shoppingListItem {
    @Id
    private Long productId;
    private int amount;
}
