package ejercicio1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Archivo {

	//Si en la variable ruta recibe solo un nombre de un archivo, entonces crea ese archivo dentro del proyecto
	//Si en ruta recibe una ruta absoluta, entonces lo crea en esa ruta
	
	private String ruta;
	
	public String getRuta() {
		return ruta;
	}
	
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public boolean existe()
	{
		File archivo = new File(ruta); 
		if(archivo.exists())
		      return true;
		return false;
	}
	
	public boolean creaArchivo()
	{
		FileWriter escritura;
		try {
			escritura = new FileWriter(ruta, true);
			escritura.write("");
			escritura.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
			
	}
	

	
	
	public void escribe_lineas(String frase) {
		try 
		{	
			FileWriter entrada = new FileWriter(ruta, true);
			BufferedWriter miBuffer = new BufferedWriter(entrada);
			miBuffer.write(frase);
			miBuffer.close();
			entrada.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void lee_lineas() {
		FileReader entrada;
		try {
			entrada = new FileReader(ruta);
			BufferedReader miBuffer = new BufferedReader(entrada);
			
		   String linea = "";
			while (linea != null) {
				System.out.println(linea);
				linea = miBuffer.readLine();
			}
			miBuffer.close();
			entrada.close();

		} catch (IOException e) {
			System.out.println("No se encontro el archivo");
		}
	}
	
	public List<Persona> obtenerPersonasValidasOrdenadas() {
        Set<String> dnisUnicos = new HashSet<>();
        List<Persona> personas = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("-");
                if (partes.length == 3) {
                    String nombre = partes[0].trim();
                    String apellido = partes[1].trim();
                    String dni = partes[2].trim();

                    try {
                        Persona.verificarDNIInvalido(dni);

                        if (!dnisUnicos.contains(dni)) {
                            Persona persona = new Persona(nombre, apellido, dni);
                            personas.add(persona);
                            dnisUnicos.add(dni);
                        }
                    } catch (DniInvalido e) {
                        System.out.println("DNI inválido descartado: " + dni + " (" + e.getMessage() + ")");
                    }
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        
        personas.sort(Comparator.comparing(Persona::getApellido, String.CASE_INSENSITIVE_ORDER));
        return personas;
    }
}
