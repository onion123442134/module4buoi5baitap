package com.example.baitap1.service;

import com.example.baitap1.model.Song;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HibernateSongService implements ISongService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Song> findAll() {
        TypedQuery<Song> query = entityManager.createQuery("SELECT s FROM Song s", Song.class);
        return query.getResultList();
    }

    @Override
    public Song findById(int id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    public void save(Song song) {
        if (song.getId() == 0) {
            entityManager.persist(song);
        } else {
            Song existing = findById(song.getId());
            if (existing != null) {
                existing.setTitle(song.getTitle());
                existing.setArtist(song.getArtist());
                existing.setGenre(song.getGenre());
                existing.setFilePath(song.getFilePath());
                entityManager.merge(existing);
            }
        }
    }

    @Override
    public void remove(int id) {
        Song song = findById(id);
        if (song != null) {
            entityManager.remove(song);
        }
    }
}
