package services;

import java.util.List;

import model.Product;

public interface ProductService {
 List<Product> getAllProduct();
 void addProduct(Product product);
 void deleteProduct(Long ID);
 void updateProduct(Product product);
}
