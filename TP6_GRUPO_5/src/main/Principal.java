package main;

import presentacion.vista.VentanaPrincipal;
import presentacion.controlador.ControladorPrincipal;

public class Principal {
    public static void main(String[] args) {
        VentanaPrincipal vista = new VentanaPrincipal();
        ControladorPrincipal controlador = new ControladorPrincipal(vista);
        vista.setVisible(true);
    }
}
