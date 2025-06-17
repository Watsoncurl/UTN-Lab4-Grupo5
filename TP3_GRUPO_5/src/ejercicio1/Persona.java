package ejercicio1;

public class Persona {
	
	private String nombre;
	private String apellido;
	private String dni;
	
	
	
	public Persona(String nombre, String apellido, String dni)
	{
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Override
	public String toString() {
		return  "Apellido: " + apellido + " Nombre: " + nombre + " DNI: " + dni ;
	}
	
	public static boolean verificarDNIInvalido(String dni) throws DniInvalido {
		
		boolean auxSoloNumeros = true;
		
		for(int i = 0; i<dni.length(); i++) {
			
			if (!Character.isDigit(dni.charAt(i))) {
				auxSoloNumeros = false;
			}
		}
		if(auxSoloNumeros == false) {
			throw new  DniInvalido();
		}
		return true;
	}
}


