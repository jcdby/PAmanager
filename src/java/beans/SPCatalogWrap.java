/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ebeans.ServiceForPA;
import Ebeans.ServiceProviderForPA;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ethan
 */

//Datatype for extrange between EJB server and client such as android client
public class SPCatalogWrap implements  Serializable{
    
    private List<ServiceProviderForPA> sps;
    private List<ServiceForPA> services;

    public SPCatalogWrap() {
        sps = new ArrayList<ServiceProviderForPA>();
        services = new ArrayList<ServiceForPA>();
    }

    public SPCatalogWrap(List<ServiceProviderForPA> sps) {
        this.sps = sps;
    }

    public List<ServiceProviderForPA> getSps() {
        return sps;
    }

    public void setSps(List<ServiceProviderForPA> sps) {
        this.sps = sps;
        
        
    }

    public List<ServiceForPA> getServices() {
        return services;
    }

    public void setServices(List<ServiceForPA> services) {
        this.services = services;
    }
    
    
    
    
    
            
    
}
