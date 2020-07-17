package com.cenfotec.pomponio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cenfotec.pomponio.domain.Actor;
import com.cenfotec.pomponio.domain.Guion;
import com.cenfotec.pomponio.repository.ActorRepository;
import com.cenfotec.pomponio.repository.GuionRepository;

@Service
public class GuionServiceImpl implements GuionService{
	@Autowired  
	GuionRepository guionRepo; 
	
	@Autowired  
	ActorRepository actorRepo; 
	
	@Override  
	public void saveGuion(Guion newGuion) 
	{   
		guionRepo.save(newGuion);
	}
	
	@Override  
	public void updateGuion(Guion guion) 
	{   
		Optional<Guion> guionFromDb = guionRepo.findById(guion.getId());
		Guion guionGetter = guionFromDb.get();
		guionGetter.setEstado(guion.getEstado()); 
		guionRepo.save(guionGetter);
	}
	
	@Override  
	public List<Guion> searchGuion(String query) 
	{   
		return guionRepo.findAllByGeneroContainingOrNombreContaining(query, query);
	}
	 
	 @Override  
	 public List<Guion> getAllGuiones() 
	 { 
		 return guionRepo.findAll();  
	 }
	 
	 @Override
	 public List<Guion> getAllGuionesByEstado(int estado)
	 {
		 return guionRepo.findAllByEstado(estado);
	 }
	 
	 @Override
	 public Optional<Guion> getGuiones(Long id){
		 return guionRepo.findById(id);
	 }
	 
	 @Override
	 public void updateActorGuion(Long idGuion, Long idActor, Long idActriz)
	 {
		 Guion guion = guionRepo.findById(idGuion).get();
		 Actor actor = actorRepo.findById(idActor).get();
		 Actor actriz = actorRepo.findById(idActriz).get();
		 guion.setActores(actor);
		 guion.setActores(actriz);
		 guionRepo.save(guion);
	 }
}
