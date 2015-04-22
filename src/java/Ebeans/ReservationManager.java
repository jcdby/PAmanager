/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebeans;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Han
 */
@Stateless
public class ReservationManager {

    @EJB
    ServiceTypeFacade serviceTypeFacade;
    @EJB
    ServiceForPAFacade serviceFacade;

    private static final String serviceName = "Reservation";

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public List<Reservation> getReservations(Long uid) {

        List<Reservation> resv = new ArrayList<>();

//        Long sid = serviceTypeFacade.findSidByName(serviceName);
//        if(sid==null)
//            return resv;
//        
//        //Fetch all reservation services
//        List<ServiceForPA> services = serviceFacade.findAllServices(sid);
//
//        //Retrieve all reservations from fetched providers
//        for (ServiceForPA item : services) {
//            String uri = String.format(item.getUri(), uid);
//            //Get reservations from designated uri
//            //Parse and add into arraylist
//            //** Not implemented yet.
//            Logger.getLogger(ReservationManager.class.getName()).log(Level.INFO, null, "Got URI: " + uri);
//            
//        }
        
        //Just for test
        for (int i = 0; i < 10; i++) {
            Reservation a = new Reservation();

            a.setId((long) i);
            a.setName("Name");
            a.setStarts(Calendar.getInstance());
            a.setEnds(Calendar.getInstance());
            resv.add(a);
        }

        return resv;
    }

}
