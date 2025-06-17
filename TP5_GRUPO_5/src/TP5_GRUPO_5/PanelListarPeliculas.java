package TP5_GRUPO_5;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Font;

public class PanelListarPeliculas extends JPanel {


	private static final long serialVersionUID = 1L;
	private JTextPane tpPeliculas;
	
	public PanelListarPeliculas() {
		setLayout(null);
		
		JLabel lblPeliculas = new JLabel("Peliculas");
		lblPeliculas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPeliculas.setBounds(34, 132, 60, 14);
		add(lblPeliculas);
		
		tpPeliculas = new JTextPane();
		tpPeliculas.setBounds(104, 28, 304, 243);
		add(tpPeliculas);

	}
}
