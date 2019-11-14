package com.follansbee.portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {
    @RequestMapping({"", "/", "/index", "/index.html", "/index/", "/index.html/"})
    public String getIndexPage(Model model) {

        //model.addAttribute("recipes", recipeService.getRecipes());

        //messageService.sendMessages();
        return "index";
    }

    @RequestMapping({"/index2"})
    public String getIndex2Page(Model model) {

        //model.addAttribute("recipes", recipeService.getRecipes());

        //messageService.sendMessages();
        return "index2";
    }

}
