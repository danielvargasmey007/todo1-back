package com.advm.hulkstore.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.advm.hulkstore.service.BillService;

@RunWith(SpringRunner.class)
@WebMvcTest(BillController.class)
public class BillControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BillService billService;

    @Test
    public void showSave() throws Exception {
        Long productId = new Long("123");
        mvc.perform(get("/bill/detail/{id}", productId)).andExpect(status().isOk()).andExpect(view().name("bill/detail"));
        verify(billService, times(1)).get(productId);
    }

}
