package ec.unl.edu.clases;

public class Equipo {

	String nombre;
	Integer ranking;
	Character grupo;
	Integer numero_copas;
	Integer antepenultimo_partido;
	Integer penultimo_partido;
	Integer ultimo_partido;
	
	public Equipo(){
		
	}
	
	public Equipo(String nombre, Integer ranking, Character grupo, Integer numero_copas,
			Integer antepenultimo_partido, Integer penultimo_partido,
			Integer ultimo_partido) {
		super();
		this.nombre = nombre;
		this.ranking = ranking;
		this.grupo = grupo;
		this.numero_copas = numero_copas;
		this.antepenultimo_partido = antepenultimo_partido;
		this.penultimo_partido = penultimo_partido;
		this.ultimo_partido = ultimo_partido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

	public Character getGrupo() {
		return grupo;
	}

	public void setGrupo(Character grupo) {
		this.grupo = grupo;
	}

	public Integer getNumero_copas() {
		return numero_copas;
	}

	public void setNumero_copas(Integer numero_copas) {
		this.numero_copas = numero_copas;
	}

	public Integer getAntepenultimo_partido() {
		return antepenultimo_partido;
	}

	public void setAntepenultimo_partido(Integer antepenultimo_partido) {
		this.antepenultimo_partido = antepenultimo_partido;
	}

	public Integer getPenultimo_partido() {
		return penultimo_partido;
	}

	public void setPenultimo_partido(Integer penultimo_partido) {
		this.penultimo_partido = penultimo_partido;
	}

	public Integer getUltimo_partido() {
		return ultimo_partido;
	}

	public void setUltimo_partido(Integer ultimo_partido) {
		this.ultimo_partido = ultimo_partido;
	}
	public String toString() {
        return this.getNombre();
    }
}
