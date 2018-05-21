/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import clases.Agenda;
import clases.Persona;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
//---------------ESTO NOSE USA; ES LEGACY-------------------------------------
/**
 *
 * @author Portatil
 */
public class ImportarExportar {
    
    File agenda;

    public ImportarExportar(String nombre) {
        agenda = new File(nombre);
    }
    
    public File guardarAgenda(Agenda agenda){
        try {
            JAXBContext context = JAXBContext.newInstance(Agenda.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(agenda, this.agenda);
            return this.agenda;
        } catch (JAXBException ex) {
            Logger.getLogger(ImportarExportar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void exportarPersona(Persona persona,String nombreArchivo){
        try {
            JAXBContext context = JAXBContext.newInstance(Persona.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File XMLfile = new File(nombreArchivo+".xml");
            marshaller.marshal(persona, XMLfile);
        } catch (JAXBException ex) {
            Logger.getLogger(ImportarExportar.class.getName()).log(Level.SEVERE, null, ex);
        }catch(NullPointerException ex){}
    }
    
    public File guardarPersona(Persona persona){
        try {
            JAXBContext context = JAXBContext.newInstance(Persona.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File f = new File(persona.getNombre()+".xml");
            marshaller.marshal(persona, f);
            return f;
        } catch (JAXBException ex) {
            Logger.getLogger(ImportarExportar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public Persona importarPersona(File persona){
        try {
            System.out.println("Cargando persona ...");
            JAXBContext jaxbC = JAXBContext.newInstance(Persona.class);
            Unmarshaller jaxbU = jaxbC.createUnmarshaller();
            Persona p = (Persona) jaxbU.unmarshal(persona);
            return p;
        } catch (JAXBException ex) {
            Logger.getLogger(ImportarExportar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Agenda cargar(){
        try {
            System.out.println("Cargando agenda ...");
            JAXBContext jaxbC = JAXBContext.newInstance(Agenda.class);
            Unmarshaller jaxbU = jaxbC.createUnmarshaller();
            Agenda a = (Agenda) jaxbU.unmarshal(agenda);
            return a;
        } catch (JAXBException ex) {
            //Logger.getLogger(ImportarExportar.class.getName()).log(Level.SEVERE, null, ex);
            return new Agenda();
        }
        //return null;
        //return null;
    }
    
    public Agenda comprobarSiExisteAgenda(){
        if(agenda.exists() && !agenda.isDirectory()){
            return cargar();
        }
        return null;
    }
    
}
