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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author AJOU
 */
@Path("/login")
public class UserLogin {
    
    @EJB
    UserAuthenticationManager manager;
    
    @GET
    @Produces("text/html")
    public String loginServiceGetTest() {
        return "<h3>test successful</h3>";
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String loginServicePostTest(@FormParam("id") String id, @FormParam("pwd") String pwd) {
        
        return "<h3>test id=" + id + " pwd=" + pwd + ": " + manager.userLogin(id, pwd) + " " + "</h3>";
    }
}
