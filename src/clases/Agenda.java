/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author y9d1ru
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Agenda")
public class Agenda implements Serializable{
    @XmlElement(name="Persona")
    ArrayList<Persona> personas;
    
    public Agenda(ArrayList<Persona> personas) {
        this.personas = personas;
    }

    public Agenda() {
        personas=new ArrayList<>();        
    }
    
    public void anadirPersona(Persona persona){
        personas.add(persona);
    }   
    
    public ArrayList<Persona> getPersonas(){
        return personas;
    }
    
}
