package com.iprzd.zshop.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/user/index")
    //@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ModelAndView userIndex(HttpServletRequest req, Exception e) {
        ModelAndView mv = new ModelAndView("user/index");
        mv.addObject("e", e);
        mv.addObject("uri", req.getRequestURI());
        return mv;
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
