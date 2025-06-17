package negocioImpl;

import entidad.Persona;
import negocio.IPersonaNegocio;
import java.util.ArrayList;
import java.util.List;

import daoImpl.PersonaDaoImpl;

public class PersonaNegocioImpl implements IPersonaNegocio {

    private List<Persona> personas = new ArrayList<>();
    private PersonaDaoImpl personaDao = new PersonaDaoImpl();
    @Override
    public boolean agregar(Persona nuevaPersona) {
        for (Persona p : personas) {
            if (p.getDNI() == nuevaPersona.getDNI()) {
                return false; 
            }
        }
        personas.add(nuevaPersona);
        return true;
    }

    @Override
    public boolean modificar(Persona modPersona) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getDNI() == modPersona.getDNI()) {
                personas.set(i, modPersona);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean eliminar(Persona eliminarPersona) {
        return personas.removeIf(p -> p.getDNI() == eliminarPersona.getDNI());
    }

    @Override
    public List<Persona> listarPersonas() {
    	 return personaDao.listarPersonas();
    }
    
}
