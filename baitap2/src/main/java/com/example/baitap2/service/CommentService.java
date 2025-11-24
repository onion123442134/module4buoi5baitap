package com.example.baitap2.service;

import com.example.baitap2.model.Comment;
import com.example.baitap2.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository repo;

    @Override
    public void save(Comment c) {
        repo.save(c);
    }

    @Override
    public List<Comment> today() {
        return repo.getTodayComments();
    }

    @Override
    public void like(Long id) {
        repo.like(id);
    }
}
