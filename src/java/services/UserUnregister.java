/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.userauthentication;

import Ebeans.UserData;
import beans.UserAuthenticationManager;
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
@Path("/unregister")
public class UserUnregister {
    @EJB
    UserAuthenticationManager manager;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean registerTest(@FormParam("id") String id, @FormParam("pwd") String pwd) {
        UserData userData = new UserData(id, pwd, "");
        return manager.userUnregister(userData);
    }
    
    //This is for android communication
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean registerFromJSON(UserData userData) {
        System.out.println("register from json called");
        return manager.userUnregister(userData);
    }
}
