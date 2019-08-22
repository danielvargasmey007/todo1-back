package com.advm.hulkstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.advm.hulkstore.model.Bill;
import com.advm.hulkstore.service.BillService;

@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("bill/detail/{id}")
    public String showSave(@PathVariable Long id, Model model) {
        Bill bill = billService.get(id);

        if (bill != null) {
            model.addAttribute("bill", bill);
        } else {
            model.addAttribute("bill", null);
        }

        return "bill/detail";
    }
}
