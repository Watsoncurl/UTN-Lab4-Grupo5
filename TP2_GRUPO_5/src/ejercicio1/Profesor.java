package ejercicio1;

import java.util.Objects;

public class Profesor extends Empleado implements Comparable<Profesor>{
	
	/// Atributos
	private String cargo;
	private int antiguedadDocente;
	
	/// Constructores
	public Profesor() {
		super();
		this.cargo = "Sin cargo";
		this.antiguedadDocente = 0;
	}
	
	public Profesor(String nombre, int edad, String cargo, int antiguedadDocente) {
		super(nombre, edad);
		this.cargo = cargo;
		this.antiguedadDocente = antiguedadDocente;
	}
	
	/// Metodos
	public int getAntiguedadDocente() {
		return antiguedadDocente;
	}
	public void setAntiguedadDocente(int antiguedadDocente) {
		this.antiguedadDocente = antiguedadDocente;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	
	@Override
	public String toString() {
		return "Profesor = " + super.toString() + ", Cargo: " + cargo + " y cuenta con " + antiguedadDocente +  " años de antiguedad";
							
	}

	@Override
	public int compareTo(Profesor o) {
	
				if(o.getId() == this.getId())
					return 0;
				
				if (o.getId()<this.getId())
				{
					return 1;
				}		
				return -1;
	}

	@Override
	public int hashCode() {
		return Objects.hash(antiguedadDocente, cargo);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;
	    if (obj == null || getClass() != obj.getClass()) return false;
	    
	    Profesor other = (Profesor) obj;
	    
	    return Objects.equals(getNombre(), other.getNombre()) &&
	           getEdad() == other.getEdad() &&
	           Objects.equals(cargo, other.cargo) &&
	           antiguedadDocente == other.antiguedadDocente;
	}
	
	
	

}
