package com.example.baitap2.controller;

import com.example.baitap2.model.Comment;
import com.example.baitap2.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @Autowired
    private ICommentService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("comments", service.today());
        return "index";
    }

    @PostMapping("/comment")
    public String submit(@ModelAttribute Comment comment) {
        service.save(comment);
        return "redirect:/";
    }

    @GetMapping("/like/{id}")
    public String like(@PathVariable Long id) {
        service.like(id);
        return "redirect:/";
    }
}
