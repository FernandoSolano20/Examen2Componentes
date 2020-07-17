package com.cenfotec.pomponio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cenfotec.pomponio.domain.Actor;

public interface ActorRepository extends JpaRepository<Actor,Long>{
	List<Actor> findAllByNombreContaining(String nombre);
	List<Actor> findAllByGenero(String genero);
}
