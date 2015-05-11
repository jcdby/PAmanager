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
import Ebeans.UserData;
import Ebeans.UserDataFacade;
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
    
    @EJB
    UserDataFacade userDataFacade;
    
    // For user authentication
    UserData userData;
    UserData userDataFound;
   
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
  
    /* userLogin
     * Find ID in database and compare password
     * return true or false
     */
    public boolean userLogin(UserData u) {
        
        
        try {
            userData = new UserData();
            userData.setId(u.getId());
            userData.setName(u.getName());
            userData.setPwd(u.getPwd());
            
            userDataFound = new UserData();
            userDataFound =  userDataFacade.find(u.getId());
            
            if(!userAlreadyExists(userData.getId())) {
                return false;
            }
            return userData.getPwd().equals(userDataFound.getPwd());
        } finally {
        }
    }
    
    /* userRegister
     * Create user data in database
     */
    public boolean userRegister(UserData u) {
        UserData userData;
        
        try {
            if(userAlreadyExists(u.getId()))
                return false;
            
            userData = new UserData();
            userData.setId(u.getId());
            userData.setName(u.getName());
            userData.setPwd(u.getPwd());
                                    
            userDataFacade.create(userData);
            
            System.out.println(userData.getId() + userData.getPwd() + userData.getName());
            return true;
        } finally {
        }        
    }
    
    /* userUnregister
     * Remove user data from database
     */
    public boolean userUnregister(UserData u) {
        UserData userData;
        UserData userDataFound;
        
        try {
            userData = new UserData();
            userData.setId(u.getId());
            userData.setName(u.getName());
            userData.setPwd(u.getPwd());
            
            userDataFound = new UserData();            
            userDataFound = userDataFacade.find(userData.getId());
            if(userAlreadyExists(userData.getId()) && userData.getPwd().equals(userDataFound.getPwd())) {
                userDataFacade.remove(userDataFound);
                return true;
            }
            return false;
        } finally {
        }
    }
    
    public boolean userAlreadyExists(String id) {
        return userDataFacade.find(id)!=null;
    }
}
