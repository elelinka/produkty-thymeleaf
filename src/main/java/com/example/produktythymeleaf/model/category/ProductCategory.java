package com.example.produktythymeleaf.model.category;

public enum ProductCategory {
    GROCERIES("art. spożywcze"),
    OTHER("inne"),
    HOUSEHOLD_ITEMS("art. gosp. domowego");

    private String name;

    private ProductCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
