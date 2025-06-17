package Ventana;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel ventanaPrincipal;

	public Ventana() {
		setTitle("TP4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		ventanaPrincipal = new JPanel();
		ventanaPrincipal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setContentPane(ventanaPrincipal);
		ventanaPrincipal.setLayout(null);

		JLabel lblGrupo = new JLabel("GRUPO NRO 5:");
		lblGrupo.setBounds(23, 21, 104, 33);
		ventanaPrincipal.add(lblGrupo);

		JButton btnEjercicio1 = new JButton("Ejercicio 1");
		btnEjercicio1.setBounds(143, 59, 124, 27);
		ventanaPrincipal.add(btnEjercicio1);

		JButton btnEjercicio2 = new JButton("Ejercicio 2");
		btnEjercicio2.setBounds(143, 113, 124, 27);
		ventanaPrincipal.add(btnEjercicio2);

		JButton btnEjercicio3 = new JButton("Ejercicio 3");
		btnEjercicio3.setBounds(143, 169, 124, 27);
		ventanaPrincipal.add(btnEjercicio3);

		
		btnEjercicio1.addActionListener((ActionEvent e) -> {
			VentanaEjercicio1 ejercicio1 = new VentanaEjercicio1();
			ejercicio1.setVisible(true);
		});
		btnEjercicio2.addActionListener((ActionEvent e) -> {
			VentanaEjercicio2 ejercicio2 = new VentanaEjercicio2();
			ejercicio2.setVisible(true);
		});
		btnEjercicio3.addActionListener((ActionEvent e) -> {
		    VentanaEjercicio3 ejercicio3 = new VentanaEjercicio3();
		    ejercicio3.setVisible(true);
		});

	}
}
