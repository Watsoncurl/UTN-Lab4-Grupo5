package TP5_GRUPO_5;

public class Categoria {
	private static int count = 0;
	private int id = 0;
	private String nombre;
	
	Categoria(String nombre)
	{
		this.id = count++;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "[" + this.id + "] [nombre=" + this.nombre + "]";
	}
	
	public static Categoria fromString(String nombre) {
	        return new Categoria(nombre);
	    }
	
	
}
