/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Ebeans.Reservation;
import beans.ClientFacade;
import beans.SPCatalogWrap;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
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
    
    
}
