package model.service.product;

import model.dao.product.ProductDAO;
import model.dao.product.ProductDAOImpl;
import model.entity.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    public static ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }

    @Override
    public boolean save(Product product) {
        return productDAO.save(product);
    }

    @Override
    public Product findById(Integer integer) {
        return productDAO.findById(integer);
    }

    @Override
    public void delete(Integer integer) {
        productDAO.delete(integer);
    }
}
