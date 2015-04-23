/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.userauthentication;

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
@Path("/register")
public class UserRegister {
    @EJB
    UserAuthenticationManager manager;
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String registerTest(@FormParam("id") String id, @FormParam("name") String name, @FormParam("pwd") String pwd) {
        String result = manager.userRegister(id, name, pwd);
        return "<h3>" + result + "</h3>";
    }
}
