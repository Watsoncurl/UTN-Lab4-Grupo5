package ejercicio1;

import java.util.Iterator;
import java.util.TreeSet;



public class mainEjercicio1_b {

	public static void main(String[] args) {
		TreeSet<Profesor> listaProfesores = new TreeSet<Profesor>();
		
		listaProfesores.add(new Profesor("Lisa Marquez", 21, "Titular", 1));
		listaProfesores.add(new Profesor("Rodolfo Nieva", 43, "Suplente", 11));
		listaProfesores.add(new Profesor("Natalia Diaz", 32, "Suplente", 5));
		listaProfesores.add(new Profesor("Raul Andre", 50, "Titular", 20));
		listaProfesores.add(new Profesor("Oscar Sosa", 32, "Suplente", 2));
		
		System.out.println("Listado de Profesores en Treeset:");
		
		Iterator<Profesor> it = listaProfesores.iterator();
		while(it.hasNext())
		{
			Profesor p = (Profesor) it.next();
			System.out.println(p.toString());
		}
				
	}

}
