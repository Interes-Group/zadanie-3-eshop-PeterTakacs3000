package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Product create(ProductRequest request);
    Product getProductsById(Long id);
    Product update(Long id, ProductRequestUpdate request);
    void delete(Long id);
    int getAmount(Long id);
    int addAmount(Long id, ProductRequestAmount requestAmount);
}
