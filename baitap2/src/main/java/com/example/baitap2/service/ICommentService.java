package com.example.baitap2.service;

import com.example.baitap2.model.Comment;

import java.util.List;

public interface ICommentService {
    void save(Comment c);

    List<Comment> today();

    void like(Long id);
}
