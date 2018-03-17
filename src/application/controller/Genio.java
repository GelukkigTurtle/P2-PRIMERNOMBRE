package application.controller;

public class Genio {
	
	private Integer idGenio;
	private String nombre;
	private Integer nacimiento;
	
	
	public Genio() {

	}
	
	public Genio(Integer idGenio, String nombre, Integer nacimiento) {
		super();
		this.idGenio = idGenio;
		this.nombre = nombre;
		this.nacimiento = nacimiento;
	}

	public Integer getIdGenio() {
		return idGenio;
	}

	public void setIdGenio(Integer idGenio) {
		this.idGenio = idGenio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Integer nacimiento) {
		this.nacimiento = nacimiento;
	}
	

}
