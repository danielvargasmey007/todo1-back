package com.advm.hulkstore.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.advm.hulkstore.dao.KardexDAO;
import com.advm.hulkstore.model.Kardex;
import com.advm.hulkstore.service.KardexService;

@RunWith(SpringRunner.class)
public class KardexServiceImplTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public KardexService employeeService() {
            return new KardexServiceImpl();
        }
    }

    @Autowired
    private KardexService kardexService;

    @MockBean
    private KardexDAO kardexDAO;

    @Test
    public void calculateTotalPrice() {
        double stock = 11;
        double price = 25000;
        assertEquals(275000, kardexService.calculateTotalPrice(stock, price), 0);
    }

    @Test
    public void calculateNewUnitPrice() {
        Long productId = new Long("123");

        when(kardexDAO.findByProductId(productId)).thenReturn(this.getStaticKardexForTest());
        assertEquals(7200, kardexService.calculateNewUnitPrice(productId, 10, 22000, "Buy"), 0);
        assertEquals(1000, kardexService.calculateNewUnitPrice(productId, 10, 22000, "Sale"), 0);
    }

    @Test
    public void calculateNewStock() {
        Long productId = new Long("123");

        when(kardexDAO.findByProductId(productId)).thenReturn(this.getStaticKardexForTest());
        assertEquals(200, kardexService.calculateNewStock(productId, 100, "Buy"), 0);
        assertEquals(0, kardexService.calculateNewStock(productId, 100, "Sale"), 0);
    }

    @Test
    public void getLastKardex() {
        Long productId = new Long("123");

        when(kardexDAO.findByProductId(productId)).thenReturn(this.getStaticKardexForTest());
        assertEquals(this.getStaticKardexForTest().get(0).getId(), kardexService.getLastKardex(productId).getId());
    }

    @Test
    public void getAllKardex() {
        Long productId = new Long("123");

        when(kardexDAO.findByProductId(productId)).thenReturn(this.getStaticKardexForTest());
        assertEquals(this.getStaticKardexForTest().size(), kardexService.getAllKardex(productId).size());
    }

    public List<Kardex> getStaticKardexForTest() {
        Kardex kardex1 = new Kardex();
        kardex1.setId(new Long("123"));
        kardex1.setUnitPrice(1000);
        kardex1.setStock(10);
        kardex1.setStockInventory(100);
        kardex1.setTotalPriceInventory(50000);

        Kardex kardex2 = new Kardex();
        kardex2.setId(new Long("456"));
        kardex2.setUnitPrice(25000);
        kardex2.setStock(105);
        kardex2.setStockInventory(1005);
        kardex2.setTotalPriceInventory(100000);
        List<Kardex> kardexs = new ArrayList<Kardex>();
        kardexs.add(kardex1);
        kardexs.add(kardex2);

        return kardexs;
    }

}
