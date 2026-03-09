package org.practising.shopingbackend.controller;

import org.practising.shopingbackend.model.ProductsModel;
import org.practising.shopingbackend.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    private ProductServices productServices;

    @Autowired
    public void setProductServices(ProductServices productServices) {
        this.productServices = productServices;
    }

    @PostMapping("/m")
    public ProductsModel save(@RequestBody ProductsModel productsModel) {
        return productServices.save(productsModel);
    }

    @GetMapping("/cart")
    public List<ProductsModel> findAll() {
        return productServices.findAll();
    }
}
