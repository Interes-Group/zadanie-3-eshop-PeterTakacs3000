package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class ProductService implements IProductService{

    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Product> getAll() {
        return this.repository.findAll();
    }

    @Override
    public Product create(ProductRequest request) {
        Product newProduct = new Product();
        newProduct.setDescription(request.getDescription());
        newProduct.setPrice(request.getPrice());
        newProduct.setUnit(request.getUnit());
        newProduct.setAmount(request.getAmount());
        newProduct.setName(request.getName());
        return this.repository.save(newProduct);
    }

    @Override
    public Product getProductsById(Long id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public Product update(Long id, ProductRequestUpdate request) {
        Product pToUpdate = this.repository.findById(id).orElseThrow();
        if (request.getName() != null) pToUpdate.setName(request.getName());
        if (request.getDescription() != null) pToUpdate.setDescription(request.getDescription());
        return pToUpdate;
    }

    @Override
    public void delete(Long id) {
        Product pToDelete = this.repository.findById(id).orElseThrow();
        this.repository.delete(pToDelete);
    }

    @Override
    public int getAmount(Long id) {
        return this.repository.findById(id).orElseThrow().getAmount();
    }

    @Override
    public int addAmount(Long id, ProductRequestAmount requestAmount) {
        Product pToAddAmount = this.repository.findById(id).orElseThrow();
        pToAddAmount.setAmount(pToAddAmount.getAmount() + requestAmount.getAmount());
        return pToAddAmount.getAmount();
    }
}
