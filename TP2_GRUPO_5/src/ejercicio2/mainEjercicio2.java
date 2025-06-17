package ejercicio2;

public class mainEjercicio2 {
	 public static void main(String[] args) {

	
	        ProductoFresco fresco = new ProductoFresco( "2025-11-20", 101,"2025-11-15","Argentina");

	        ProductoRefrigerado refrigerado = new ProductoRefrigerado("2026-01-13",202,"ORG520");

	        ProductoCongelado congelado = new ProductoCongelado( "2026-03-22", 303, -18.5);

	        /*System.out.println(fresco.toString());
	        System.out.println(refrigerado.toString());
	        System.out.println(congelado.toString());*/
	       
	        
	        fresco.mostrar();
	        refrigerado.mostrar();
	        congelado.mostrar();
	    }
}
