package ru.otus.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.db.hibernate.core.model.User;
import ru.otus.services.DbServiceUserCache;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private final DbServiceUserCache usersService;

    public UserController(DbServiceUserCache usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.addAttribute("users", usersService.getAllUsers());
        return "users";
    }


    @RequestMapping(value = "user", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("user", "command", new User());
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("mvc-dispatcher") User user,
                             ModelMap model) {
        usersService.saveUser(user);
        return "redirect:/";
    }
}
