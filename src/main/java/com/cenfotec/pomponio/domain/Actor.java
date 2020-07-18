package com.cenfotec.pomponio.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity  
@Table(name = "TActor") 
public class Actor {
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)  
	private Long id;  
	 
	@Column(name="nombre")  
	private String nombre;
	
	@Column(name="genero")  
	private String genero;
	
	@Column(name="nacimiento")
	private Date nacimiento;
	
	@Column(name="estatura")
	private String estatura;
	
	@Column(name="complexion")
	private String complexion;
	
	@Column(name="ojos")
	private String ojos;
	
	@Column(name="pelo")
	private String pelo;
	
	@ManyToMany(mappedBy = "actores", fetch = FetchType.LAZY)
    private Set<Guion> guiones = new HashSet<>();
	
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public Actor() {
		
	}
	
	public Actor(Long id) {
		this.id = id;
	}

	public Actor(String nombre, String genero, String nacimiento, String estatura, String complexion, 
			String ojos, String pelo) throws ParseException 
	{
		this.nombre = nombre;
		this.genero = genero;
		this.nacimiento = format.parse(nacimiento);
		this.estatura = estatura;
		this.complexion = complexion;
		this.ojos = ojos;
		this.pelo = pelo;
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

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}

	public String getEstatura() {
		return estatura;
	}

	public void setEstatura(String estatura) {
		this.estatura = estatura;
	}

	public String getComplexion() {
		return complexion;
	}

	public void setComplexion(String complexion) {
		this.complexion = complexion;
	}

	public String getOjos() {
		return ojos;
	}

	public void setOjos(String ojos) {
		this.ojos = ojos;
	}

	public String getPelo() {
		return pelo;
	}

	public void setPelo(String pelo) {
		this.pelo = pelo;
	}
	
	public String getCreatedAsShort() {
		return format.format(nacimiento);
	}
	
	public int getAge() {
		LocalDate birthDate = 	this.nacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if ((birthDate != null)) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        } else {
            return 0;
        }
	}
}
