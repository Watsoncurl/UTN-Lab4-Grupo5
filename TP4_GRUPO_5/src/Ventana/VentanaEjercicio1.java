package Ventana;

import Filters.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;


public class VentanaEjercicio1 extends JFrame {

	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JFormattedTextField txtFechaNac;
	private JLabel lblResultado;

	public VentanaEjercicio1() {
		setTitle("Contactos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(600, 100, 500, 300);
		getContentPane().setLayout(null);
		

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(30, 20, 100, 20);
		getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(140, 20, 310, 20);
		((AbstractDocument) txtNombre.getDocument()).setDocumentFilter(new LettersOnlyFilter());
		getContentPane().add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(30, 60, 100, 20);
		getContentPane().add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.setBounds(140, 60, 310, 20);
		((AbstractDocument) txtApellido.getDocument()).setDocumentFilter(new LettersOnlyFilter());
		getContentPane().add(txtApellido);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(30, 100, 100, 20);
		getContentPane().add(lblTelefono);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(140, 100, 310, 20);
		((AbstractDocument) txtTelefono.getDocument()).setDocumentFilter(new NumbersOnlyFilter());
		getContentPane().add(txtTelefono);

		JLabel lblFechaNac = new JLabel("Fecha Nac:");
		lblFechaNac.setBounds(30, 140, 100, 20);
		getContentPane().add(lblFechaNac);

		try {
		    MaskFormatter dateMask = new MaskFormatter("##/##/####");
		    dateMask.setPlaceholderCharacter('_');
		    txtFechaNac = new JFormattedTextField(dateMask);
		    txtFechaNac.setBounds(140, 140, 310, 20);
		    getContentPane().add(txtFechaNac);
		} catch (ParseException e) {
		    e.printStackTrace();
		}

		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean completo = true;
				
				if (txtNombre.getText().compareTo("") == 0) {
					txtNombre.setBackground(Color.red);
					
					completo = false;
				}
				else {
					txtNombre.setBackground(new Color(255, 255, 255));
				}
				
				
				if (txtApellido.getText().compareTo("") == 0) {
					txtApellido.setBackground(Color.red);
					completo = false;
				}
				else {
					txtApellido.setBackground(new Color(255, 255, 255));
				}
				
				
				if (txtTelefono.getText().compareTo("") == 0) {
					txtTelefono.setBackground(Color.red);
					completo = false;
				}
				else {
					txtTelefono.setBackground(new Color(255, 255, 255));
				}
				
				String fechaTexto = txtFechaNac.getText();

				if (fechaTexto.contains("_") || fechaTexto.trim().isEmpty()) {
				    txtFechaNac.setBackground(Color.red);
				    completo = false;
				} else if (!DateValidator.isValidDate(fechaTexto)) {
				    txtFechaNac.setBackground(Color.orange);
				    completo = false;
				    JOptionPane.showMessageDialog(null, "La fecha ingresada no es válida. Aseguresé que tanto día, mes y año sean fechas validas");
				} else {
				    txtFechaNac.setBackground(new Color(255, 255, 255));
				}

				
				if(completo) {
					String resultado = "Los datos ingresados fueron: " + txtNombre.getText() + ", " + txtApellido.getText() + 
							", " + txtTelefono.getText() + ", " + txtFechaNac.getText();
					lblResultado.setText(resultado);
				}else {
					lblResultado.setText("");
					JOptionPane.showMessageDialog(null,"Debe completar los campos en rojo");
				}
			}
		});
		btnMostrar.setBounds(240, 180, 100, 25);
		getContentPane().add(btnMostrar);
		
		lblResultado = new JLabel();
		lblResultado.setBounds(30, 220, 410, 30);
		getContentPane().add(lblResultado);
		
		
		
		
		
	}
	
	public void restart() {
		txtNombre.setBackground(new Color(255, 255, 255));
		txtNombre.setText("");
	}
	
}
