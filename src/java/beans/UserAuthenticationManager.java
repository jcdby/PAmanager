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
    UserData userDataFound;
    
    /* userLogin
     * Find ID in database and compare password
     * return true or false
     */
    public boolean userLogin(UserData u) {
        try {
            userData = new UserData();
            userData.setId(u.getId());
            userData.setName(u.getName());
            userData.setPwd(u.getPwd());
            
            userDataFound = new UserData();
            userDataFound =  userDataFacade.find(u.getId());
            
            if(!userAlreadyExists(userData.getId())) {
                return false;
            }
            return userData.getPwd().equals(userDataFound.getPwd());
        } finally {
        }
    }
    
    /* userRegister
     * Create user data in database
     */
    public boolean userRegister(UserData u) {
        try {
            if(userAlreadyExists(u.getId()))
                return false;
            
            userData = new UserData();
            userData.setId(u.getId());
            userData.setName(u.getName());
            userData.setPwd(u.getPwd());
                                    
            userDataFacade.create(userData);
            
            System.out.println(userData.getId() + userData.getPwd() + userData.getName());
            return true;
        } finally {
        }        
    }
    
    /* userUnregister
     * Remove user data from database
     */
    public boolean userUnregister(UserData u) {
        try {
            userData = new UserData();
            userData.setId(u.getId());
            userData.setName(u.getName());
            userData.setPwd(u.getPwd());
            
            userDataFound = new UserData();
            
            userDataFound = userDataFacade.find(userData.getId());
            if(userAlreadyExists(userData.getId()) && userData.getPwd().equals(userDataFound.getPwd())) {
                userDataFacade.remove(userDataFound);
                return true;
            }
            return false;
        } finally {
        }
    }
    
    private boolean userAlreadyExists(String id) {
        return userDataFacade.find(id)!=null;
    }
}
