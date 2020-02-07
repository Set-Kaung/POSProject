package services;

import java.util.List;

import dao.ProductDAO;
import dto.ProductDTO;
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

	@Override
	public void restockProduct(Long ID, Long stock) {
	productDAO.restockProduct(ID, stock);
		
	}

	@Override
	public ProductDTO getProductByBarcode(Long barcode) {
		return productDAO.getProductByBarcode(barcode);
		
	}
	
	
	
	
	
	

}
