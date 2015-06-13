/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ebeans.Reservation;
import Ebeans.ReservationFacade;
import javax.ejb.EJB;
import javax.ejb.Stateless;


/**
 *
 * @author Ethan
 */
import Ebeans.ServiceForPAFacade;
import Ebeans.ServiceProviderForPAFacade;
import Ebeans.ServiceForPA;
import Ebeans.ServiceProviderForPA;
import java.util.ArrayList;
import java.util.List;


@Stateless
public class ClientFacade {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    ServiceForPAFacade servicesManager;
    
    @EJB
    ServiceProviderForPAFacade spManager;
    
    @EJB
    private ReservationFacade reservationFacade;
    
   
    //Produce Service Provider catalog wrap class to exchange between EJB server and Android client.
    public SPCatalogWrap produceSPcataWrap()
    {
         SPCatalogWrap temSpcata = new SPCatalogWrap();
         List<ServiceProviderForPA> tempSP = new ArrayList<ServiceProviderForPA>();
         List<ServiceForPA> tempServices = new ArrayList<ServiceForPA>();
         
         //Get all Service Providers from the database in EJB server.
         tempSP = getServiceProvider();
         tempServices = getServices();
         //Add them into catalog list.
         temSpcata.setSps(tempSP);
         temSpcata.setServices(tempServices);
        
         return temSpcata;
    }
   
    public List<ServiceForPA> getServices()
    {
        List<ServiceForPA> temSer  = new ArrayList<ServiceForPA>();
        temSer = servicesManager.findAll();
        return temSer;
    }
    
    public List<ServiceProviderForPA> getServiceProvider()
    {
        List<ServiceProviderForPA> splist = new ArrayList<ServiceProviderForPA>();
        splist  =  spManager.findAll();
        return splist;
    }
    
    public List<Reservation> getReservations(long uid)
    {
        return reservationFacade.getReservations(uid);
    }
    
    public void setServiceProvider(String SPname, String uri)
    {
        ServiceProviderForPA serProvider = new ServiceProviderForPA();// serProvider is an entity. Here is not sure if it will work well.let`s see.
        serProvider.setSpName(SPname);
        serProvider.setUri(uri);        
        spManager.create(serProvider);
    }
}
