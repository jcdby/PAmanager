/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebeans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Han
 */
@Stateless
public class ServiceTypeFacade extends AbstractFacade<ServiceType> {

    @PersistenceContext(unitName = "PAmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceTypeFacade() {
        super(ServiceType.class);
    }

    public Long findSidByName(String name) {
        for(ServiceType item: this.findAll()){
            if(item.getName().equals(name))
                return item.getId();
        }
        return null;
    }

}
