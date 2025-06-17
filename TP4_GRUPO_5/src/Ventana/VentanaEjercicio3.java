package Ventana;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;

public class VentanaEjercicio3 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaEjercicio3 frame = new VentanaEjercicio3();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public VentanaEjercicio3() {
       
        setTitle("Selección Multiple"); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 500, 400); 
      
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panelButtom = new JPanel();
        panelButtom.setBorder(new LineBorder(Color.BLACK));
        panelButtom.setBackground(new Color(240, 240, 240));
        panelButtom.setForeground(new Color(0, 0, 0));
        panelButtom.setBounds(10, 32, 464, 74);
        contentPane.add(panelButtom);
        panelButtom.setLayout(null);
        
        JLabel lbl1 = new JLabel("Elije un sistema operativo");
        lbl1.setBounds(10, 31, 147, 14);
        panelButtom.add(lbl1);
        
        JRadioButton rdbtnWindows = new JRadioButton("Windows");
        rdbtnWindows.setBounds(177, 27, 93, 23);
        panelButtom.add(rdbtnWindows);
        
        JRadioButton rdbtnMac = new JRadioButton("Mac");
        rdbtnMac.setBounds(289, 27, 82, 23);
        panelButtom.add(rdbtnMac);
        
        JRadioButton rdbtnLinux = new JRadioButton("Linux");
        rdbtnLinux.setBounds(373, 27, 81, 23);
        panelButtom.add(rdbtnLinux);
        
        ButtonGroup grupoSistemasOp = new ButtonGroup();
        grupoSistemasOp.add(rdbtnWindows);
        grupoSistemasOp.add(rdbtnMac);
        grupoSistemasOp.add(rdbtnLinux);
        
        JPanel panelEspecialidades = new JPanel();
        panelEspecialidades.setBorder(new LineBorder(Color.BLACK));
        panelEspecialidades.setBackground(new Color(240, 240, 240));
        panelEspecialidades.setBounds(10, 130, 464, 150);
        panelEspecialidades.setLayout(null);
        contentPane.add(panelEspecialidades);

        JLabel lbl2 = new JLabel("Elije una especialidad");
        lbl2.setBounds(20, 60, 200, 14);
        panelEspecialidades.add(lbl2);

        JCheckBox chkProgramacion = new JCheckBox("Programación");
        chkProgramacion.setBounds(250, 30, 150, 23);
        panelEspecialidades.add(chkProgramacion);

        JCheckBox chkDisenoGrafico = new JCheckBox("Administración");
        chkDisenoGrafico.setBounds(250, 60, 150, 23);
        panelEspecialidades.add(chkDisenoGrafico);

        JCheckBox chkAdminSistemas = new JCheckBox("Diseño Gráfico");
        chkAdminSistemas.setBounds(250, 90, 200, 23);
        panelEspecialidades.add(chkAdminSistemas);
        
        JLabel lblHoras = new JLabel("Horas en el computador:");
        lblHoras.setBounds(120, 300, 180, 20);
        contentPane.add(lblHoras);

        JTextField txtHoras = new JTextField();
        txtHoras.setBounds(310, 300, 80, 20);
        contentPane.add(txtHoras);
        txtHoras.setColumns(10);
    }
}

