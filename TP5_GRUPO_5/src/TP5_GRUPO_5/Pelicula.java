package TP5_GRUPO_5;

public class Pelicula {
	 	private int id;
	    private String nombre;
	    private Categoria categoria;

	    public Pelicula(int id, String nombre, Categoria categoria) {
	        this.id = id;
	        this.nombre = nombre;
	        this.categoria = categoria;
	    }

	    
	    public int getId() {
	        return id;
	    }

	    public String getNombre() {
	        return nombre;
	    }

	    public Categoria getCategoria() {
	        return categoria;
	    }

	    
	    public void setId(int id) {
	        this.id = id;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public void setCategoria(Categoria categoria) {
	        this.categoria = categoria;
	    }

		@Override
		public String toString() {
			return "Pelicula [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + "]";
		}
	    
	    
}
