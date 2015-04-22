/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.service;

import Ebeans.ServiceType;
import Ebeans.ServiceTypeFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Han
 */
@Stateless
@Path("/ServiceType")
public class ServiceTypeREST {
    @EJB
    ServiceTypeFacade facade;

    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(ServiceType entity) {
        facade.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Long id, ServiceType entity) {
        facade.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        facade.remove(facade.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public ServiceType find(@PathParam("id") Long id) {
        return facade.find(id);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<ServiceType> findAll() {
        return facade.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<ServiceType> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return facade.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(facade.count());
    }
    
}
