/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Ebeans.ReservationFacade;
import templates.Reservation;
import Ebeans.ServiceForPA;
import Ebeans.ServiceForPAFacade;
import Ebeans.ServiceProviderForPA;
import Ebeans.ServiceProviderForPAFacade;
import beans.ClientFacade;
import beans.SPCatalogWrap;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;
import Ebeans.ReservationService;
import templates.ReservationList;
import templates.ServiceProviderRegistrationForm;
import templates.Service;
import templates.Result;

/**
 *
 * @author Ethan
 */
@Path("")
@Stateless
//this is the most important RESTful stateless bean. there are GET,POST method for consumming or producing the json message.
public class PamanagerWS {
       
    @EJB
    ClientFacade clientFacade;
    
    @EJB
    ReservationFacade reservationFacade;
    
    @EJB 
    ServiceForPAFacade serviceFacade;
    
    @EJB
    ServiceProviderForPAFacade sProviderFacade;
    
    @GET
    @Path("services")
    @Produces(MediaType.APPLICATION_JSON)
    //GET method show its services and its service providers.
    public SPCatalogWrap  getname()
    {
        return clientFacade.produceSPcataWrap();
    }
    
    @GET
    @Path("reservations/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    //GET method that request the user reservation.
    public ReservationList getReservations(@PathParam("uid") String uid){
        ReservationList reservations = new ReservationList();
        reservations.setReservations(clientFacade.getReservations(uid));
        return reservations;
    }
    
    
    @POST
    @Path("Service/Register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //Registraton entry point for service provider registration.
    public Result RegisterSP(String form)
    {
        ObjectMapper mapper = new ObjectMapper();

        Result result = new Result();
        
        try {
            ServiceProviderRegistrationForm sprform = mapper.readValue(form, ServiceProviderRegistrationForm.class);
            ServiceProviderForPA sp = new ServiceProviderForPA();
            
            sp.setSpName(sprform.getSp());            
            Long ownerId = sProviderFacade.findIdByName(sprform.getSp());
            if(ownerId != null){
                result.setResult("Failed");
                return result;
            }
            sProviderFacade.create(sp);
            ownerId = sProviderFacade.findIdByName(sprform.getSp());
            
            //set service informatin
            for(Service service:sprform.getServices()){
                ServiceForPA s = new ServiceForPA();
                s.setName(service.getName());
                s.setOwnerid(ownerId);
                s.setRedirectionAddress(service.getUrl());
                
                serviceFacade.create(s);
            }
            //set reservation address and other information.
            for(templates.ReservationService rService:sprform.getReservationServices()){
                ReservationService rs = new ReservationService();
                
                rs.setProviderId(ownerId);
                rs.setName(rService.getName());
                rs.setUrl(rService.getUrl());
                                
                reservationFacade.create(rs);
            }
            
            result.setResult("OK");
            
        } catch (IOException ex) {
            Logger.getLogger(PamanagerWS.class.getName()).log(Level.SEVERE, null, ex);
            result.setResult("Failed");
            
        }
       
        
        
        //Register the Service Provider and its services.
//         clientFacade.setServiceProvider(SPname, servicename, redirectionaddress);
         
        return result;
    }
    
    
}
