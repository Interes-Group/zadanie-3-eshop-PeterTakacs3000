package sk.stuba.fei.uim.oop.assignment3.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return this.service.getAll().stream().map(ProductResponse::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductResponse getProductsById(@PathVariable("id") Long id) {
        return new ProductResponse(this.service.getProductsById(id));
    }

    @GetMapping("/{id}/amount")
    public ProductResponseAmount getAmount(@PathVariable("id") Long id){
        return new ProductResponseAmount(this.service.getAmount(id));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse addProduct(@RequestBody ProductRequest request){
        return new ProductResponse(this.service.create(request));
    }

    @PostMapping("/{id}/amount")
    public ProductResponseAmount addAmount(@PathVariable("id") Long id, @RequestBody ProductRequestAmount requestAmount){
        return new ProductResponseAmount(this.service.addAmount(id, requestAmount));
    }

    @PutMapping("/{id}")
    public ProductResponse addProduct(@PathVariable("id") Long id, @RequestBody ProductRequestUpdate request){
        return new ProductResponse(this.service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id){
        this.service.delete(id);
    }
}
