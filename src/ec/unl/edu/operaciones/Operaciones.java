package ec.unl.edu.operaciones;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import sun.misc.Sort;
import ec.unl.edu.clases.Equipo;

public class Operaciones {

	public String recuperarDirectorio() {
		String directorio = System.getProperty("user.dir");
		return directorio;
	}

	public Equipo seleccionarEquipo(int indice, ArrayList<Equipo> lista_equipo) {
		Equipo equipo = lista_equipo.get(indice);
		return equipo;
	}

	public ArrayList<Equipo> recuperarEquipos() {

		ArrayList<Equipo> lista_equipos = new ArrayList<Equipo>();

		String textoPregunta;
		String[] arreglo_atributos_equipo = new String[100];

		try {
			File archivoEquipos = new File(recuperarDirectorio()
					+ "/src/main/resources/mundial.txt");
			BufferedReader br = new BufferedReader(new FileReader(
					archivoEquipos));

			if (archivoEquipos.exists()) {
				while ((textoPregunta = br.readLine()) != null) {

					// lee, recorta y almacena los atributos de cada equipo
					textoPregunta.endsWith("\n");
					arreglo_atributos_equipo = textoPregunta.split(",");

					lista_equipos.add(new Equipo(arreglo_atributos_equipo[0],
							Integer.parseInt(arreglo_atributos_equipo[1]),
							arreglo_atributos_equipo[2].charAt(0), Integer
									.parseInt(arreglo_atributos_equipo[3]),
							Integer.parseInt(arreglo_atributos_equipo[4]),
							Integer.parseInt(arreglo_atributos_equipo[5]),
							Integer.parseInt(arreglo_atributos_equipo[6])));

				}

				br.close();
			}

		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "Error al leer el archivo");
		}
		return lista_equipos;
	}

	public Integer[] listarRankingsFifa() {

		ArrayList<Equipo> listaEquipos = this.recuperarEquipos();
		
		Integer[] numeros = new Integer[listaEquipos.size()];

		for (int i = 0; i < listaEquipos.size(); i++) {
			numeros[i] = listaEquipos.get(i).getRanking();
		}
		//System.out.print(numeros.length);
		Arrays.sort(numeros);
		//for (int i = 0; i < numeros.length; i++) {
			//System.out.println(numeros[i]);
		//}
		
		return numeros;
	}

	public void escribirEquiposModificados(Integer index, Equipo equipo,
			ArrayList<Equipo> listaEquipos) {
		
		File archivoEquipos = new File(recuperarDirectorio()
				+ "/src/main/resources/mundial.txt");

		// actualizamos valores nuevos
		listaEquipos.set(index, equipo);

		try {
			FileWriter w = new FileWriter(archivoEquipos);
			BufferedWriter bw = new BufferedWriter(w);
			PrintWriter wr = new PrintWriter(bw);
			for (int i = 0; i < listaEquipos.size(); i++) {

				// en caso de ser la ultima linea no agregar salto de linea
				if (i == listaEquipos.size() - 1) {

					wr.write(listaEquipos.get(i).getNombre() + ","
							+ listaEquipos.get(i).getRanking() + ","
							+ listaEquipos.get(i).getGrupo() + ","
							+ listaEquipos.get(i).getNumero_copas() + ","
							+ listaEquipos.get(i).getAntepenultimo_partido()
							+ "," + listaEquipos.get(i).getPenultimo_partido()
							+ "," + listaEquipos.get(i).getUltimo_partido());

				} else {
					wr.write(listaEquipos.get(i).getNombre() + ","
							+ listaEquipos.get(i).getRanking() + ","
							+ listaEquipos.get(i).getGrupo() + ","
							+ listaEquipos.get(i).getNumero_copas() + ","
							+ listaEquipos.get(i).getAntepenultimo_partido()
							+ "," + listaEquipos.get(i).getPenultimo_partido()
							+ "," + listaEquipos.get(i).getUltimo_partido()
							+ "\n");
				}
			}
			wr.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<String> listaGrupos(String grupo) {
		ArrayList<String> gruposSalida = new ArrayList<String>();
		ArrayList<String> grupos = new ArrayList<String>();
		grupos.add("A");
		grupos.add("B");
		grupos.add("C");
		grupos.add("D");
		grupos.add("E");
		grupos.add("F");
		grupos.add("G");
		grupos.add("H");

		for (int i = 0; i < grupos.size(); i++) {
			if (!grupo.equals(grupos.get(i))) {
				gruposSalida.add(grupos.get(i));
			}
		}

		return gruposSalida;

	}
}
