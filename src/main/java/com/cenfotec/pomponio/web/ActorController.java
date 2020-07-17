package com.cenfotec.pomponio.web;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cenfotec.pomponio.domain.Actor;
import com.cenfotec.pomponio.service.ActorService;


@Controller
public class ActorController {
	@Autowired
	ActorService actorService;
	
	@RequestMapping(value = "actor", method = RequestMethod.GET)
	public String actorForm(Model model) throws ParseException 
	{
		return "actorForm";
	}
	
	
	@PostMapping("actor")
	public String createActor(Model model, @RequestParam MultiValueMap body) throws ParseException {
		String nombre = (String) body.getFirst("nombre");
		String genero = (String) body.getFirst("genero");
		String nacimiento = (String) body.getFirst("nacimiento");
		String estatura = (String) body.getFirst("estatura");
		String complexion = (String) body.getFirst("complexion");
		String ojos = (String) body.getFirst("ojos");
		String pelo = (String) body.getFirst("pelo");
		Actor newEntry = new Actor(nombre,genero,nacimiento,estatura,complexion,ojos,pelo);   
		actorService.saveActor(newEntry);
		return "actorForm";
	}
	
	@RequestMapping(value = "actor/list", method = RequestMethod.GET)
	public String guionList(Model model) throws ParseException 
	{
		model.addAttribute("actor", actorService.getAllActores());
		return "actorList";
	}
	
	@PostMapping("actor/list")
	public String searchGuion(Model model, @RequestParam MultiValueMap body) {
		if(body.getFirst("nombre") != null) {
			String query = (String) body.getFirst("nombre");
			model.addAttribute("actor", actorService.searchActoresByName(query));
		}
		else if(body.getFirst("maxEdad") != null && body.getFirst("minEdad") != null) {
			int max = Integer.parseInt((String) body.getFirst("maxEdad"));
			int min = Integer.parseInt((String) body.getFirst("minEdad"));
			model.addAttribute("actor", actorService.searchActoresByAge(max,min));
		}
		
		return "actorList";
	}
}
