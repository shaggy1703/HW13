package org.skypro.skyshop.product;

public class Product {
    private final String product;
    private final int productPrice;

    public Product(String product, int productPrice) {
        this.product = product;
        this.productPrice = productPrice;
    }

    public String getProduct() {

        return product;
    }

    public int getProductPrice() {

        return productPrice;
    }
}
