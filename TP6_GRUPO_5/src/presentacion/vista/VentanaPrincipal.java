package presentacion.vista;

import java.awt.BorderLayout;

import javax.swing.*;

public class VentanaPrincipal extends JFrame {
    public JMenuItem itemAgregar, itemModificar, itemEliminar, itemListar;
    private JPanel panelPrincipal;

    public VentanaPrincipal() {
        setTitle("Programa");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuPersona = new JMenu("Persona");

        itemAgregar = new JMenuItem("Agregar");
        itemModificar = new JMenuItem("Modificar");
        itemEliminar = new JMenuItem("Eliminar");
        itemListar = new JMenuItem("Listar");

        menuPersona.add(itemAgregar);
        menuPersona.add(itemModificar);
        menuPersona.add(itemEliminar);
        menuPersona.add(itemListar);

        menuBar.add(menuPersona);
        setJMenuBar(menuBar);
        
        panelPrincipal = new JPanel(new BorderLayout());
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    }
    
    public void mostrarPanel(JPanel nuevoPanel) {
        panelPrincipal.removeAll();
        panelPrincipal.add(nuevoPanel, BorderLayout.CENTER);
        panelPrincipal.revalidate();
        panelPrincipal.repaint();
    }
}

