package dao;

import entidad.Persona;
import java.util.List;

public interface IPersonaDao {
	
	public boolean agregar(Persona persona);
	public boolean modificar(Persona modPersona);
	public boolean eliminar(Persona eliminarPersona);
	public List<Persona> listarPersonas();

}
