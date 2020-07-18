package com.cenfotec.pomponio.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient; 

@Entity 
@Table(name = "TGuionista") 
public class Guionista {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="email")
	private String email;
	
	@Column(name="nacimiento")
	private Date nacimiento;
	
	@Transient
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	
	Guionista(){
		
	}
	
	public Guionista(Long id){
		this.id = id;
	}

	public Guionista(String nombre, String direccion, String email, String nacimiento) throws ParseException {
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.nacimiento = format.parse(nacimiento);
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
		
	public String getCreatedAsShort() {
		return format.format(nacimiento);
	}
}
