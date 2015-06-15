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
import javax.persistence.TypedQuery;

/**
 *
 * @author Ethan
 */
@Stateless
public class ServiceProviderForPAFacade extends AbstractFacade<ServiceProviderForPA> {

    @PersistenceContext(unitName = "PAmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<ServiceProviderForPA> findRange(int[] range) {
        return super.findRange(range); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ServiceProviderForPA> findAll() {
        return super.findAll(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int count() {
        return super.count(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void edit(ServiceProviderForPA entity) {
        super.edit(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o); //To change body of generated methods, choose Tools | Templates.
    }

    public ServiceProviderForPA findByName(String name) {
        for (ServiceProviderForPA sp : findAll()) {
            if(sp.getSpName().endsWith(name)){
                return sp;
            }
        }
        return null;
    }

    public Long findIdByName(String name) {
        return findByName(name).getId();
    }

    public Boolean isServiceProvider(String name) {
        if (findByName(name) != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ServiceProviderForPA find(Object id) {
        return super.find(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(ServiceProviderForPA entity) {
        super.remove(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void create(ServiceProviderForPA entity) {
        super.create(entity); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<String> getAllSpNames() {
        ArrayList<ServiceProviderForPA> sps = (ArrayList<ServiceProviderForPA>) findAll();
        ArrayList<String> names = new ArrayList<String>();
        for (ServiceProviderForPA p : sps) {
            names.add(p.getSpName());
        }

        return names;

    }

    public ServiceProviderForPAFacade() {
        super(ServiceProviderForPA.class);
    }

}
