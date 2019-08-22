package com.advm.hulkstore.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.advm.hulkstore.model.Bill;
import com.advm.hulkstore.model.Kardex;
import com.advm.hulkstore.model.Product;
import com.advm.hulkstore.service.BillService;
import com.advm.hulkstore.service.KardexService;
import com.advm.hulkstore.service.ProductService;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private KardexService kardexService;

    @Autowired
    private BillService billService;

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

    @GetMapping("/detail/{id}")
    public String showSave(@PathVariable Long id, Model model) {
        Product product = productService.get(id);

        if (product != null) {
            model.addAttribute("product", product);
            model.addAttribute("kardexs", product.getKardexs());
        } else {
            model.addAttribute("product", null);
        }

        return "product/detail";
    }

    @PostMapping("/save")
    public String save(Product product, Kardex kardex, Model model) {
        if (product != null && kardex != null) {
            Product productUploaded = productService.save(product);
            kardex.setProduct(productUploaded);
            kardex.setStockInventory(kardex.getStock());
            kardex.setTotalPriceInventory(kardex.getTotalPrice());
            kardex.setUnitPriceInventory(kardex.getUnitPrice());
            kardex.setTotalPrice(kardexService.calculateTotalPrice(kardex.getStock(), kardex.getUnitPrice()));
            kardex.setTotalPriceInventory(kardex.getTotalPrice());
            kardex.setType("Existencia");

            productUploaded.getKardexs().add(kardexService.save(kardex));
            productService.save(productUploaded);

        }
        return "redirect:/";
    }

    @GetMapping("/sale/{id}")
    public String showSale(@PathVariable Long id, Model model) {
        if (id != null) {
            Product product = productService.get(id);
            model.addAttribute("product", product);
            model.addAttribute("kardex", new Kardex());
            model.addAttribute("bill", new Bill());
        }
        return "product/sale";
    }

    @PostMapping("/sale")
    public String sale(Product product, Kardex kardex, Bill bill, Model model) {

        if (product != null && kardex != null) {

            /* se consulta el ultimo registro kardex de este producto */
            Kardex lastKardex = kardexService.getLastKardex(product.getId());
            product = lastKardex.getProduct();
            kardex.setProduct(product);
            kardex.setId(null);
            kardex.setUnitPrice(lastKardex.getUnitPriceInventory());
            kardex.setTotalPrice(kardexService.calculateTotalPrice(kardex.getStock(), kardex.getUnitPrice()));
            kardex.setType("Sale");

            kardex.setStockInventory(kardexService.calculateNewStock(product.getId(), kardex.getStock(), "Sale"));
            kardex.setUnitPriceInventory(kardexService.calculateNewUnitPrice(product.getId(),
                    kardex.getStockInventory(), kardex.getTotalPrice(), "Sale"));
            kardex.setTotalPriceInventory(
                    kardexService.calculateTotalPrice(kardex.getStockInventory(), kardex.getUnitPriceInventory()));

            bill.setProducts(new ArrayList<Product>());
            bill.getProducts().add(product);
            bill.setType("Sale");

            bill = billService.save(bill);
            kardex.setBill(billService.save(bill));
            kardex.setDate(new Date());

            kardex = kardexService.save(kardex);

            product.getKardexs().add(kardex);
            bill.setKardexs(new ArrayList<Kardex>());
            bill.getKardexs().add(kardex);

            productService.save(product);

        }

        return "redirect:/";
    }

    @GetMapping("/buy/{id}")
    public String showBuy(@PathVariable Long id, Model model) {
        if (id != null) {
            Product product = productService.get(id);

            model.addAttribute("product", product);
            model.addAttribute("kardex", new Kardex());
            model.addAttribute("bill", new Bill());
        }
        return "product/buy";
    }

    @PostMapping("/buy")
    public String buy(Product product, Kardex kardex, Bill bill, Model model) {

        if (product != null && kardex != null) {

            /* se consulta el ultimo registro kardex de este producto */
            Kardex lastKardex = kardexService.getLastKardex(product.getId());
            product = lastKardex.getProduct();
            kardex.setProduct(product);
            kardex.setId(null);
            kardex.setTotalPrice(kardexService.calculateTotalPrice(kardex.getStock(), kardex.getUnitPrice()));
            kardex.setType("Buy");

            kardex.setStockInventory(kardexService.calculateNewStock(product.getId(), kardex.getStock(), "Buy"));
            kardex.setUnitPriceInventory(kardexService.calculateNewUnitPrice(product.getId(),
                    kardex.getStockInventory(), kardex.getTotalPrice(), "Buy"));
            kardex.setTotalPriceInventory(
                    kardexService.calculateTotalPrice(kardex.getStockInventory(), kardex.getUnitPriceInventory()));

            bill.setProducts(new ArrayList<Product>());
            bill.getProducts().add(product);
            bill.setType("Buy");

            bill = billService.save(bill);
            kardex.setBill(billService.save(bill));
            kardex.setDate(new Date());

            kardex = kardexService.save(kardex);

            product.getKardexs().add(kardex);
            bill.setKardexs(new ArrayList<Kardex>());
            bill.getKardexs().add(kardex);

            productService.save(product);

        }

        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {

        productService.delete(id);

        return "redirect:/";
    }
}
