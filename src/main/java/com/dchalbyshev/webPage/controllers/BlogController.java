package com.dchalbyshev.webPage.controllers;

import com.dchalbyshev.webPage.models.Post;
import com.dchalbyshev.webPage.repo.Postrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    private Postrepository postrepository;

    @GetMapping("/towPage")  // прописываем имя бина который получает информацию о переходе пользователя
    public String towPage(Model model) { //если ("/") то при вызове метода
        // открывается главная страница
        // и вызывается метод  home
       Iterable<Post> posts =  postrepository.findAll();
       model.addAttribute("posts",posts);

        return "tow-Page";             // возвращаем html  шаблон  home - но лучше создать xml шаблон второйэ
        // страницы
    }

    @GetMapping("/towPage/add")  // прописываем имя бина
    public String towPageAdd(Model model) { //если ("/") то при вызове метода
                                           // открывается главная страница

                return "blog-add";             // возвращаем html  шаблон  blog-add
    }

    @PostMapping("/towPage/add")         // получаем данные из формы приипомощи меттода post
   public String blogpostAdd(@RequestParam String title,@RequestParam String anons, @RequestParam String fullText,Model model){
    Post post = new Post(title,anons,fullText);
    postrepository.save(post);
        return "redirect:/towPage";
    }
    @GetMapping("/towPage/{id}")  // прописываем имя бина//если ("/") то при вызове метода
                                        // открывается главная страница
    public String blogdetails(@PathVariable(value = "id") long id,  Model model) {
        if(!postrepository.existsById(id)){
          return "redirect:/towPage";

        }
    // @PathVariable(value = "id")   анатация о принятии динанмического параметра
        // long id указываем название параметра и тип его данных, название может быть любым
       // @GetMapping("/towPage/{id}/{name}") в анатации может бытьт указано несколлько дин параметров
        Optional<Post> post = postrepository.findById(id);
        // в репе находим записть по id
        // для работы в шабдлоне необходимо перевести ooptional to ArrayList
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post",res);
        return "blog-detail";             // возвращаем html  шаблон  blog-add
    }

    @GetMapping("/towPage/{id}/edit")  // прописываем имя бина//если ("/") то при вызове метода
    // открывается главная страница
    public String blogEdit(@PathVariable(value = "id") long id,  Model model) {
        if(!postrepository.existsById(id)){
            return "redirect:/towPage";

        }
        // @PathVariable(value = "id")   анатация о принятии динанмического параметра
        // long id указываем название параметра и тип его данных, название может быть любым
        // @GetMapping("/towPage/{id}/{name}") в анатации может бытьт указано несколлько дин параметров
        Optional<Post> post = postrepository.findById(id);
        // в репе находим записть по id
        // для работы в шабдлоне необходимо перевести ooptional to ArrayList
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res :: add);
        model.addAttribute("post",res);
        return "blog-edit";             // возвращаем html  шаблон  blog-add
    }
    @PostMapping("/towPage/{id}/edit")         // получаем данные из формы приипомощи меттода post
    public String blogpostUpdate(@PathVariable(value = "id") long id, @RequestParam String title,@RequestParam String anons, @RequestParam String fullText,Model model){
        Post post = postrepository.findById(id).orElseThrow(SecurityException::new);
        post.setTitle(title);
        post.setAnons(anons);
        post.setFullText(fullText);
        postrepository.save(post);
       // postrepository.save(post);
       return "redirect:/towPage"; // перенаправляем на страницу
    }

    @PostMapping("/towPage/{id}/remove")         // получаем данные из формы приипомощи меттода post
    public String blogpostDelete(@PathVariable(value = "id") long id,Model model){
        Post post = postrepository.findById(id).orElseThrow(SecurityException::new);

        postrepository.delete(post);
        // postrepository.save(post);
        return "redirect:/towPage"; // перенаправляем на страницу
    }




}

/*
 <form th:action="'/towPage/' + ${el.id} + '/remove'" method="Post">  // method="Post" данные отправляются на сервер
        <button class="btn btn-warning" type="submint" >Удалить</button>//  type="submint" для обновления
        </form>


 */