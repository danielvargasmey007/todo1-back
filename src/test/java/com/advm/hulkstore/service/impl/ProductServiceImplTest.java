package com.advm.hulkstore.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.advm.hulkstore.dao.ProductDAO;
import com.advm.hulkstore.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

	@MockBean
	private ProductDAO productDAO;

	@Autowired
	private ProductService productService;

	@TestConfiguration
	static class ProductServiceImplTestContextConfiguration {

		@Bean
		public ProductService productService() {
			return new ProductServiceImpl();
		}
	}

	@Test
	public void testDao() {
		Assert.assertNotNull(productService.Dao());
	}

}
