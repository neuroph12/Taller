package com.tikal.tallerWeb.modelo.entity;


import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import technology.tikal.taller.automotriz.model.servicio.costo.RegistroCosto;

@Entity
public class PresupuestoEntity extends RegistroCosto {
	
	@Id public Long idPresupuesto;
	@Index private int indice;
	@Index private boolean facturado;
	private boolean done;
	
	public PresupuestoEntity(){
		this.setPrecioUnitarioConIVA(true);
		this.setAutorizado(false);
	}

	public Long getIdPresupuesto() {
		return idPresupuesto;
	}

	public int getIndice() {
		return indice;
	}

	public void setIndice(int indice) {
		this.indice = indice;
	}

	public boolean isFacturado() {			
		return facturado;
	}

	public void setFacturado(boolean facturado) {
		this.facturado = facturado;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
