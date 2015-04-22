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

    public List<ServiceForPA> findAllServices() {
        return this.findAll();
    }

    public List<ServiceForPA> findAllServices(Long sid) {
        //QUERY is handcoded(Bad choice).
        List<ServiceForPA> list = new ArrayList<ServiceForPA>();
        for (ServiceForPA item: this.findAllServices()) {
            if(item.getSid().equals(sid)){
                list.add(item);
            }
        }
        return list;
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

}
