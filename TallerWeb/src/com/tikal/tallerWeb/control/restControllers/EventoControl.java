package com.tikal.tallerWeb.control.restControllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tikal.tallerWeb.control.restControllers.VO.BitacoraVO;
import com.tikal.tallerWeb.control.restControllers.VO.EventoVO;
import com.tikal.tallerWeb.control.restControllers.VO.EvidenciaVO;
import com.tikal.tallerWeb.data.access.BitacoraDAO;
import com.tikal.tallerWeb.data.access.ServicioDAO;
import com.tikal.tallerWeb.modelo.entity.EventoEntity;
import com.tikal.tallerWeb.modelo.entity.ServicioEntity;
import com.tikal.tallerWeb.util.AsignadorDeCharset;
import com.tikal.tallerWeb.util.EstatusMap;
import com.tikal.tallerWeb.util.JsonConvertidor;

import technology.tikal.taller.automotriz.model.servicio.bitacora.Evidencia;

@Controller
@RequestMapping(value = { "/eventos" })
public class EventoControl {

	@Autowired
	BitacoraDAO bitacora;
	
	@Autowired
	ServicioDAO servdao;

	@RequestMapping(value = {
			"/add" }, method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void add(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		AsignadorDeCharset.asignar(request, response);
		EventoVO vo = (EventoVO) JsonConvertidor.fromJson(json, EventoVO.class);
		EventoEntity e = vo.getEvento();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		try {
			e.setFecha(df.parse(vo.getFecha()));
			e.setFechaCreacion(new Date());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String tipo = vo.getEvento().getTipo();
		
		tipo=EstatusMap.getStatus(tipo);
		
		if(tipo!=null){
		/*	ServicioEntity ser= servdao.cargar(e.getId());
			if(tipo.compareTo("Diagnóstico")==0){
				ser.setAsesor(vo.getEvento().getResponsable());
			}
			if(tipo.compareTo("Terminado")==0){
				ser.setFechafin(new Date());
			}
			ser.getMetadata().setStatus(tipo);
			servdao.guardar(ser);*/
		}
		response.getWriter().println(JsonConvertidor.toJson(bitacora.agregar(e.getId(), e)));
	}
	
	@RequestMapping(value = { "/appendImages/{id}" }, method = RequestMethod.POST, produces = "application/json")
	public void addImages(HttpServletRequest request, HttpServletResponse response, @PathVariable String id,@RequestBody String json)
			throws IOException {
		EvidenciaVO evi= (EvidenciaVO) JsonConvertidor.fromJson(json, EvidenciaVO.class);
		AsignadorDeCharset.asignar(request, response);
		
		EventoEntity evento=bitacora.cargarEvento(evi.getIdEvento());
		List<Evidencia> lista= new ArrayList<Evidencia>();
		for(String image:evi.getImages()){
			Evidencia e= new Evidencia();
			e.setImage(image);
			lista.add(e);
		}
		evento.getEvidencia().addAll(lista);
		response.getWriter().println(JsonConvertidor.toJson(bitacora.guardar(evento)));
	}
	@RequestMapping(value = { "/getBitacora/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public void get(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
			throws IOException {
		AsignadorDeCharset.asignar(request, response);
		response.getWriter().println(JsonConvertidor.toJson(bitacora.cargar(Long.parseLong(id))));
	}

	@RequestMapping(value = { "/remove/{id}" }, method = RequestMethod.POST)
	public void remove(HttpServletRequest request, HttpServletResponse response, @PathVariable String id)
			throws IOException {
		AsignadorDeCharset.asignar(request, response);
		EventoEntity e = bitacora.cargarEvento(Long.parseLong(id));
		if (e != null) {
			bitacora.borrarEvento(e.getIdEvento());
		}
	}
	
	@RequestMapping(value = { "/update/" }, method = RequestMethod.POST, consumes="application/json")
	public void update(HttpServletRequest request, HttpServletResponse response, @RequestBody String json)
			throws IOException {
		BitacoraVO bit= (BitacoraVO) JsonConvertidor.fromJson(json, BitacoraVO.class);
		bitacora.guardar(Long.parseLong(bit.getId()), bit.getEventos());
		response.getWriter().println(JsonConvertidor.toJson(bit.getEventos()));
	}

}
