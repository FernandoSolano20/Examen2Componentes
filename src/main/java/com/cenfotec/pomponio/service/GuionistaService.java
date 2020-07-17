package com.cenfotec.pomponio.service;

import java.util.List;
import com.cenfotec.pomponio.domain.Guionista;

public interface GuionistaService {
	public void saveGuionista(Guionista newGuonista);
	public List<Guionista> getAllGuionistas();
}
