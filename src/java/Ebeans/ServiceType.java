/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebeans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  ServiceType represents one method in an EJB
 * @author Han
 */
@Entity
@XmlRootElement
public class ServiceType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sid")
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="lookupAddr")
    private String addr;
    
    @Column(name="method")
    private String method;
    
    //Arguments should be conform: "Arg1, Arg2, Arg3, ...). Each arguments represents type;
    @Column(name="arguments")
    private String arguments;

    public String getName() {
        return name;
    }
    
    public String getAddr(){
        return addr;
    }
    
    public void setAddr(String addr){
        this.addr = addr;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
        public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceType)) {
            return false;
        }
        ServiceType other = (ServiceType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ebeans.ServiceType[ id=" + id + " ]";
    }
    
}
