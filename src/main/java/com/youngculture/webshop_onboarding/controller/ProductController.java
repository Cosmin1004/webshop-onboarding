package com.youngculture.webshop_onboarding.controller;

import com.youngculture.webshop_onboarding.model.Product;
import com.youngculture.webshop_onboarding.repository.ProductRepository;
import com.youngculture.webshop_onboarding.repository.impl.ProductRepositoryImpl;
import com.youngculture.webshop_onboarding.service.Impl.ProductServiceImpl;
import com.youngculture.webshop_onboarding.service.ProductService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/product")
public class ProductController extends HttpServlet {

    List<Product> products = new ArrayList<>();

    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());

    @Override
    public void init() {
        products = productService.getAllProducts();
        getServletContext().setAttribute("products", products);
    }

}
