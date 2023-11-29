package model.dao.product;

import model.dao.category.CategoryDAO;
import model.dao.category.CategoryDAOImpl;
import model.entity.Product;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    public static CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_show_product");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Product pro = new Product();
                pro.setId(rs.getInt("id"));
                pro.setName(rs.getString("name"));
                pro.setPrice(rs.getInt("price"));
                pro.setCategory(categoryDAO.findById(rs.getInt("category_id")));
                products.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return products;
    }

    @Override
    public boolean save(Product product) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            con.setAutoCommit(false);
            if (product.getId() == 0) {
                cs = con.prepareCall("call proc_create_new_product(?,?,?)");
                cs.setString(1, product.getName());
                cs.setFloat(2, product.getPrice());
                cs.setInt(3, product.getCategory().getId());
            } else {
                cs = con.prepareCall("call proc_update_product(?,?,?,?)");
                cs.setString(1, product.getName());
                cs.setFloat(2, product.getPrice());
                cs.setInt(3, product.getCategory().getId());
                cs.setInt(4, product.getId());
            }
            check = cs.executeUpdate();
            if (check > 0) {
                con.commit();
                return true;
            }

        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return false;
    }


    @Override
    public Product findById(Integer integer) {
        Product pro = new Product();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_pro_by_id(?)");
            cs.setInt(1,integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()){
                pro.setId(rs.getInt("id"));
                pro.setName(rs.getString("name"));
                pro.setPrice(rs.getInt("price"));
                pro.setCategory(categoryDAO.findById(rs.getInt("category_id")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
        return pro;
    }

    @Override
    public void delete(Integer integer) {
Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_delete_product(?)");
            cs.setInt(1,integer);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeCon(con);
        }
    }
}
