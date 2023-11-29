package model.dao.category;

import model.entity.Category;
import utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_show_category()");
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                cat.setStatus(rs.getBoolean("status"));
                categories.add(cat);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return categories;
    }

    @Override
    public boolean save(Category category) {
        Connection con = ConnectionDB.openCon();
        CallableStatement cs = null;
        int check;
        try {
            if (category.getId() == 0) {
                cs = con.prepareCall("call proc_create_new_category(?,?)");
                cs.setString(1, category.getName());
                cs.setBoolean(2, category.isStatus());
//                check = cs.executeUpdate();
            } else {
                cs = con.prepareCall("call proc_update_category(?,?,?)");
                cs.setInt(1, category.getId());
                cs.setString(2, category.getName());
                cs.setBoolean(3, category.isStatus());

            }
            check = cs.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return false;
    }

    @Override
    public Category findById(Integer integer) {
        Category cat = new Category();
        Connection con = ConnectionDB.openCon();
        try {
            CallableStatement cs = con.prepareCall("call proc_find_cat_by_id(?)");
            cs.setInt(1, integer);
            ResultSet rs = cs.executeQuery();
            while (rs.next()) {
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));
                cat.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(con);
        }
        return cat;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = ConnectionDB.openCon();
        try {

            CallableStatement cs = connection.prepareCall("call proc_delete_category(?)");
            cs.setInt(1, integer);
            cs.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeCon(connection);
        }
    }
}
