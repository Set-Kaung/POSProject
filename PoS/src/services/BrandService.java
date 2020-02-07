package services;

import java.util.List;

import model.Brand;
public interface BrandService {
	List<Brand> getAllBrand();
	void addBrand(String name);
	void updateBrand(Long ID, String name);
	void deleteBrand(Long ID);
	
}
