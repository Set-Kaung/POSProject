package services;

import java.util.List;

import dao.CategoryDAO;
import model.Category;

public class CategoryServiceImpl implements CategoryService {
	CategoryDAO categoryDAO = new CategoryDAO();
	
	public List<Category> getAllCategories() {
		
		
		List<Category> categories = categoryDAO.getAllCategory();
		return categories;
	}

	@Override
	public void addCategory(String name) {
		categoryDAO.addCategory(name);
		
	}

	@Override
	public void updateCategory(Long ID, String name) {
		categoryDAO.updateCategory(ID, name);
		
	}

	@Override
	public void deleteCategory(Long ID) {
		categoryDAO.deleteCategory(ID);
		
	}

}
