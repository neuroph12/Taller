package com.tikal.tallerWeb.control.restControllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tikal.tallerWeb.control.restControllers.VO.DatosPresupuestoVO;
import com.tikal.tallerWeb.control.restControllers.VO.DatosServicioVO;
import com.tikal.tallerWeb.control.restControllers.VO.GruposCosto;
import com.tikal.tallerWeb.data.access.AutoDAO;
import com.tikal.tallerWeb.data.access.BitacoraDAO;
import com.tikal.tallerWeb.data.access.ClienteDAO;
import com.tikal.tallerWeb.data.access.CostoDAO;
import com.tikal.tallerWeb.data.access.ServicioDAO;
import com.tikal.tallerWeb.modelo.entity.AutoEntity;
import com.tikal.tallerWeb.modelo.entity.ClienteEntity;
import com.tikal.tallerWeb.modelo.entity.EventoEntity;
import com.tikal.tallerWeb.modelo.entity.ServicioEntity;
import com.tikal.tallerWeb.rest.util.NewServiceObject;
import com.tikal.tallerWeb.util.AsignadorDeCharset;

import technology.tikal.taller.automotriz.model.servicio.bitacora.Evidencia;

@Controller
@RequestMapping(value= { "/reporte" })
public class ReportePresupuesto {
	
	@Autowired
	ServicioDAO servdao;		
	
	@Autowired
	AutoDAO autodao;
	
	@Autowired
	ClienteDAO clientedao;
	
	@Autowired
	CostoDAO costodao;
	
	@Autowired
	BitacoraDAO bitacorin;
	
	@RequestMapping(value={"/presupuestoPDF/{id}"}, method = RequestMethod.GET,produces="application/pdf")
	public ModelAndView generaReporte(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) throws UnsupportedEncodingException{
		AsignadorDeCharset.asignar(request, response);

		NewServiceObject servicio = new NewServiceObject();
		servicio.setServicio(servdao.cargar(Long.parseLong(id)));
		servicio.setAuto(autodao.cargar(Long.parseLong(servicio.getServicio().getIdAuto())));
		servicio.setCliente(clientedao.cargar(servicio.getServicio().getIdCliente()));
		List<GruposCosto> grupos = costodao.cargar(servicio.getServicio().getIdServicio());
		DatosServicioVO datosin = new DatosServicioVO();
		datosin.setServicio(servicio);
		datosin.setPresupuesto(grupos);
		
		NewServiceObject interfacin = datosin.getServicio();
		List<GruposCosto> presupuestin = datosin.getPresupuesto();
		AutoEntity auto = interfacin.getAuto();
		ClienteEntity cliente = interfacin.getCliente();
		ServicioEntity servicin = interfacin.getServicio();
		
		String domicilio = cliente.getDomicilio().getCalle() + "," +cliente.getDomicilio().getColonia() + "," +cliente.getDomicilio().getCiudad();
				
		DatosPresupuestoVO datos = new DatosPresupuestoVO();
		datos.setConCosto(true);
		datos.setNombre(cliente.getNombre());
		datos.setDireccion(domicilio);
		datos.setEmail(cliente.getEmail());
		datos.setTelefono(cliente.getTelefonoContacto().get(0).getValor());
		datos.setAsesor("S/D");
		datos.setMarca(auto.getMarca());
		datos.setTipo(auto.getTipo());
		datos.setModelo(auto.getModelo());
		datos.setColor(auto.getColor());
		datos.setPlacas(auto.getPlacas());
		datos.setKilometros(servicin.getDatosAuto().getKilometraje());
		datos.setSerie(auto.getNumeroSerie());
		datos.setServicio(servicin.getDescripcion());
		datos.setNivelCombustible(servicin.getDatosAuto().getCombustible());
		datos.setObservaciones("Sin Obervaciones");
		datos.setListaServicios(presupuestin);
	
		List<EventoEntity> eventin = bitacorin.cargar(Long.parseLong(id));
		List<String> pathImagenes= new ArrayList<String>();
		for(EventoEntity evento:eventin){
			for(Evidencia ev:evento.getEvidencia()){
				if(ev.isAppended(true)){
					pathImagenes.add(ev.getImage());
				}
			}
		}
		datos.setListaImages(pathImagenes);
		return new ModelAndView("PDFViewer","aquinoseporquevaesto", datos);
	}
	
	@RequestMapping(value={"/presupuestoPDFSin/{id}"}, method = RequestMethod.GET,produces="application/pdf")
	public ModelAndView generaReporteSinCosto(HttpServletRequest request, HttpServletResponse response, @PathVariable String id) throws UnsupportedEncodingException{

		AsignadorDeCharset.asignar(request, response);
		NewServiceObject servicio = new NewServiceObject();
		servicio.setServicio(servdao.cargar(Long.parseLong(id)));
		servicio.setAuto(autodao.cargar(Long.parseLong(servicio.getServicio().getIdAuto())));
		servicio.setCliente(clientedao.cargar(servicio.getServicio().getIdCliente()));
		List<GruposCosto> grupos = costodao.cargar(servicio.getServicio().getIdServicio());
		DatosServicioVO datosin = new DatosServicioVO();
		datosin.setServicio(servicio);
		datosin.setPresupuesto(grupos);
		
		NewServiceObject interfacin = datosin.getServicio();
		List<GruposCosto> presupuestin = datosin.getPresupuesto();
		AutoEntity auto = interfacin.getAuto();
		ClienteEntity cliente = interfacin.getCliente();
		ServicioEntity servicin = interfacin.getServicio();
		
		String domicilio = cliente.getDomicilio().getCalle() + "," +cliente.getDomicilio().getColonia() + "," +cliente.getDomicilio().getCiudad();
				
		DatosPresupuestoVO datos = new DatosPresupuestoVO();
		datos.setConCosto(false);
		datos.setNombre(cliente.getNombre());
		datos.setDireccion(domicilio);
		datos.setEmail(cliente.getEmail());
		datos.setTelefono(cliente.getTelefonoContacto().get(0).getValor());
		datos.setAsesor("S/D");
		datos.setMarca(auto.getMarca());
		datos.setTipo(auto.getTipo());
		datos.setModelo(auto.getModelo());
		datos.setColor(auto.getColor());
		datos.setPlacas(auto.getPlacas());
		datos.setKilometros(servicin.getDatosAuto().getKilometraje());
		datos.setSerie(auto.getNumeroSerie());
		datos.setServicio(servicin.getDescripcion());
		datos.setNivelCombustible(servicin.getDatosAuto().getCombustible());
		datos.setObservaciones("Sin Obervaciones");
		datos.setListaServicios(presupuestin);
	
		List<EventoEntity> eventin = bitacorin.cargar(Long.parseLong(id));
		List<String> pathImagenes= new ArrayList<String>();
		for(EventoEntity evento:eventin){
			for(Evidencia ev:evento.getEvidencia()){
				if(ev.isAppended(true)){
					pathImagenes.add(ev.getImage());
				}
			}
		}
		datos.setListaImages(pathImagenes);
		return new ModelAndView("PDFViewer","aquinoseporquevaesto", datos);
	}
	
	

}
