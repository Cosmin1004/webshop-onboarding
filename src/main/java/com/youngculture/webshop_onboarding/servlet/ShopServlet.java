package com.youngculture.webshop_onboarding.servlet;

import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.impl.CategoryRepositoryImpl;
import com.youngculture.webshop_onboarding.repository.impl.ProductRepositoryImpl;
import com.youngculture.webshop_onboarding.service.CategoryService;
import com.youngculture.webshop_onboarding.service.Impl.CategoryServiceImpl;
import com.youngculture.webshop_onboarding.service.Impl.ProductServiceImpl;
import com.youngculture.webshop_onboarding.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/shop")
public class ShopServlet extends HttpServlet {

    private List<String> categories = new ArrayList<>();
    private List<Product> products = new ArrayList<>();

    ProductService productService =
            new ProductServiceImpl(new ProductRepositoryImpl());
    CategoryService categoryService =
            new CategoryServiceImpl(new CategoryRepositoryImpl());

    public ShopServlet() {
        super();
    }

    @Override
    public void init() {
        categories = categoryService.getAllCategoryNames();
        getServletContext().setAttribute("categories", categories);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("shop.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
