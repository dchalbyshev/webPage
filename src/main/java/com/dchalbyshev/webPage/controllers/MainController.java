package com.dchalbyshev.webPage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
// обработка перходов на сайте
    @GetMapping("/")    //("/greeting") // указываем какой URL адрес обрабатываем
                       // ннапример https://www.dchalbysev.com/greeting

    public String home(Model model) { //если ("/") то при вызове метода
                                           // открывается главная страница
                                       // и вызывается метод  home
        model.addAttribute("title", "Главная страница");
        return "home";             // возвращаем html  шаблон  home
    }

    @GetMapping("/towPage")  // прописываем имя бина
    public String towPage(Model model) { //если ("/") то при вызове метода
        // открывается главная страница
        // и вызывается метод  home
        model.addAttribute("title", "Главная страница");
        return "home";             // возвращаем html  шаблон  home - но лучше создать xml шаблон второйэ
                                  // страницы
    }

    @GetMapping("/about")  // прописываем имя бина
    public String about(Model model) { //если ("/") то при вызове метода
        // открывается главная страница
        // и вызывается метод  home
        model.addAttribute("title", "Страница про нас");
        return "about";             // возвращаем html  шаблон  home - но лучше создать xml шаблон второйэ
        // страницы
    }


}