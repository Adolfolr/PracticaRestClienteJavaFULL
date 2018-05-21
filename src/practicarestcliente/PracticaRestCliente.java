    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicarestcliente;

import clases.Agenda;
import clases.Persona;
import clases.Usuario;
import java.io.File;
import java.util.Scanner;
import metodos.ImportarExportar;
import servicios.AgendaServicio;
import servicios.ContactoServicio;
import servicios.LoginServicio;
import servicios.RegistroSericio;
import servicios.ValidarAgendaServicio;
import servicios.ValidarPersonaServicio;
/**
 *
 * @author y9d1ru
 */
public class PracticaRestCliente {

    static Agenda a;
    static ImportarExportar guardador;
    static Scanner scanner;
    static String token;
    
    public static void main(String[] args) {
        a = new Agenda();
        guardador = new ImportarExportar("Agenda.xml");
        scanner = new Scanner(System.in);
        crearMenu1();
    }
    
    public static void crearMenu1(){
        System.out.println("1. Login");
        System.out.println("2. Registro");
        System.out.println("3. Salir");
        int opcion = scanner.nextInt();
        comprobar1(opcion);
    }
    
    public static void crearMenu2() {
        System.out.println("1. Añadir contacto");
        System.out.println("2. Mostrar contacto");
        System.out.println("3. Modificar contacto");
        System.out.println("4. Eliminar contacto");
        System.out.println("5. Mostrar agenda");        
        System.out.println("6. Validar agenda");
        System.out.println("7. Validar persona");
        System.out.println("8. Salir");
        int opcion = scanner.nextInt();
        comprobar2(opcion);
}
    public static void comprobar1(int opcion){
        switch(opcion){
            case 1:
                login();
                break;
            case 2:
                registro();
                break;
            case 3:
                System.exit(0);
                break;
        }
    }
    
    public static void comprobar2(int opcion) {
        switch (opcion) {
            case 1:
                crearContacto();
                break;
            case 2:
                verContacto();
                break;
            case 3:
                modificarContacto();
            case 4:
                borrarContacto();
                break;
            case 5:
                verAgenda();
                break;
            case 6:
                validarAgenda();
                break;
            case 7:
                validarPersona();
                break;
            case 8:
                System.exit(0);
        }
    }

    public static void registro(){
        RegistroSericio registroServicio = new RegistroSericio();
        System.out.println("Introduzca su nombre de usuario:");
        String nombre = scanner.next();
        System.out.println("Introduzca la password:");
        String password = scanner.next();
        Usuario user = new Usuario(nombre, password);
        registroServicio.postXml(user);
        crearMenu1();
    }
    
    public static void login(){
        LoginServicio loginServicio = new LoginServicio();
        System.out.println("Introduzca su nombre de usuario:");
        String nombre = scanner.next();
        System.out.println("Introduzca la password:");
        String password = scanner.next();
        Usuario user = new Usuario(nombre, password);
        token = loginServicio.putXml(user);
        crearMenu2();
    }
    
    public static void guardar(){
        guardador.guardarAgenda(a);
        crearMenu2();
    }
    
    
    public static void verAgenda() {
        AgendaServicio agendaServicio = new AgendaServicio();
        Agenda a = agendaServicio.getXml(Agenda.class, token);
        
        for (Persona p : a.getPersonas()) {
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Telefono: " + p.getTelefono());
            System.out.println("Email: " + p.getEmail());
            System.out.println("--------------");
        }
        crearMenu2();
    }
    
    public static void validarAgenda(){        
        ValidarAgendaServicio validarAgendaServicio = new ValidarAgendaServicio();
        System.out.println("¿Qué agenda quieres validar?:");
        String nombreAgenda = scanner.next();
        ImportarExportar ie = new ImportarExportar(nombreAgenda);
        Agenda a = ie.cargar();
//        String valida = validarAgendaServicio.postXML(a);
        System.out.println(validarAgendaServicio.postXML(a));
        crearMenu2();
    }
    
    public static void validarPersona(){
        System.out.println("Introduce el nombre del archivo de la persona a validar:");  
        String nombre = scanner.next();
        ImportarExportar ie = new ImportarExportar(nombre);
        Persona p = ie.importarPersona(new File(nombre));
        ValidarPersonaServicio validarPersonaServicio = new ValidarPersonaServicio();
        String respuesta = validarPersonaServicio.postXML(p);
        if(p!= null){
        if(respuesta.equals("true")){
            System.out.println("-----------------------");
            System.out.println("Esta persona es válida");
            System.out.println("-----------------------");
        }else{
            System.out.println("-----------------------");
            System.out.println("La persona no es válida");
            System.out.println("-----------------------");
        }
        }
        crearMenu2();
    }
    

    public static void crearContacto() {
        ContactoServicio contactoServicio = new ContactoServicio();
        System.out.println("Introduzca el nombre:");
        String nombre = scanner.next();
        String restriccionEmail = "[_\\-a-zA-z0-9\\.\\+]+@[a-zA-z0-9](\\.?[\\-a-zA-z0-9]*[a-zA-z0-9])*";

        System.out.println("Introduzaca el numero de telefono:");
        String telefono = scanner.next();
        System.out.println("Introduzca el email:");
        String email = scanner.next();
        while(!email.matches(restriccionEmail) && email.length()<255){
            System.out.println("Por favor, introduzca un email válido");
            email = scanner.next();
        }
        Persona p = new Persona(nombre, telefono, email);
        contactoServicio.postXml(p, token);
        //a.anadirPersona(p);
        crearMenu2();
}
    
    public static void modificarContacto(){
        ContactoServicio contactoServicio = new ContactoServicio();
        System.out.println("Introduzca el ID de la persona a modificar:");
        String idContacto = scanner.next();
        System.out.println("Introduzca el nombre");
        String nombre = scanner.next();
        String restriccionEmail = "[_\\-a-zA-z0-9\\.\\+]+@[a-zA-z0-9](\\.?[\\-a-zA-z0-9]*[a-zA-z0-9])*";
        System.out.println("Introduzca el numero de telefono:");
        String telefono = scanner.next();
        System.out.println("Introduzca el email:");
        String email = scanner.next();
        while(!email.matches(restriccionEmail) && email.length()<255){
            System.out.println("Por favor, introduzca un email válido");
            email = scanner.next();
        }
        Persona p = new Persona(nombre, telefono, email);
        contactoServicio.putXML(p,idContacto, token);
        crearMenu2();
    }
    
    public static void borrarContacto(){
        ContactoServicio contactoServicio = new ContactoServicio();
        System.out.println("Introduzca en id del contacto que deseas eliminar");
        String idContacto = scanner.next();
        contactoServicio.deleteXML(idContacto, token);
        crearMenu2();
    }
    
    public static void verContacto() {
        ContactoServicio contactoServicio = new ContactoServicio();
        System.out.println("Introduce el nombre: ");
        String nombre = scanner.next();
        Agenda a = contactoServicio.getXml(Agenda.class, nombre, token);
        
        for (Persona p : a.getPersonas()) {
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Telefono: " + p.getTelefono());
            System.out.println("Email: " + p.getEmail());
            System.out.println("--------------");
        }
        crearMenu2();
    }
}
