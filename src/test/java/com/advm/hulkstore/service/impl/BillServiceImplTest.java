package com.advm.hulkstore.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.advm.hulkstore.dao.BillDAO;
import com.advm.hulkstore.service.BillService;

@RunWith(SpringRunner.class)
public class BillServiceImplTest {

	@MockBean
	private BillDAO billDAO;

	@Autowired
	private BillService billService;

	@TestConfiguration
	static class BillServiceImplTestContextConfiguration {

		@Bean
		public BillService billService() {
			return new BillServiceImpl();
		}
	}

	@Test
	public void testDao() {
		Assert.assertNotNull(billService.Dao());
	}

}
