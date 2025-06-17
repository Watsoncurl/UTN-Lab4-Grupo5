package presentacion.controlador;

import presentacion.vista.PanelAgregar;
import presentacion.vista.PanelEliminar;
import presentacion.vista.PanelListar;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.PanelModificar;
import entidad.Persona;
import negocio.IPersonaNegocio;
import negocioImpl.PersonaNegocioImpl;
import daoImpl.PersonaDaoImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.event.*;
import java.util.List;

public class ControladorPrincipal implements ActionListener {
    private VentanaPrincipal vista;

    public ControladorPrincipal(VentanaPrincipal vista) {
        this.vista = vista;

        this.vista.itemAgregar.addActionListener(this);
        this.vista.itemModificar.addActionListener(this);
        this.vista.itemEliminar.addActionListener(this);
        this.vista.itemListar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.itemAgregar) {
            PanelAgregar panelAgregar = new PanelAgregar();
            vista.mostrarPanel(panelAgregar);

            IPersonaNegocio personaNegocio = new PersonaNegocioImpl();
            PersonaDaoImpl p = new PersonaDaoImpl();
            
            panelAgregar.btnAceptar.addActionListener(ev -> {
                String nombre = panelAgregar.txtNombre.getText().trim();
                String apellido = panelAgregar.txtApellido.getText().trim();
                String dniStr = panelAgregar.txtDni.getText().trim();
                
                if (nombre.isEmpty() || apellido.isEmpty() || dniStr.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int dni;
                try {
                    dni = Integer.parseInt(dniStr);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "DNI inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Persona nuevaPersona = new Persona(dniStr, nombre, apellido);
                boolean agregado = p.agregar(nuevaPersona);

                if (agregado) {
                    JOptionPane.showMessageDialog(vista, "Persona agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    panelAgregar.txtNombre.setText("");
                    panelAgregar.txtApellido.setText("");
                    panelAgregar.txtDni.setText("");
                } else {
                    JOptionPane.showMessageDialog(vista, "El DNI ya está registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

        } else if (e.getSource() == vista.itemModificar) {
        	PanelModificar panelModificar = new PanelModificar();
            vista.mostrarPanel(panelModificar);
            IPersonaNegocio personaNegocio = new PersonaNegocioImpl();
            List<Persona> personas = personaNegocio.listarPersonas();
            PersonaDaoImpl personaDAO = new PersonaDaoImpl();
            
            DefaultListModel<String> model = panelModificar.getListModel();
            model.clear();
            
            for (Persona p : personas) {
                model.addElement(p.getDNI() + " - " + p.getNombre() + " " + p.getApellido());
            }
            JList<String> list = panelModificar.getList();
            list.addListSelectionListener(ev -> {
                int index = list.getSelectedIndex();
                if (index >= 0) {
                    Persona seleccionada = personas.get(index);
                    panelModificar.getTextFieldDni().setText(seleccionada.getDNI());
                    panelModificar.getTextFieldNombre().setText(seleccionada.getNombre());
                    panelModificar.getTextFieldApellido().setText(seleccionada.getApellido());
                }
            });
            
            	panelModificar.getBtnModificar().addActionListener(ev -> {
                int index = list.getSelectedIndex();
                if (index < 0) {
                    JOptionPane.showMessageDialog(vista, "Debe seleccionar una persona de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String nuevoNombre = panelModificar.getTextFieldNombre().getText().trim();
                String nuevoApellido = panelModificar.getTextFieldApellido().getText().trim();
                String dni = panelModificar.getTextFieldDni().getText();

                if (nuevoNombre.isEmpty() || nuevoApellido.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "Nombre y Apellido no pueden estar vacíos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                Persona personaModificada = new Persona(dni, nuevoNombre, nuevoApellido);
                
                boolean ok = personaDAO.modificar(personaModificada);
                if (ok) {
                    JOptionPane.showMessageDialog(vista, "Persona modificada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    personas.set(index, personaModificada);
                    model.set(index, dni + " - " + nuevoNombre + " " + nuevoApellido);
                } else {
                    JOptionPane.showMessageDialog(vista, "Error al modificar persona.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
            
        } else if (e.getSource() == vista.itemEliminar) {
            
        	
        	PanelEliminar panelEliminar = new PanelEliminar();
            vista.mostrarPanel(panelEliminar);
            IPersonaNegocio personaNegocio = new PersonaNegocioImpl();
            final List<Persona> personas;
            
            

            try {
                personas = personaNegocio.listarPersonas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "No se pudo cargar la lista de personas. Verifique la conexión con la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;  // No continuar si hubo error en la carga
            }

            DefaultListModel<String> model = panelEliminar.getListModel();
            model.clear();

            if (personas.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "No hay personas registradas.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            } else {
                for (Persona p : personas) {
                    model.addElement(p.getDNI() + " - " + p.getNombre() + " " + p.getApellido());
                }
            }
            
            
            panelEliminar.getBtnEliminar().addActionListener(ev -> {
                // Obtener la persona seleccionada
                int selectedIndex = panelEliminar.getList().getSelectedIndex();
                if (selectedIndex >= 0) {
                    // Obtener la persona seleccionada
                    Persona personaSeleccionada = personas.get(selectedIndex);
                    
                    // Intentar eliminar la persona
                    PersonaDaoImpl personaDao = new PersonaDaoImpl();
                    boolean eliminado = personaDao.eliminar(personaSeleccionada);

                    if (eliminado) {
                        JOptionPane.showMessageDialog(vista, "Persona eliminada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        // Actualizar el modelo de la lista
                        model.remove(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Error al eliminar persona.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Debe seleccionar una persona para eliminar.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            
            
            });
            
}
    else if (e.getSource() == vista.itemListar) {
        PanelListar panelListar = new PanelListar();
        vista.mostrarPanel(panelListar);

        IPersonaNegocio personaNegocio = new PersonaNegocioImpl();
        List<Persona> personas = personaNegocio.listarPersonas();

        DefaultTableModel model = panelListar.getTableModel();
        model.setRowCount(0);

        for (Persona p : personas) {
            Object[] fila = { p.getNombre(), p.getApellido(), p.getDNI() };
            model.addRow(fila);
        }
    }
} 
    
}
