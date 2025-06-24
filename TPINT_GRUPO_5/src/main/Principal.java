package main;

import datosImpl.ClientesDaoImpl;
import entidades.Cliente;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Cliente cliente = new Cliente(2, "12615598", "20126155981", "Jose", "Perez", "M", "Argentina", "2000-12-18" ,"Siempre viva 123", "Tigre", "Buenos Aires", "pepito@yahoo.com.ar", "4715", true);
		ClientesDaoImpl prueba = new ClientesDaoImpl();
		prueba.insertar(cliente);
		
		//System.out.println(cliente.getApellido());
		
		
	}

}
