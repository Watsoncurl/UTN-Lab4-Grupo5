package ejercicio1;

import java.util.List;

public class Principal {

	 public static void main(String[] args) {
	        
	        Archivo archivo = new Archivo(); 
	        archivo.setRuta("Personas.txt");
	        
	        if(archivo.existe()) {
	            System.out.println("Existe archivo");
	        } else {
	            System.out.println("No existe archivo entonces lo creo");
	            archivo.creaArchivo();
	        }
	        
	        System.out.println("\nA CONTINUACION LEE DE A LINEAS: ");
	        archivo.lee_lineas();
	        
	        System.out.println("\nA CONTINUACION SE LISTAN LOS DATOS VALIDOS ORDENADOS POR APELLIDO:");
	        List<Persona> personas = archivo.obtenerPersonasValidasOrdenadas();
	        //for (Persona persona : personas) {
	        //    System.out.println(persona);
	        //}
	    }

	}

