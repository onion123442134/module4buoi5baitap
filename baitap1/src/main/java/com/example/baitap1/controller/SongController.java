package com.example.baitap1.controller;

import com.example.baitap1.model.Song;
import com.example.baitap1.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private ISongService songService;

    private final String uploadDir = "D:/CodeGym/Module4/buoi5/baitap/baitap1/src/main/webapp/music/";

    @GetMapping
    public String listSongs(Model model) {
        model.addAttribute("songs", songService.findAll());
        return "list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("song", new Song());
        return "create";
    }

    @PostMapping("/create")
    public String createSong(@ModelAttribute Song song,
                             @RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            File folder = new File(uploadDir);
            if (!folder.exists()) folder.mkdirs();

            String filename = file.getOriginalFilename();
            File destination = new File(uploadDir + filename);
            file.transferTo(destination);

            song.setFilePath("/music/" + filename);
        }

        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Song song = songService.findById(id);
        if (song == null) {
            return "redirect:/songs";
        }
        model.addAttribute("song", song);
        return "edit";
    }

    @PostMapping("/edit")
    public String editSong(@ModelAttribute Song song,
                           @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {

        if (file != null && !file.isEmpty()) {
            File folder = new File(uploadDir);
            if (!folder.exists()) folder.mkdirs();

            String filename = file.getOriginalFilename();
            File destination = new File(uploadDir + filename);
            file.transferTo(destination);

            song.setFilePath("/music/" + filename);
        }

        songService.save(song);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable int id) {
        songService.remove(id);
        return "redirect:/songs";
    }

    @GetMapping("/play/{id}")
    public String playSong(@PathVariable int id, Model model) {
        Song song = songService.findById(id);
        if (song == null) {
            return "redirect:/songs";
        }
        model.addAttribute("song", song);
        return "play";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "Controller hoạt động!";
    }
}
