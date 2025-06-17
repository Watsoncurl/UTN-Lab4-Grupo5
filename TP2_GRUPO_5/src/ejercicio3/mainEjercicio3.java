package ejercicio3;

import java.util.ArrayList;
import java.util.Iterator;

public class mainEjercicio3 {

	public static void main(String[] args) {
		
		ArrayList<Edificio> listaEdificios = new ArrayList<Edificio>();	
		
		Polideportivo poli1 = new Polideportivo("poli1", 100,1);
		Polideportivo poli2 = new Polideportivo("poli2", 150,1);
		Polideportivo poli3 = new Polideportivo("poli3", 200,1);
		EdificioDeOficinas ofi1 = new EdificioDeOficinas(2000,30);
		EdificioDeOficinas ofi2 = new EdificioDeOficinas(2500,50);
		
		listaEdificios.add(poli1);
		listaEdificios.add(poli2);
		listaEdificios.add(poli3);
		listaEdificios.add(ofi1);
		listaEdificios.add(ofi2);
		
		Iterator<Edificio> it = listaEdificios.iterator();
		
		while(it.hasNext())
		{
			Edificio e = (Edificio) it.next();
			System.out.println(e.toString());
		}
	}
}
