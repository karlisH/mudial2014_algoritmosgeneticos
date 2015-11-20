package ec.unl.edu.main;

import java.awt.EventQueue;
import java.util.ArrayList;

import ec.unl.edu.clases.Equipo;
import ec.unl.edu.operaciones.Operaciones;
import ec.unl.edu.visual.VentanaPrincipal;

public class Run {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					
			} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
