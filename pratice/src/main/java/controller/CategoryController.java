package controller;

import model.entity.Category;
import model.service.category.CategoryService;
import model.service.category.CategoryServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CategoryController", value = "/CategoryController")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showList(request, response);
        }
        switch (action) {
            case "add":
                request.getRequestDispatcher("views/addCategory.jsp").forward(request, response);
                break;
            case "delete":
                int delId= Integer.parseInt(request.getParameter("id"));
                categoryService.delete(delId);
                showList(request,response);
                break;
            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Category cat = categoryService.findById(idEdit);
                request.setAttribute("cat",cat);
                request.getRequestDispatcher("views/edit-cat.jsp").forward(request,response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action==null){
            showList(request,response);
        }
        switch (action){
            case "add":
                handleAddNewCategory(request,response);
                break;
            case "edit":
                handleEditCategory(request,response);
                break;
        }
    }

    private void handleEditCategory(HttpServletRequest request, HttpServletResponse response) {
    Category cat = new Category();
    cat.setId(Integer.parseInt(request.getParameter("id")));
    cat.setName(request.getParameter("name"));
    cat.setStatus(Boolean.parseBoolean(request.getParameter("status")));
    categoryService.save(cat);
        try {
            showList(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleAddNewCategory(HttpServletRequest request, HttpServletResponse response) {
        Category cat = new Category();
        cat.setName(request.getParameter("categoryName"));
        cat.setStatus(Boolean.parseBoolean(request.getParameter("categoryStatus")));
        System.out.println("ok?" +
                "");
        categoryService.save(cat);
        try {
            showList(request,response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categoryList", categories);
        req.getRequestDispatcher("views/list.jsp").forward(req, resp);
    }
}