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
    public boolean userLogin(String id, String pwd) {
        try {
            userData = new UserData();
            userData =  userDataFacade.find(id);
            if(!checkUserAlreadyExists(id)) {
                return false;
            }
            return userData.getPwd().equals(pwd);
        } finally {
        }
    }
    
    /* userRegister
     * Create user data in database
     */
    public boolean userRegister(String id, String name, String pwd) {
        try {
            if(checkUserAlreadyExists(id))
                return false;
            
            userData = new UserData(id, pwd, name);
            userDataFacade.create(userData);
            return true;
        } finally {
        }        
    }
    
    /* userUnregister
     * Remove user data from database
     */
    public boolean userUnregister(String id, String pwd) {
        try {
            userData = new UserData();
            userData = userDataFacade.find(id);
            if(checkUserAlreadyExists(id) && userData.getPwd().equals(pwd)) {
                userDataFacade.remove(userData);
                return true;
            }
            return false;
        } finally {
        }
    }
    
    private boolean checkUserAlreadyExists(String id) {
        return userDataFacade.find(id)!=null;
    }
}
