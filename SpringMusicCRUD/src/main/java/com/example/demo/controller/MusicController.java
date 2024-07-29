package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Music;
import com.example.demo.form.MusicForm;
import com.example.demo.service.MusicService;

@Controller
public class MusicController {
	@Autowired
	MusicService service;
	
	@GetMapping("top")
	public String indexView() {
		return "top";
	}
	
	@PostMapping(value="song",params="list")
	public String listView(Model model) {
		Iterable<Music> view = service.findAll();
		model.addAttribute("view",view);
		return "list";
	}
	@PostMapping(value="song",params="input")
	public String musicInputView() {
		return "input";
	}
	
	@PostMapping(value="song",params="updata")
	public String updataView(Model model) {
		Iterable<Music> view = service.findAll();
		model.addAttribute("view",view);
		return "update";
	}
	@PostMapping(value="song",params="delete")
	public String deleteInputView(Model model) {
		Iterable<Music> view = service.findAll();
		model.addAttribute("view",view);
		return "delete";
	}
	
	@PostMapping("entry")
	public String musicConfirmView(MusicForm m) {
		Music music = new Music(m.getSong_id(),m.getSong_name(),m.getSinger());
		service.insertMusic(music);
		return "input-complete";
	}
	
	@PostMapping("up")
	public String idselectView(@RequestParam("song_id") Integer song_id,Model model) {
		Music select = service.findById(song_id);
		MusicForm musicform = new MusicForm(select.getSong_id(),select.getSong_name(),select.getSinger());
		model.addAttribute("select",musicform);
		return "update-input";
	}
	
	@PostMapping("update")
	public String updateView(@ModelAttribute MusicForm musicform,Model model) {
		Music music = service.findById(musicform.getSong_id());
		music.setSong_name(musicform.getSong_name());
		music.setSinger(musicform.getSinger());
		service.updateMusic(music);
		return "update_complete";
	}
	
	@PostMapping("delete")
	public String deleteView(@RequestParam("delete") Integer song_id,Model model) {
	Music music = service.findById(song_id);
	service.deleteMusic(song_id);
	return "delete-complete";
	}
}