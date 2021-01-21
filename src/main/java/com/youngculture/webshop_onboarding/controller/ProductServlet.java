package com.youngculture.webshop_onboarding.controller;

import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.impl.CategoryRepositoryImpl;
import com.youngculture.webshop_onboarding.repository.impl.ProductRepositoryImpl;
import com.youngculture.webshop_onboarding.service.CategoryService;
import com.youngculture.webshop_onboarding.service.Impl.CategoryServiceImpl;
import com.youngculture.webshop_onboarding.service.Impl.ProductServiceImpl;
import com.youngculture.webshop_onboarding.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductServlet extends HttpServlet {

    private List<String> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    private boolean categoryRendered = true;

    private ProductService productService;
    private CategoryService categoryService;

    public ProductServlet() {
        productService = new ProductServiceImpl(
                new ProductRepositoryImpl());
        categoryService = new CategoryServiceImpl(
                new CategoryRepositoryImpl());
    }

    @Override
    public void init() {
        //load information for startup (categories & all products)
        categories = categoryService.getAllCategoryNames();
        products = productService.getAllProducts();
        getServletContext().setAttribute("rendered", true);
        getServletContext().setAttribute("categories", categories);
        getServletContext().setAttribute("products", products);
    }

    //load products
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String category = request.getParameter("category");
        categoryRendered = handleCategoryFieldRendered(category);
        products = handleGetProducts(category);
        setRequestAttributes(request);
        request.getRequestDispatcher("product.jsp")
                .forward(request, response);
    }

    public List<Product> handleGetProducts(String category) {
        List<Product> products;
        if (category == null || category.equals("all")) {
            products = productService.getAllProducts();
        } else {
            products = productService.getProductsByCategory(category);
        }
        return products;
    }

    public boolean handleCategoryFieldRendered(String category) {
        return category == null || category.equals("all");
    }

    private void setRequestAttributes(HttpServletRequest request) {
        request.setAttribute("rendered", categoryRendered);
        request.setAttribute("products", products);
    }

}
