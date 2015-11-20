/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.unl.edu.ag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
import org.jgap.impl.IntegerGene;

import ec.unl.edu.clases.Equipo;

public class EquipoFitnessFunction extends FitnessFunction {

	ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	List genes = new ArrayList();

	public EquipoFitnessFunction(ArrayList<Equipo> equipos, List genes) {
		this.equipos = equipos;
		this.genes = genes;
	}

	@Override
	protected double evaluate(IChromosome icromosoma) {
		double puntuacion = 0;
		// double imdbScore = 0;

		List dups = new ArrayList();
		int malaSolucion = 1;

		for (int i = 0; i < icromosoma.size(); i++) {

			// IntegerGene un_gen = (IntegerGene) icromosoma.getGene(i);

			int indice = (Integer) icromosoma.getGene(i).getAllele();

			if (dups.contains(indice)) {
				malaSolucion = 0;
			} else {
				dups.add(indice);
			}

			Equipo equipo = equipos.get(indice);

			double puntuacionGenes = this.obtenerPuntuacionGenes(equipo);

			if (puntuacionGenes == 0) {
				malaSolucion = 0;
			}

			double resultado1 = equipo.getAntepenultimo_partido();
			double resultado2 = equipo.getPenultimo_partido();
			double resultado3 = equipo.getUltimo_partido();

			puntuacion = (puntuacion + resultado1 + resultado2 + resultado3 + puntuacionGenes);

		}
		return (puntuacion * malaSolucion);

	}

	private int obtenerPuntuacionGenes(Equipo equipo) {
		int _copas = 0;
		int _ranking = 0;
		int _partido_antepenultimo = 0;
		int _partido_penultimo = 0;
		int _partido_ultimo = 0;
		int total = 0;


		int numero_copas = equipo.getNumero_copas();
		int ranking_fifa = equipo.getRanking();
		int antepenultimo = equipo.getAntepenultimo_partido();
		int penultimo = equipo.getPenultimo_partido();
		int ultimo = equipo.getUltimo_partido();

		if (antepenultimo == 1) {

			_partido_antepenultimo = 1;
		}
		if (antepenultimo == 0) {

			_partido_antepenultimo =  0;

		}
		if (antepenultimo == -1) {

			_partido_antepenultimo = -1;
		}
		if (penultimo == 1) {
			_partido_penultimo = 1;
		}
		if (penultimo == 0) {
			_partido_penultimo = 0;
		}
		if (penultimo == -1) {
			_partido_penultimo = -1;
		}
		if (ultimo == 1) {
			_partido_ultimo = 1;
		}
		if (ultimo == 0) {
			_partido_ultimo = 0;
		}
		if (ultimo == -1) {
			_partido_ultimo = -1;
		}
		
		/*if (numero_copas > 0) {
			_copas = numero_copas;
		}
		if (numero_copas == 0) {
			_copas = 0;
		}*/

		if ((ranking_fifa >= 1) && (ranking_fifa <= 10)) {
			_ranking = 12;
		}

		if ((ranking_fifa >= 11) && (ranking_fifa <= 20)) {
			_ranking = 11;
		}

		if ((ranking_fifa >= 21) && (ranking_fifa <= 30)) {
			_ranking = 10;
		}

		if ((ranking_fifa >= 31) && ((ranking_fifa) <= 40)) {
			_ranking = 9;
		}

		if ((ranking_fifa >= 41) && (ranking_fifa <= 50)) {
			_ranking = 8;
		}

		if ((ranking_fifa >= 51) && (ranking_fifa <= 60)) {
			_ranking = 7;
		}
		
		if ((ranking_fifa >= 61) && (ranking_fifa <= 70)) {
			_ranking = 6;
		}
		
		if ((ranking_fifa >= 71) && (ranking_fifa <= 80)) {
			_ranking = 5;
		}
		
		if ((ranking_fifa >= 81) && (ranking_fifa <= 90)) {
			_ranking = 4;
		}
		
		if ((ranking_fifa >= 91) && (ranking_fifa <= 100)) {
			_ranking = 3;
		}
		
		if ((ranking_fifa >= 101) && (ranking_fifa <= 110)) {
			_ranking = 2;
		}
		if ((ranking_fifa >= 111)) {
			_ranking = 1;
		}

		total = numero_copas + _ranking + _partido_antepenultimo + _partido_penultimo
				+ _partido_ultimo;

		return total;
	}
}
