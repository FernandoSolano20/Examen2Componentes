package com.cenfotec.pomponio.service;

import java.util.List;
import java.util.Optional;

import com.cenfotec.pomponio.domain.Guion;


public interface GuionService {
	public void saveGuion(Guion newGuion);
	public void updateGuion(Guion guion);
	public void updateActorGuion(Long idGuion, Long idActor, Long idActriz);
	public List<Guion> searchGuion(String query);
	public List<Guion> getAllGuiones();
	public List<Guion> getAllGuionesByEstado(int estado);
	public Optional<Guion> getGuiones(Long id);
}
