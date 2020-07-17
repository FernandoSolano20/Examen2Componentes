package com.cenfotec.pomponio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cenfotec.pomponio.repository.GuionistaRepository;
import com.cenfotec.pomponio.domain.Guionista;

@Service
public class GuionistaServiceImpl implements GuionistaService {
	@Autowired  
	GuionistaRepository guionistaRepo;   
	
	@Override  
	public void saveGuionista(Guionista newJournal) 
	{   
		guionistaRepo.save(newJournal);  
	} 
	 
	 @Override  
	 public List<Guionista> getAllGuionistas() 
	 {   
		 return guionistaRepo.findAll();  
	 }
}
