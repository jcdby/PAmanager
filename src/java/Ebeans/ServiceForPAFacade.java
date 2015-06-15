/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ethan
 */
@Stateless
public class ServiceForPAFacade extends AbstractFacade<ServiceForPA> {

    @PersistenceContext(unitName = "PAmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServiceForPAFacade() {
        super(ServiceForPA.class);
    }

    @Override
    public List<ServiceForPA> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ServiceForPA> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(ServiceForPA entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceForPA find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(ServiceForPA entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(ServiceForPA entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    public ServiceForPA findByName(String name) {
        for (ServiceForPA service : findAll()) {
            if (service.getName().endsWith(name)) {
                return service;
            }
        }
        return null;
    }

    public Long findIdByName(String name) {
        return findByName(name).getId();
    }

    public Boolean isService(String name, Long id) {
        ServiceForPA service = findByName(name);
        if (service != null) {
            if (service.getOwnerid().equals(id)) {
                return true;
            }
        
        }
          return false;
    }
}
