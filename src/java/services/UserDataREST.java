/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import Ebeans.UserData;
import beans.ClientFacade;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author AJOU
 */
@Path("")
public class UserDataREST {
    
    @EJB
    ClientFacade clientFacade;
    
    // Login: with form and text
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean loginTest(@FormParam("id") String id, @FormParam("pwd") String pwd) {
        System.out.println("asdf");
        UserData userData = new UserData(id, pwd, "");
        return clientFacade.userLogin(userData);
    }
    
    // Login with json
    @Path("/login")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean loginFromJSON(UserData userData) {
        System.out.println("register from json called");
        return clientFacade.userLogin(userData);
    }
    
    // Register with form and text
    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean registerTest(@FormParam("id") String id, @FormParam("name") String name, @FormParam("pwd") String pwd) {
        UserData userData = new UserData(id, pwd, name);
        return clientFacade.userRegister(userData);
    }
    
    // Register with json
    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean registerFromJSON(UserData userData) {
        System.out.println("register from json called");
        return clientFacade.userRegister(userData);
    }
    
    // Unregister with form and text
    @Path("/unregister")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean unRegisterTest(@FormParam("id") String id, @FormParam("pwd") String pwd) {
        UserData userData = new UserData(id, pwd, "");
        return clientFacade.userUnregister(userData);
    }
    
    // Unregister with json
    @Path("/unregister")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean unRegisterFromJSON(UserData userData) {
        System.out.println("register from json called");
        return clientFacade.userUnregister(userData);
    }
}
