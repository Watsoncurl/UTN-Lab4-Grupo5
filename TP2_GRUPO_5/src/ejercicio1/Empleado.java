package ejercicio1;

import java.util.Objects;

public class Empleado{
	
	//atributos
	private final int id;
	private String nombre;
	private int edad;
	private static int cont =  999;

	//metodos
	//punto 6
	public static int getProximoID() {
        return cont + 1;
    }
	
	public int getId() {
		return id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	//Constructores
	
	public Empleado()
	{
		cont++;
		this.id = cont;
		this.nombre = "Sin nombre";
		this.edad=99;
	}
	
	public Empleado(String nombre, int edad)
	{
		cont++;
		this.id = cont;
		this.nombre = nombre;
		this.edad=edad;
	}
	
	//metodo toString
		@Override
		public String toString() {
			return "Empleado Id: " + id + ", Nombre: " + nombre + ", Edad: " + edad;
		}

		@Override
		public int hashCode() {
			return Objects.hash(edad, nombre);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Empleado other = (Empleado) obj;
			return edad == other.edad && Objects.equals(nombre, other.nombre);
		}

		
		
		




}
