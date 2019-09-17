package com.example.produktythymeleaf.repository;

import com.example.produktythymeleaf.model.Product;
import com.example.produktythymeleaf.model.category.ProductCategory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private List<Product> products;

    public ProductRepository() {
        products = new LinkedList<>();
        products.add(new Product("czekolada", BigDecimal.valueOf(2.5), ProductCategory.GROCERIES));
        products.add(new Product("płyn do naczyń", BigDecimal.valueOf(7.49), ProductCategory.HOUSEHOLD_ITEMS));
        products.add(new Product("telewizor", BigDecimal.valueOf(1999), ProductCategory.OTHER));
    }

    public List<Product> getAll() {
        return products;
    }

    public List<Product> getByCategory(ProductCategory category) {
        return products.stream()
                .filter(Objects::nonNull)
                .filter(product -> product.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    public void add(String name, BigDecimal price, ProductCategory category) {
        products.add(new Product(name, price, category));
    }

    public BigDecimal sumPrice(List<Product> products) {
        BigDecimal result = new BigDecimal("0");
        for (Product product : products) {
            result = result.add(product.getPrice());
        }
        return result;
    }

}
