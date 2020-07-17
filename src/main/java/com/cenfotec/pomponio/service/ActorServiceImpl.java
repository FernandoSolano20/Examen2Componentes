package com.cenfotec.pomponio.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.pomponio.domain.Actor;
import com.cenfotec.pomponio.repository.ActorRepository;

@Service
public class ActorServiceImpl implements ActorService {
	@Autowired  
	ActorRepository actorRepo;   
	
	@Override  
	public void saveActor(Actor newActor) 
	{   
		actorRepo.save(newActor);  
	}
	
	@Override  
	public List<Actor> searchActoresByName(String query) 
	{   
		return actorRepo.findAllByNombreContaining(query);
	}
	
	@Override  
	public List<Actor> searchActoresByAge(int max, int min) 
	{   
		List<Actor> actores = actorRepo.findAll();
		List<Actor> returnActores = new ArrayList<>();
		for (Actor actor : actores) {
			int age = actor.getAge();
		    if(age <= max && age >= min) {
		    	returnActores.add(actor);
		    }
		}
		return returnActores;
	}	
	 
	 @Override  
	 public List<Actor> getAllActores() 
	 {   
		 return actorRepo.findAll();  
	 }
	 
	 @Override
	 public List<Actor> getActoresByGenero(String genero)
	 {
		 return actorRepo.findAllByGenero(genero);
	 }
}
