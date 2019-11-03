package services;

import java.util.List;

import model.Category;

public interface CategoryService {
 List<Category> getAllCategories();
 void addCategory(String name);
 void updateCategory(Long ID, String name);
 void deleteCategory(Long ID);
}
