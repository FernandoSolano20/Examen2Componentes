package com.cenfotec.pomponio.service;

import java.util.List;

import com.cenfotec.pomponio.domain.Actor;


public interface ActorService {
	public void saveActor(Actor newActor);
	public List<Actor> getAllActores();
	public List<Actor> searchActoresByName(String query);
	public List<Actor> searchActoresByAge(int max, int min);
	public List<Actor> getActoresByGenero(String genero);
}
