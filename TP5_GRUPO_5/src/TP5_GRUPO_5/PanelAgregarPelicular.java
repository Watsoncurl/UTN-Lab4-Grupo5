package TP5_GRUPO_5;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;


public class PanelAgregarPelicular extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTextField textNombre;
	private JComboBox<String> cbGenero;
	private JButton btnAceptar;
	
	List<String> generos = Arrays.asList("Seleccione un genero ", "Terror", "Acción", "Suspenso", "Romántica");
	
	public PanelAgregarPelicular() {
		setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblId.setBounds(70, 68, 46, 14);
		add(lblId);
		
		JLabel lblIdPelicula = new JLabel("New label");
		lblIdPelicula.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIdPelicula.setBounds(225, 68, 71, 14);
		add(lblIdPelicula);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre.setBounds(70, 117, 60, 14);
		add(lblNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(225, 114, 118, 20);
		add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGenero.setBounds(70, 179, 60, 14);		
		add(lblGenero);
		
		cbGenero = new JComboBox<String>();
		cbGenero.setBounds(225, 176, 118, 20);
		for (String genero : generos) {
	        cbGenero.addItem(genero);
	    }
	    add(cbGenero);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAceptar.setBounds(70, 229, 155, 30);
		add(btnAceptar);
		
		btnAceptar.addActionListener((ActionEvent e) -> {
		    String nombre = textNombre.getText();
		    String genero = (String) cbGenero.getSelectedItem();

		    javax.swing.JOptionPane.showMessageDialog(this,
		        "Nombre: " + nombre + "\nGénero: " + genero,
		        "Datos Ingresados",
		        javax.swing.JOptionPane.INFORMATION_MESSAGE);
		});
	}
}
