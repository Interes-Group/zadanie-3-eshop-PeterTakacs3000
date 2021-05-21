package sk.stuba.fei.uim.oop.assignment3.product;

import lombok.Getter;

@Getter
public class ProductResponseAmount {
    private int amount;


    public ProductResponseAmount(int a) {
        this.amount = a;
    }
}
