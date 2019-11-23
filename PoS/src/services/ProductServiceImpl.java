package services;

import java.util.List;

import dao.ProductDAO;
import model.Product;

public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO = new ProductDAO();

	@Override
	public List<Product> getAllProduct() {
		List<Product> products = productDAO.getAllProduct();
		return products;
	}

	@Override
	public void addProduct(Product product) {
		productDAO.addProduct(product);
		
	}

	@Override
	public void deleteProduct(Long ID) {
		productDAO.deleteProduct(ID);
		
	}

	@Override
	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
		
	}
	
	
	
	
	
	

}
