/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ebeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AJOU
 */
@Entity
@Table(name = "USERDATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserData.findAll", query = "SELECT u FROM UserData u"),
    @NamedQuery(name = "UserData.findById", query = "SELECT u FROM UserData u WHERE u.id = :id"),
    @NamedQuery(name = "UserData.findByName", query = "SELECT u FROM UserData u WHERE u.name = :name"),
    @NamedQuery(name = "UserData.findByPwd", query = "SELECT u FROM UserData u WHERE u.pwd = :pwd")})
public class UserData implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ID")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NAME")
    private Character name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PWD")
    private Character pwd;

    public UserData() {
    }

    public UserData(String id) {
        this.id = id;
    }

    public UserData(String id, Character name, Character pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Character getName() {
        return name;
    }

    public void setName(Character name) {
        this.name = name;
    }

    public Character getPwd() {
        return pwd;
    }

    public void setPwd(Character pwd) {
        this.pwd = pwd;
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
        if (!(object instanceof UserData)) {
            return false;
        }
        UserData other = (UserData) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Ebeans.UserData[ id=" + id + " ]";
    }
    
}
