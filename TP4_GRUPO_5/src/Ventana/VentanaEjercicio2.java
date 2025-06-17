package Ventana;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class VentanaEjercicio2 extends JFrame {
    private JTextField textFieldNota1;
    private JTextField textFieldNota2;
    private JTextField textFieldNota3;
    private JTextField textField;
    private JTextField textField_1;

    public VentanaEjercicio2() {

        setTitle("Promedio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(600, 100, 500, 400);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(25, 28, 313, 187);
        panel.setBorder(new TitledBorder(null, "Notas del Estudiante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNota1 = new JLabel("Nota 1:");
        lblNota1.setBounds(23, 41, 46, 14);
        panel.add(lblNota1);

        JLabel lblNota2 = new JLabel("Nota 2:");
        lblNota2.setBounds(23, 66, 46, 14);
        panel.add(lblNota2);

        JLabel lblNota3 = new JLabel("Nota 3:");
        lblNota3.setBounds(23, 91, 46, 14);
        panel.add(lblNota3);

        JLabel lblTPS = new JLabel("TPS:");
        lblTPS.setBounds(23, 116, 46, 14);
        panel.add(lblTPS);

        textFieldNota1 = new JTextField();
        textFieldNota1.setBounds(115, 38, 150, 20);
        panel.add(textFieldNota1);
        textFieldNota1.setColumns(10);

        textFieldNota2 = new JTextField();
        textFieldNota2.setBounds(115, 63, 150, 20);
        panel.add(textFieldNota2);
        textFieldNota2.setColumns(10);

        textFieldNota3 = new JTextField();
        textFieldNota3.setBounds(115, 88, 150, 20);
        panel.add(textFieldNota3);
        textFieldNota3.setColumns(10);

        JComboBox comboBoxTPS = new JComboBox();
        comboBoxTPS.setModel(new DefaultComboBoxModel(new String[] { "Aprobado", "Desaprobado" }));
        comboBoxTPS.setBounds(115, 113, 150, 20);
        panel.add(comboBoxTPS);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(25, 237, 313, 113);
        panel_1.setBorder(new TitledBorder(null, "Notas del Estudiante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblPromedio = new JLabel("Promedio:");
        lblPromedio.setBounds(23, 42, 58, 14);
        panel_1.add(lblPromedio);

        JLabel lblCondicion = new JLabel("Condición:");
        lblCondicion.setBounds(23, 67, 69, 14);
        panel_1.add(lblCondicion);

        textField = new JTextField();
        textField.setBounds(115, 39, 150, 20);
        panel_1.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(115, 64, 150, 20);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        JButton btnCalcular = new JButton("CALCULAR");
        btnCalcular.setBounds(358, 75, 116, 46);
        getContentPane().add(btnCalcular);

        btnCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String strNota1 = textFieldNota1.getText().trim();
                    String strNota2 = textFieldNota2.getText().trim();
                    String strNota3 = textFieldNota3.getText().trim();
                    String condicionTP = (String) comboBoxTPS.getSelectedItem();

                    if (strNota1.isEmpty() || strNota2.isEmpty() || strNota3.isEmpty()) {
                        throw new Exception("Todos los campos de nota deben estar completos.");
                    }

                    double nota1 = Double.parseDouble(strNota1);
                    double nota2 = Double.parseDouble(strNota2);
                    double nota3 = Double.parseDouble(strNota3);

                    if (nota1 < 1 || nota1 > 10 || nota2 < 1 || nota2 > 10 || nota3 < 1 || nota3 > 10) {
                        throw new Exception("Las notas deben estar entre 1 y 10.");
                    }

                    if (condicionTP == null || condicionTP.isEmpty()) {
                        throw new Exception("Debe seleccionar una opción en el campo TPS.");
                    }

                    double promedio = (nota1 + nota2 + nota3) / 3;
                    String condicionAlumno;

                    if (condicionTP.equals("Desaprobado")) {
                        condicionAlumno = "Libre";
                    } else if (nota1 < 6 || nota2 < 6 || nota3 < 6) {
                        condicionAlumno = "Libre";
                    } else if (nota1 >= 8 && nota2 >= 8 && nota3 >= 8) {
                        condicionAlumno = "Promocionado";
                    } else {
                        condicionAlumno = "Regular";
                    }

                    textField.setText(String.format("%.2f", promedio));
                    textField_1.setText(condicionAlumno);

                } catch (NumberFormatException ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, "Las notas deben ser números válidos (pueden tener decimales).");
                } catch (Exception ex) {
                    javax.swing.JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        JButton buttonNuevo = new JButton("NUEVO");
        buttonNuevo.setBounds(358, 121, 116, 46);
        getContentPane().add(buttonNuevo);

        buttonNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldNota1.setText("");
                textFieldNota2.setText("");
                textFieldNota3.setText("");
                comboBoxTPS.setSelectedIndex(0);
                textField.setText("");
                textField_1.setText("");
            }
        });

        JButton btnSalir = new JButton("SALIR");
        btnSalir.setBounds(358, 167, 116, 46);
        getContentPane().add(btnSalir);

        btnSalir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
       
    }
}

