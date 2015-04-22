/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebeans;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Query;

/**
 *
 * @author Han
 */
@Stateless
public class ServiceManager {

    @EJB
    ServiceForPAFacade serviceFacade;

    @EJB
    ServiceTypeFacade serviceTypeFacade;
    

    public List<ServiceForPA> getAvailableServices() {
        return serviceFacade.findAll();
    }

    /**
     *
     * This method may be vulnerable, we need to check validation before running
     * this. 
     * 
     * Running steps:
     * 1. Find EJB by lookup
     * 2. Find method in the EJB using ServiceType object
     * 3. Execute method and return the result.
     *
     * @param serviceType
     * @param args
     * @return
     */
    public Object invokeService(ServiceType serviceType, Object... args) {
        java.lang.reflect.Method method;
        try {
            Context ctx = new InitialContext();
            Object bean = ctx.lookup(serviceType.getAddr());
            //Need to modify regex
            String[] argumentClassesString = serviceType.getArguments().split(",");
            Class[] argumentClasses = new Class[argumentClassesString.length];

            for (int i = 0; i < argumentClassesString.length; i++) {
                argumentClasses[i] = Class.forName(argumentClassesString[i]);
            }
            method = bean.getClass().getMethod(serviceType.getMethod(), argumentClasses);

            return method.invoke(bean, args);

        } catch (NamingException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(ServiceManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public Object invokeService(Long sid, Object... args){
        ServiceType type = serviceTypeFacade.find(sid);
        return this.invokeService(type, args);
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
