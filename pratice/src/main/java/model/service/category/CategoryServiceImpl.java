package model.service.category;

import model.dao.category.CategoryDAO;
import model.dao.category.CategoryDAOImpl;
import model.entity.Category;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    private  CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public boolean save(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public Category findById(Integer integer) {
        return categoryDAO.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        categoryDAO.delete(integer);
    }
}
