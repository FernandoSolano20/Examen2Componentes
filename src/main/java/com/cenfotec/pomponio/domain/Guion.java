package com.cenfotec.pomponio.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity  
@Table(name = "TGuion") 
@SecondaryTables({
    @SecondaryTable(name="TGuionista")
})
public class Guion {
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;  
	
	@Column(name="nombre")  
	private String nombre;
	
	@Column(name="genero")  
	private String genero;
	
	@Column(name="ideacentral")  
	private String ideaCentral;
	
	@Column(name="estado")  
	private int estado;
	
	@ManyToOne()
    @JoinColumn(name = "idguionista")
    private Guionista guionista;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "tguionactor",
            joinColumns = {
                    @JoinColumn(name = "idguion", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "idactor", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Actor> actores = new HashSet<>();

	public Guion() {
		
	}
	
	public Guion(Long id) {
		this.id = id;
	}
	
	public Guion(Long id, int estado) {
		this.id = id;
		this.estado = estado;
	}

	public Guion(String nombre, String genero, String ideaCentral, Guionista guionista) {
		this.nombre = nombre;
		this.genero = genero;
		this.ideaCentral = ideaCentral;
		this.guionista = guionista;
		this.estado = 0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdeaCentral() {
		return ideaCentral;
	}

	public void setIdeaCentral(String ideaCentral) {
		this.ideaCentral = ideaCentral;
	}

	public Guionista getGuionista() {
		return guionista;
	}

	public void setGuionista(Guionista guionista) {
		this.guionista = guionista;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Set<Actor> getActores() {
		return actores;
	}

	public void setActores(Actor actor) {
		this.actores.add(actor);
	}
}
