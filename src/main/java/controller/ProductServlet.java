package controller;

import model.Category;
import model.Product;
import service.CategoryImpl;
import service.CategoryService;
import service.ProductService;
import service.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    CategoryService categoryService = new CategoryImpl();
    ProductService productService = new ProductServiceImpl();

    private void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        if (product != null) {
            productService.delete(id);
            try {
                response.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "create":
                showCreate(request, response);
                break;
            case "deleteForm":
                showDelete(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            case "edit":
                ShowEdit(request, response);
                break;
            default:
                showList(request, response);
        }
    }

    private void ShowEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<Category> categories = categoryService.findAll();
        request.setAttribute("ds", categories);
        Product product = productService.findById(id);
        request.setAttribute("sua", product);
        request.getRequestDispatcher("product/edit.jsp").forward(request, response);
    }


    private void showDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher = null;
        if (product != null) {
            request.setAttribute("st", product);
            dispatcher = request.getRequestDispatcher("product/delete.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("ds", categories);
        request.getRequestDispatcher("product/create.jsp").forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Product> productList;
        if (name != null & name != "") {
            productList = productService.findByName(name);
        } else {
            productList = productService.findAll();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        request.setAttribute("ds", productList);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String act = request.getParameter("act");
        if (act == null) {
            act = "";
        }
        switch (act) {
            case "create":
                create(request, response);
                break;
            case "edit":
                try {
                    edit(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findById(categoryId);
        Product product = new Product(id, name, price, color, description, category);
        productService.update(product);
        response.sendRedirect("/products");
    }



    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        Category category = categoryService.findById(categoryId);
        Product product = new Product(id, name, price, color, description, category);
        productService.add(product);
        response.sendRedirect("/products");

    }
}
