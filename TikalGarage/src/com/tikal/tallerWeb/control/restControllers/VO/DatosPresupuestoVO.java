package com.tikal.tallerWeb.control.restControllers.VO;

import java.util.List;

public class DatosPresupuestoVO {
	
	
	//Datos del cliente
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private String asesor;
	
	//Datos del vehiculo
	private String marca;
	private String tipo;
	private String modelo;
	private String color;
	private String placas;
	private String kilometros;
	private String serie;
	private String servicio;
	private String nivelCombustible;
	private String observaciones;
	
	//Inventario de reparaciones
	private List<GruposCosto> listaServicios;
	
	//Lista de paths de imagenes
	
	private List<String> listaImages;
	
	//Atributo para imprimir costos
	private boolean conCosto;

	
	
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAsesor() {
		return asesor;
	}
	public void setAsesor(String asesor) {
		this.asesor = asesor;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getPlacas() {
		return placas;
	}
	public void setPlacas(String placas) {
		this.placas = placas;
	}
	public String getKilometros() {
		return kilometros;
	}
	public void setKilometros(String kilometros) {
		this.kilometros = kilometros;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getNivelCombustible() {
		return nivelCombustible;
	}
	public void setNivelCombustible(String nivelCombustible) {
		this.nivelCombustible = nivelCombustible;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public List<GruposCosto> getListaServicios() {
		return listaServicios;
	}
	public void setListaServicios(List<GruposCosto> listaServicios) {
		this.listaServicios = listaServicios;
	}
	public List<String> getListaImages() {
		return listaImages;
	}
	public void setListaImages(List<String> listaImages) {
		this.listaImages = listaImages;
	}
	public boolean isConCosto() {
		return conCosto;
	}
	public void setConCosto(boolean conCosto) {
		this.conCosto = conCosto;
	}
	
	
	
	
	
	
	
	


	
	
	

}
