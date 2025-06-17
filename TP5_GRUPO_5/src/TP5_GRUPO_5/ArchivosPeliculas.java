package TP5_GRUPO_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ArchivosPeliculas {

	private String ruta;
	private Pelicula pelicula;
	
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
	
	public List<Pelicula> leerPeliculas() {
        List<Pelicula> lista = new ArrayList<>();
        try (BufferedReader lector = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    int id = Integer.parseInt(datos[0].trim());
                    String nombre = datos[1].trim();
                    Categoria categoria = Categoria.fromString(datos[2].trim());
                    Pelicula pelicula = new Pelicula(id, nombre, categoria);
                    lista.add(pelicula);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
        return lista;
    }
	
	public boolean agregarPelicula(Pelicula p){
	    try(FileWriter fw = new FileWriter(ruta, true)){
	        fw.write(p.getId() + ","+p.getNombre()+","+p.getCategoria().getNombre()+"\n");
	        return true;
	    }catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
}
