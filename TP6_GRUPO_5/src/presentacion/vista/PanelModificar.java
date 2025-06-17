package presentacion.vista;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PanelModificar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldDni;
	private JButton btnModificar;
	private JList<String> list;
	private DefaultListModel<String> listModel;

	/**
	 * Create the panel.
	 */
	public PanelModificar() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Seleccione la persona que desea modificar:");
		lblNewLabel.setBounds(10, 26, 393, 14);
		add(lblNewLabel);
		
		listModel = new DefaultListModel<>();
		list = new JList<String>(listModel);
		list.setBounds(10, 51, 345, 140);
		list.setBorder(BorderFactory.createLineBorder(Color.black));
		add(list);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(10, 202, 80, 20);
		add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldApellido = new JTextField();
		textFieldApellido.setBounds(97, 202, 80, 20);
		add(textFieldApellido);
		textFieldApellido.setColumns(10);
		
		textFieldDni = new JTextField();
		textFieldDni.setBounds(182, 202, 80, 20);
		add(textFieldDni);
		textFieldDni.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(266, 201, 89, 23);
		add(btnModificar);

	}
	
	public JTextField getTextFieldNombre() { return textFieldNombre; }
	public JTextField getTextFieldApellido() { return textFieldApellido; }
	public JTextField getTextFieldDni() { return textFieldDni; }
	public JButton getBtnModificar() { return btnModificar; }
	public JList<String> getList() { return list; }
	public DefaultListModel<String> getListModel() { return listModel; }
}
