package com.advm.hulkstore.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ErrorControllerHandler implements ErrorController {
    @Override
    @RequestMapping("/error")
    @ResponseBody
    public String getErrorPath() {
        return "¡Oops! Ha ocurrido un error inesperado.";
    }

}