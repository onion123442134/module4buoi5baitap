package com.example.baitap2.controller;

import com.example.baitap2.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LikeController {

    @Autowired
    private ICommentService service;

    @PostMapping("/like/{id}")
    public String like(@PathVariable("id") Long id) {
        service.like(id);
        return "redirect:/";
    }
}
