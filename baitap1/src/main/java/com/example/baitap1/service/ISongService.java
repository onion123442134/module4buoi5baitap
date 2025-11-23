package com.example.baitap1.service;

import com.example.baitap1.model.Song;

import java.util.List;

public interface ISongService {
    List<Song> findAll();

    Song findById(int id);

    void save(Song song);

    void remove(int id);
}
