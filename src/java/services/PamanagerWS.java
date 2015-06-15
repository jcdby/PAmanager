/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Ebeans.Reservation;
import Ebeans.ServiceForPA;
import Ebeans.ServiceProviderForPA;
import beans.ClientFacade;
import beans.SPCatalogWrap;
import com.google.gson.Gson;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ethan
 */

@Path("")
@Stateless
public class PamanagerWS {
    
    
    @EJB
    ClientFacade clientFacade;
    
    
    @GET
    @Path("services")
    @Produces(MediaType.APPLICATION_JSON)
    public SPCatalogWrap  getname()
    {
        return clientFacade.produceSPcataWrap();
    }
    
    @GET
    @Path("reservations/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations(@PathParam("uid") long uid){
        return clientFacade.getReservations(uid);
    }
    
    
    @GET
    @Path("RegisterServiceProvider/{SPname}/{ServiceName}/{RedirectionAddress}")
    public String RegisterSP(
            @PathParam("SPname") String SPname, 
            @PathParam("ServiceName") String servicename,
            @PathParam("RedirectionAddress") String redirectionaddress/*,
            @PathParam("ReservationUri") String reservationuri*/)//SPname is the name of service provider, and the uri
            //is used to retrieve the services provided by the related service provider.
    {
        
        //Register the Service Provider and its services.
         clientFacade.setServiceProvider(SPname, servicename, redirectionaddress);
         
         return "OK";
    }
    
    
    
    
    
    
    
    
    
}
