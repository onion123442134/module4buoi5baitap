package com.example.baitap2.repository;

import com.example.baitap2.model.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class CommentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void save(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.save(comment);
    }

    public List<Comment> getTodayComments() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery(
                        "from Comment where date = :today order by id desc", Comment.class)
                .setParameter("today", LocalDate.now())
                .list();
    }

    public void like(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Comment c = session.get(Comment.class, id);
        c.setLikes(c.getLikes() + 1);
        session.update(c);
    }
}
