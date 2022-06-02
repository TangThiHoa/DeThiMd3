package service;

import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryImpl implements CategoryService{
    List<Category>categories;

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product2001?useSSL=false", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void add(Category category) {
//        try (Connection connection = getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement("insert into category(id,name) values (?, ?)");) {
//            preparedStatement.setString(1, category.getId());
//            preparedStatement.setInt(2, category.getName());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
    }



    @Override
    public Category findById(int id) {
        Category category = new Category();
        try (Connection connection = getConnection();

             PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id = ?");) {
            System.out.println(preparedStatement);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                category = (new Category(id, name));

            }
        } catch (SQLException e) {

        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category");) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }
        } catch (SQLException e) {

        }
        return categories;
    }


    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Category category) {
        return false;
    }

    @Override
    public List<Category> findByName(String name) {
        List<Category> categories  = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from category where name like ?");) {
            preparedStatement.setString(1,"%"+name+"%");
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameFind = rs.getString("name");
                categories.add(new Category(id, nameFind));
            }
        } catch (SQLException e) {

        }
        return categories;
    }


}
