package com.advm.hulkstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.advm.hulkstore.model.Kardex;
import com.advm.hulkstore.model.Product;
import com.advm.hulkstore.service.KardexService;
import com.advm.hulkstore.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private KardexService kardexService;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productService.getAll());
        return "product/product";
    }

    @GetMapping("/save/init")
    public String showSave(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("kardex", new Kardex());

        return "product/save";
    }

    @PostMapping("/save")
    public String save(Product product, Kardex kardex, Model model) {
        if (product != null && kardex != null) {
            Product productUploaded = productService.save(product);
            kardex.setProduct(productUploaded);
            kardex.setStockInventory(kardex.getStock());
            kardex.setTotalPriceInventory(kardex.getTotalPrice());
            kardex.setUnitPriceInventory(kardex.getUnitPrice());
            kardex.setTotalPrice(kardex.getStock() * kardex.getUnitPrice());
            kardex.setTotalPriceInventory(kardex.getStock() * kardex.getUnitPrice());
            kardex.setType("Existencia");
         
            productUploaded.getKardexs().add(kardexService.save(kardex));
            productService.save(productUploaded);

        }
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {

        productService.delete(id);

        return "redirect:/";
    }
}
