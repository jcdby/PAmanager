/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Ebeans.Reservation;
import Ebeans.ReservationManager;
import beans.ClientFacade;
import beans.SPCatalogWrap;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Han
 */

@Path("/Reservation")
public class PamanagerReservation {
    
    
    @EJB
    ReservationManager reservationManager;
    
    
    @GET
    @Path("User/{id}")
    //@Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reservation> getReservations(@PathParam("id") Long uid)
    {
        return reservationManager.getReservations(uid);
    }
    
    
    
}
