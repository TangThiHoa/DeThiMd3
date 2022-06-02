package service;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService{
    CategoryService categoryService = new CategoryImpl();

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
    public void add(Product product) {

    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();


        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product");) {
            System.out.println(preparedStatement); //in ra câu truy vấn.
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String color= rs.getString("color");
                String description = rs.getString("description");
                int  categoryId = rs.getInt("id"); // lấy ra classId từ bảng student trong db
                Category category = categoryService.findById(categoryId); // từ classId vừa lấy được, dùng ClassService để tìm đối tượng class tương ứng
                products.add(new Product(id, name, price,color,description,category)); //thêm đối tượng là danh sách
            }
        } catch (SQLException e) {
        }
        return products;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> products = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ?");) {
            preparedStatement.setString(1, "%" + name + "%");
            System.out.println(preparedStatement); //in ra câu truy vấn.
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String findname = rs.getString("name");
                int price = rs.getInt("price");
                String color= rs.getString("color");
                String description = rs.getString("description");
                int  categoryId = rs.getInt("id"); // lấy ra classId từ bảng student trong db
                Category category = categoryService.findById(categoryId); // từ classId vừa lấy được, dùng ClassService để tìm đối tượng class tương ứng
                products.add(new Product(id, name, price,color,description,category));
            }
        } catch (SQLException e) {
        }
        return products;
    }
    }

