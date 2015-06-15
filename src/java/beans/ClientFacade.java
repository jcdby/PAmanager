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
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

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
    public SPCatalogWrap produceSPcataWrap() {
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

    public List<ServiceForPA> getServices() {
        List<ServiceForPA> temSer = new ArrayList<ServiceForPA>();
        temSer = servicesManager.findAll();
        return temSer;
    }

    public List<ServiceProviderForPA> getServiceProvider() {
        List<ServiceProviderForPA> splist = new ArrayList<ServiceProviderForPA>();
        splist = spManager.findAll();
        return splist;
    }

    public List<Reservation> getReservations(long uid) {
        return reservationFacade.getReservations(uid);
    }

    public void setServiceProvider(
            String SPname,
            String servicename,
            String redirectionaddress/*,
     String reservationuri*/) {

        String redirectionaddressReplace = redirectionaddress;
        redirectionaddressReplace = redirectionaddress.replaceAll("%3A", ":");
        redirectionaddressReplace = redirectionaddressReplace.replaceAll("%2F", "/");
        System.out.println(redirectionaddressReplace);
        
        
        if (spManager.isServiceProvider(SPname)) {//Service Provider exist
            if (servicesManager.isService(servicename, spManager.findIdByName(SPname)))//Service exist
            {
            } else {//Service doesn`t exit
                ServiceForPA service = new ServiceForPA(servicename, redirectionaddressReplace, spManager.findIdByName(SPname));
                servicesManager.create(service);
            }

        } else {//Service Provider doesn`t exist
            ServiceProviderForPA serProvider = new ServiceProviderForPA();// serProvider is an entity. Here is not sure if it will work well.let`s see.
            serProvider.setSpName(SPname);
            spManager.create(serProvider);
            ServiceForPA service = new ServiceForPA(servicename, redirectionaddressReplace, serProvider.getId());
            servicesManager.create(service);
        }

    }

    /*
     //According to the uri gotten from the Service Provider, get its services provided.
     public Services RetrieveServices(String uri) {

     Services services = null;

     HttpClient client = HttpClientBuilder.create().build();
     HttpGet get = new HttpGet(uri);

     get.setHeader("Accept", "application/json");

     HttpEntity entity;

     try {
     HttpResponse response = client.execute(get);

     if (response.getStatusLine().getStatusCode() != 200) {
     System.out.println("GET> Unexpected status code: " + response.getStatusLine().getStatusCode());
     return null;
     }

     entity = response.getEntity();

     String data = EntityUtils.toString(entity);

     services = JSONToServices(data);

     } catch (ClientProtocolException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     }
     return services;

     }

     /* Conversion methods between JSON and Person. */
    /*private Services JSONToServices(String json) {
     Gson gson = new Gson();
     Services s = gson.fromJson(json, Services.class);
     return s;
     }

     private String ServiceToJSON(ServiceForPA s) {
     Gson gson = new Gson();
     String json = gson.toJson(s);
     return json;
     }

     public void storeSevices(Services services) {
        
     List<ServiceForPA> localServices = services.getServices();
     for(int i = 0; i < localServices.size(); i++)
     {
     servicesManager.create(localServices.get(i));
     }

     }*/
}
