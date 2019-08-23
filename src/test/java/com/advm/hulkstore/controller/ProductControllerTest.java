package com.advm.hulkstore.controller;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.advm.hulkstore.dao.BillDAO;
import com.advm.hulkstore.dao.KardexDAO;
import com.advm.hulkstore.dao.ProductDAO;
import com.advm.hulkstore.model.Bill;
import com.advm.hulkstore.model.Kardex;
import com.advm.hulkstore.model.Product;
import com.advm.hulkstore.service.BillService;
import com.advm.hulkstore.service.KardexService;
import com.advm.hulkstore.service.ProductService;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private KardexService kardexService;

    @MockBean
    private BillService billtService;

    @MockBean
    private BillDAO billDAO;

    @MockBean
    private KardexDAO kardexDAO;

    @MockBean
    private ProductDAO productDAO;

    @Test
    public void index() throws Exception {
        mvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("product/product"));
        verify(productService, times(1)).getAll();
    }

    @Test
    public void showSave() throws Exception {
        mvc.perform(get("/save/init")).andExpect(status().isOk()).andExpect(view().name("product/save"));
    }

    @Test
    public void save() throws Exception {
        Long productId = new Long("123");
        mvc.perform(get("/detail/{id}", productId)).andExpect(status().isOk()).andExpect(view().name("product/detail"));
        verify(productService, times(1)).get(productId);
    }

    @Test
    public void showSale() throws Exception {

        Long id = new Long("123");
        Product product = new Product();
        product.setDescription("Vaso Marvel");
        product.setId(id);
        product.setName("Marvel");

        when(productService.get(id)).thenReturn(product);
        when(kardexService.getLastKardex(id)).thenReturn(this.getStaticKardexForTest());

        mvc.perform(get("/sale/{id}", product.getId())).andExpect(status().isOk())
                .andExpect(view().name("product/sale"));
        verify(productService, times(1)).get(id);
    }

    @Test
    public void showBuy() throws Exception {

        Long id = new Long("123");
        Product product = new Product();
        product.setDescription("Vaso Marvel");
        product.setId(id);
        product.setName("Marvel");

        when(productService.get(id)).thenReturn(product);

        mvc.perform(get("/buy/{id}", product.getId())).andExpect(status().isOk()).andExpect(view().name("product/buy"));
        verify(productService, times(1)).get(id);
    }

    @Test
    public void saveBd() throws Exception {

        Long productId = new Long("123");

        when(kardexService.getLastKardex(productId)).thenReturn(this.getStaticKardexForTest());
        when(kardexService.save(Mockito.any(Kardex.class))).thenReturn(this.getStaticKardexForTest());
        when(billtService.save(Mockito.any(Bill.class))).thenReturn(this.getStaticBillForTest());

        mvc.perform(post("/sale").contentType(MediaType.APPLICATION_JSON)
                .flashAttr("kardex", this.getStaticKardexForTest()).flashAttr("product", this.getStaticProductForTest())
                .flashAttr("bill", this.getStaticBillForTest())).andExpect(status().is(302)).andExpect(view().name("redirect:/"));
    }
    
    @Test
    public void buyBd() throws Exception {

        Long productId = new Long("123");

        when(kardexService.getLastKardex(productId)).thenReturn(this.getStaticKardexForTest());
        when(kardexService.save(Mockito.any(Kardex.class))).thenReturn(this.getStaticKardexForTest());
        when(billtService.save(Mockito.any(Bill.class))).thenReturn(this.getStaticBillForTest());

        mvc.perform(post("/buy").contentType(MediaType.APPLICATION_JSON)
                .flashAttr("kardex", this.getStaticKardexForTest()).flashAttr("product", this.getStaticProductForTest())
                .flashAttr("bill", this.getStaticBillForTest())).andExpect(status().is(302)).andExpect(view().name("redirect:/"));
    }
    
    @Test
    public void saleBd() throws Exception {

        Long productId = new Long("123");

        when(kardexService.getLastKardex(productId)).thenReturn(this.getStaticKardexForTest());
        when(kardexService.save(Mockito.any(Kardex.class))).thenReturn(this.getStaticKardexForTest());
        when(billtService.save(Mockito.any(Bill.class))).thenReturn(this.getStaticBillForTest());

        mvc.perform(post("/sale").contentType(MediaType.APPLICATION_JSON)
                .flashAttr("kardex", this.getStaticKardexForTest()).flashAttr("product", this.getStaticProductForTest())
                .flashAttr("bill", this.getStaticBillForTest())).andExpect(status().is(302)).andExpect(view().name("redirect:/"));
    }

    public Product getStaticProductForTest() {
        Product product = new Product();
        product.setDescription("Vaso Marvel");
        product.setId(new Long("123"));
        product.setName("Marvel");
        product.setKardexs(new ArrayList<Kardex>());

        return product;
    }

    public Bill getStaticBillForTest() {
        Bill bill = new Bill();
        bill.setDescription("Se venden cuatro vasos");
        bill.setDate(new Date());
        bill.setId(new Long("456"));
        bill.setType("Sale");

        return bill;
    }

    public Kardex getStaticKardexForTest() {
        Kardex kardex = new Kardex();
        kardex.setProduct(this.getStaticProductForTest());
        kardex.setId(new Long("789"));
        kardex.setStock(10);
        kardex.setUnitPrice(5000);
        kardex.setStockInventory(20);
        kardex.setUnitPriceInventory(5000);

        return kardex;
    }

}
