package controller;

import model.entity.Category;
import model.entity.Product;
import model.service.category.CategoryService;
import model.service.category.CategoryServiceImpl;
import model.service.product.ProductService;
import model.service.product.ProductServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    private static CategoryService categoryService = new CategoryServiceImpl();
    private static ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showListProduct(request, response);
        }
        switch (action) {
            case "add":
                List<Category> categories = categoryService.findAll();
                request.setAttribute("categoryList", categories);
                request.getRequestDispatcher("views/addProduct.jsp").forward(request, response);
                break;
            case "delete":
                int idDel = Integer.parseInt(request.getParameter("id"));
                productService.delete(idDel);
                showListProduct(request, response);
                break;
            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                List<Category> category = categoryService.findAll();
                request.setAttribute("categoryList", category);
                Product pro = productService.findById(idEdit);
                request.setAttribute("pro", pro);
                request.getRequestDispatcher("views/edit-pro.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            showListProduct(request, response);
        }
        switch (action) {
            case "add":
                handleAddNewProduct(request, response);
                break;
            case "edit":
                handleEditProduct(request, response);
        }
    }

    private void handleEditProduct(HttpServletRequest request, HttpServletResponse response) {
        Product pro = new Product();
        pro.setId(Integer.parseInt(request.getParameter("pro-id")));
        pro.setName(request.getParameter("pro-name"));
        pro.setPrice(Integer.parseInt(request.getParameter("pro-price")));
        pro.setCategory(categoryService.findById(Integer.valueOf(request.getParameter("pro-catId"))));
        productService.save(pro);
        try {
            showListProduct(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleAddNewProduct(HttpServletRequest request, HttpServletResponse response) {
        Product pro = new Product();
        pro.setName(request.getParameter("pro-name"));
        pro.setPrice(Integer.parseInt(request.getParameter("pro-price")));
        pro.setCategory(categoryService.findById(Integer.valueOf(request.getParameter("pro-catId"))));
        productService.save(pro);
        try {
            showListProduct(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void showListProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        req.setAttribute("productList", productList);
        req.getRequestDispatcher("views/pro-list.jsp").forward(req, resp);
    }
}