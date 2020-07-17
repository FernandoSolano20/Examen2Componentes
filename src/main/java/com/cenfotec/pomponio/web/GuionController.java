package com.cenfotec.pomponio.web;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cenfotec.pomponio.domain.Actor;
import com.cenfotec.pomponio.domain.Guion;
import com.cenfotec.pomponio.domain.Guionista;
import com.cenfotec.pomponio.service.ActorService;
import com.cenfotec.pomponio.service.GuionService;
import com.cenfotec.pomponio.service.GuionistaService;

@Controller
public class GuionController {
	@Autowired
	GuionService guionService;
	
	@Autowired
	GuionistaService guionistaService;
	
	@Autowired
	ActorService actorService;
	
	@RequestMapping(value = "guion", method = RequestMethod.GET)
	public String guionForm(Model model) throws ParseException 
	{
		model.addAttribute("guionistas", guionistaService.getAllGuionistas());
		return "guionForm";
	}
	
	
	@PostMapping("guion")
	public String createGuion(Model model, @RequestParam MultiValueMap body) throws ParseException {
		String nombre = (String) body.getFirst("nombre");
		String genero = (String) body.getFirst("genero");
		String ideaCentral = (String) body.getFirst("ideaCentral");
		Long idGuionista = Long.parseLong((String) body.getFirst("idGuionista"));
		Guionista guionista = new Guionista(idGuionista);
		Guion newEntry = new Guion(nombre,genero,ideaCentral,guionista);   
		guionService.saveGuion(newEntry);
		model.addAttribute("guionistas", guionistaService.getAllGuionistas());
		return "guionForm";
	}
	
	@RequestMapping(value = "guion/list", method = RequestMethod.GET)
	public String guionList(Model model) throws ParseException 
	{
		model.addAttribute("guion", guionService.getAllGuiones());
		return "guionList";
	}
	
	@PostMapping("guion/updateEstado")
	public ResponseEntity updateGuion(Model model, @RequestParam MultiValueMap body) {
		Long id = Long.parseLong((String) body.getFirst("id"));
		int estado = Integer.parseInt((String) body.getFirst("estado"));
		Guion newEntry = new Guion(id,estado);   
		guionService.updateGuion(newEntry);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PostMapping("guion/list")
	public String searchGuion(Model model, @RequestParam MultiValueMap body) {
		String query = (String) body.getFirst("query");
		model.addAttribute("guion", guionService.searchGuion(query));
		return "guionList";
	}
	
	@RequestMapping(value = "guion/listProduction", method = RequestMethod.GET)
	public String guionListProduction(Model model) throws ParseException 
	{
		model.addAttribute("guion", guionService.getAllGuionesByEstado(1));
		return "guionProduction";
	}
	
	@RequestMapping (value = "guion/listProduction/{id}")
	 public String getGuionId(Model model, @PathVariable("id") String id) {
		 Long idEle = Long.parseLong(id);
		 Guion guion = guionService.getGuiones(idEle).get();
		 model.addAttribute("guion", guion);
		 model.addAttribute("hombres", actorService.getActoresByGenero("Hombre"));
		 model.addAttribute("mujeres", actorService.getActoresByGenero("Mujer"));
		 return "guionActor";
	 }
	
	@PostMapping("guion/listProduction")
	public String addGuionActor(Model model, @RequestParam MultiValueMap body) {
		Long idGuion = Long.parseLong((String) body.getFirst("idGuion"));
		Long idActor = Long.parseLong((String) body.getFirst("idActor"));
		Long idActriz = Long.parseLong((String) body.getFirst("idActriz"));
		guionService.updateActorGuion(idGuion,idActor,idActriz);
		model.addAttribute("guion", guionService.getAllGuionesByEstado(1));
		return "guionProduction";
	}
}
