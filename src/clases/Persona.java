/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author y9d1ru
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Persona")
public class Persona implements Serializable{
    @XmlElement
    String name;
    @XmlElement
    String email;
    @XmlElement
    String telephone;
    
    public Persona(){
    }  

    public Persona(String name, String telephone, String email) {
        this.name = name;
        this.telephone = telephone;
        this.email = email;
    }

    public String getNombre() {
        return name;
    }

    public void setNombre(String nombre) {
        this.name = telephone;
    }

    public String getTelefono() {
        return telephone;
    }

    public void setTelefono(String telefono) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
