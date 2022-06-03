package controller;

import model.Category;
import model.Product;
import service.CategoryImpl;
import service.CategoryService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder.create;

@WebServlet(name = "CategoryServlet", urlPatterns = "/categores")
public class CategoryServlet extends HttpServlet {
    CategoryService categoryService = new CategoryImpl();

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
            case "delete":
                showDelete(request,response);
                break;
            case "edit":
                showEdit(request,response);
                break;
            default:

                showList(request, response);
        }


    }

    private void showEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("sua", category);
        request.getRequestDispatcher("/category/edit.jsp").forward(request, response);
    }

    private void showDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Category category = categoryService.findById(id);
        request.setAttribute("xoa", category);
        categoryService.delete(id);
        response.sendRedirect("/categores");
    }


    private void showCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("category/create.jsp").forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        List<Category> categoryList;
        if (name != null & name != "") {
            categoryList = categoryService.findByName(name);
        } else {
            categoryList = categoryService.findAll();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("category/list.jsp");
        request.setAttribute("ds", categoryList);
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
                    edit(request,response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void edit(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Category category1 = new Category(id, name);
        categoryService.update(category1);
        response.sendRedirect("/categores");
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        categoryService.add(new Category(id,name));
        response.sendRedirect("/categores");
    }
    }


