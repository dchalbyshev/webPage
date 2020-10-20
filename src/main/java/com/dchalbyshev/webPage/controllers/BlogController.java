package com.dchalbyshev.webPage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("/towPage")  // прописываем имя бина
    public String towPage(Model model) { //если ("/") то при вызове метода
        // открывается главная страница
        // и вызывается метод  home
        model.addAttribute("title", "Главная страница");
        return "tow-Page";             // возвращаем html  шаблон  home - но лучше создать xml шаблон второйэ
        // страницы
    }
}
