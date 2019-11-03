package services;

import java.util.List;

import dao.BrandDAO;
import model.Brand;

public class BrandServiceImpl implements BrandService {
	BrandDAO brandDAO = new BrandDAO();
	@Override
	public List<Brand> getAllBrand() {
		List<Brand> brands = brandDAO.getAllBrand();
		return brands;
	}
	@Override
	public void addBrand(String name) {
		brandDAO.addBrand(name);
		
	}
	@Override
	public void updateBrand(Long ID, String name) {
		brandDAO.updateBrand(ID, name);
		
	}
	@Override
	public void deleteBrand(Long ID) {
		brandDAO.deleteBrand(ID);
		
	}

}
