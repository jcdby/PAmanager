/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import Ebeans.UserData;
import Ebeans.UserDataFacade;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author AJOU
 */
@Stateful
public class UserAuthenticationManager {

    @EJB
    UserDataFacade userDataFacade;
  
    UserData userData;
    
    /* userLogin
     * Find ID in database and compare password
     * return true or false
     */
    public String userLogin(String id, String pwd) {
        try {
            userData = new UserData();
            userData =  userDataFacade.find(id);
            if(!checkUserAlreadyExists(id)) {
                return "null " + id + " " + pwd;
            }
            System.out.println(id +" " + pwd);
            System.out.println(userData.getId() + " " + userData.getPwd());
            
            return userData.getPwd().equals(pwd) + "";
        } finally {
        }
    }
    
    /* userRegister
     * Create user data in database
     */
    public String userRegister(String id, String name, String pwd) {
        try {
            if(checkUserAlreadyExists(id))
                return "user already exists!";
            
            userData = new UserData(id, name, pwd);
            userDataFacade.create(userData);
            return "create done!";
        } finally {
        }        
    }
    
    /* userUnregister
     * Remove user data from database
     */
    public boolean userUnregister(String id) {
        try {
            userData = new UserData();
            userData = userDataFacade.find(id);
            userDataFacade.remove(userData);
            return true;
        } finally {
        }
    }
    
    private boolean checkUserAlreadyExists(String id) {
        return userDataFacade.find(id)!=null;
    }
}
