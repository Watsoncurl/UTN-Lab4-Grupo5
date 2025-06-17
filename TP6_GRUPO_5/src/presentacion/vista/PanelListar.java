package presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelListar extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel tableModel;

    public PanelListar() {
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Listado de Personas:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblTitulo, BorderLayout.NORTH);

        String[] columnas = { "Nombre", "Apellido", "DNI" };
        tableModel = new DefaultTableModel(null, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(360, 160));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelCentro.add(scrollPane);

        add(panelCentro, BorderLayout.CENTER);
    }

    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }
}
