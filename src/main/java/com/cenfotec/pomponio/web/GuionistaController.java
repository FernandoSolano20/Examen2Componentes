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
import com.cenfotec.pomponio.domain.Guionista;
import com.cenfotec.pomponio.service.GuionistaService;

@Controller
public class GuionistaController {
	@Autowired
	GuionistaService guionistaService;
	
	@RequestMapping(value = "guionista", method = RequestMethod.GET)
	public String guionistaForm(Model model) throws ParseException 
	{
		return "guionistaForm";
	}
	
	
	@PostMapping("guionista")
	public String createGuionista(Model model, @RequestParam MultiValueMap body) throws ParseException {
		String nombre = (String) body.getFirst("nombre");
		String direccion = (String) body.getFirst("direccion");
		String email = (String) body.getFirst("email");
		String date = (String) body.getFirst("date");
		Guionista newEntry = new Guionista(nombre,direccion,email,date);   
		guionistaService.saveGuionista(newEntry);
		return "guionistaForm";
	}
}
