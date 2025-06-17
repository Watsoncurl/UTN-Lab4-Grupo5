package TP5_GRUPO_5;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Programa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelAgregarPelicular panelAgregar;
	private PanelListarPeliculas panelListar; 

	public Programa() {
		setTitle("Programa");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnPeliculas = new JMenu("Peliculas");
		menuBar.add(mnPeliculas);

		JMenuItem mntmAgregar = new JMenuItem("Agregar");
		mnPeliculas.add(mntmAgregar);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mnPeliculas.add(mntmListar);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

	
		panelAgregar = new PanelAgregarPelicular();
		panelListar = new PanelListarPeliculas();

		mntmAgregar.addActionListener((ActionEvent e) -> {
			contentPane.removeAll();                 
			contentPane.add(panelAgregar, BorderLayout.CENTER);
			contentPane.revalidate();              
			contentPane.repaint();                  
		});

		mntmListar.addActionListener((ActionEvent e) -> {
            contentPane.removeAll();                  
            contentPane.add(panelListar, BorderLayout.CENTER); 
            contentPane.revalidate();              
            contentPane.repaint();                  
        });
	}
}
