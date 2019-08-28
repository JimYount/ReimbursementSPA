package com.revature.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.beans.Genre;
import com.revature.data.GenreDAO;

@RestController
@RequestMapping(value="/genres")
public class GenreController {
	@Autowired
	private GenreDAO gd;
	
	@GetMapping
	public Set<Genre> getAll(){
		return gd.getGenres();
	}
	
	@GetMapping("{steve}")
	public Genre getGenre(@PathVariable("steve") Integer bob) {
		return gd.getGenre(bob);
	}
	
	@PostMapping
	public Genre addGenre(@RequestBody Genre g) {
		Integer i = gd.addGenre(g);
		return gd.getGenre(i);
	}

}
