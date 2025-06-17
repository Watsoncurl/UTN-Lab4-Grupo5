package presentacion.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelAgregar extends JPanel {

    public JTextField txtNombre;
    public JTextField txtApellido;
    public JTextField txtDni;
    public JButton btnAceptar;

    public PanelAgregar() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        txtNombre = new JTextField(15);
        add(txtNombre, gbc);
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'a' || c>'z') && (c<'A' || c>'Z')) e.consume(); 
			}
		});

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Apellido:"), gbc);

        gbc.gridx = 1;
        txtApellido = new JTextField(15);
        add(txtApellido, gbc);
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c<'a' || c>'z') && (c<'A' || c>'Z')) e.consume(); 
			}
		});        
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("DNI:"), gbc);

        gbc.gridx = 1;
        txtDni = new JTextField(15);
        add(txtDni, gbc);
		txtDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (c<'0' || c>'9') e.consume(); 
			}
		});

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnAceptar = new JButton("Aceptar");
        add(btnAceptar, gbc);
        
        
    }
}
