package Ejercicio1;

public class Principal {
	
	public static void main(String[] args) {
		
		System.out.println("Empleados:");
		
		Empleado empleado1 = new Empleado("Damian", 29);
		Empleado empleado2 = new Empleado("Lucia", 26);
		Empleado empleado3 = new Empleado("Dario", 35);
		Empleado empleado4 = new Empleado();
		Empleado empleado5 = new Empleado();
		
		System.out.println(empleado1.toString());
		System.out.println(empleado2.toString());
		System.out.println(empleado3.toString());
		System.out.println(empleado4.toString());
		System.out.println(empleado5.toString());
		
		System.out.println("Proximo ID: " + Empleado.getProximoID());
	}
}
