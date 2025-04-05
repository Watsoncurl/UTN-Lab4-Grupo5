package ejercicio1;
import java.util.ArrayList;
import java.util.ListIterator;

public class mainEjercicio1_a {
	 public static void main(String[] args) {
	        /*ArrayList<Profesor> listaProfesores = new ArrayList<Profesor>(5);
	        listaProfesores.add(new Profesor("Tamara Herrera", 35, "Titular", 15));
	        listaProfesores.add(new Profesor("Angel Simon", 40, "Titular", 20));
	        listaProfesores.add(new Profesor("Maximiliano Sar Fernandez", 45, "Titular", 17));
	        listaProfesores.add(new Profesor("Brian Fernandez", 42, "Titular", 22));
	        listaProfesores.add(new Profesor("Daniel Klooster", 60, "Titular", 30));

	        System.out.println("Listado de Profesores CON ITERADOR:");

	        ListIterator<Profesor> it = listaProfesores.listIterator();
	        while (it.hasNext()) {
	            Profesor profesor = it.next();
	            System.out.println(profesor.toString());*/
		 
		 	Profesor profe1 = new Profesor("Tamara Herrera", 35, "Titular", 15);
	        Profesor profe2 = new Profesor("Tamara Herrera", 35, "Titular", 15);
	        Profesor profe3 = new Profesor("Tamara Herrera", 35, "Adjunto", 10);

	        if (profe1.equals(profe2)) {
	            System.out.println("profe1 y profe2 son la misma persona");
	        } else {
	            System.out.println("profe1 y profe2 NO son la misma persona");
	        }

	        if (profe1.equals(profe3)) {
	            System.out.println("profe1 y profe3 son la misma persona");
	        } else {
	            System.out.println("profe1 y profe3 NO son la misma persona");
	        }
	    }

}
