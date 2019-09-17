package com.example.produktythymeleaf.controller;

import com.example.produktythymeleaf.model.Product;
import com.example.produktythymeleaf.model.category.ProductCategory;
import com.example.produktythymeleaf.repository.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ProductController {

    private ProductRepository repository;
    private List<Product> products;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/list")
    public String getAllList(@RequestParam(required = false) ProductCategory category, Model model) {

        if (StringUtils.isEmpty(category)) {
            products = repository.getAll();
        } else {
            products = repository.getByCategory(category);
        }
        BigDecimal sumPrice = repository.sumPrice(products);
        model.addAttribute("products", products);
        model.addAttribute("sum", sumPrice);
        return "list";
    }

    @GetMapping("/table")
    public String getAllTable(@RequestParam(required = false) ProductCategory category, Model model) {

        if (StringUtils.isEmpty(category)) {
            products = repository.getAll();
        } else {
            products = repository.getByCategory(category);
        }
        BigDecimal sumPrice = repository.sumPrice(products);
        model.addAttribute("products", products);
        model.addAttribute("sum", sumPrice);
        return "table";
    }

    @PostMapping("/add")
    public String add(@RequestParam(value = "name") String name,
                      @RequestParam("price") BigDecimal price,
                      @RequestParam(value = "category") ProductCategory category) {
        if (isParamsOk(name, price, category)) {
            return "redirect:/err";
        } else {

            repository.add(name, price, category);
            return "redirect:/success";
        }
    }

    private String convertToString(List<Product> products) {
        String result = "";

        for (Product product : products) {
            result += product.toString() + "<br>";
        }
        return result;
    }

    private boolean isParamsOk(String name, BigDecimal price, ProductCategory category) {
        return StringUtils.isEmpty(name) || StringUtils.isEmpty(price) || StringUtils.isEmpty(category);
    }

    @GetMapping("/success")
    public String successPage() {
        return "success.html";
    }

    @GetMapping("/err")
    public String errPage() {
        return "err.html";
    }

}