package presentacion.vista;

import java.awt.*;
import javax.swing.*;

public class PanelEliminar extends JPanel {

	private static final long serialVersionUID = 1L;
	private JList<String> list;
	private DefaultListModel<String> listModel;
	private JButton btnEliminar;

	public PanelEliminar() {
		setLayout(new BorderLayout());

		// Título centrado arriba
		JLabel lblTitulo = new JLabel("Eliminar Usuarios");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitulo, BorderLayout.NORTH);

		// Panel central con componentes alineados verticalmente
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
		panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding

		listModel = new DefaultListModel<>();
		list = new JList<>(listModel);
		list.setBorder(BorderFactory.createLineBorder(Color.black));
		list.setFixedCellWidth(250);
		list.setVisibleRowCount(8);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(scrollPane);
		panelCentral.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio

		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelCentral.add(btnEliminar);

		// Centrado horizontal
		JPanel wrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
		wrapper.add(panelCentral);

		add(wrapper, BorderLayout.CENTER);
	}

	public DefaultListModel<String> getListModel() {
		return listModel;
	}

	public JList<String> getList() {
		return list;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}
}
