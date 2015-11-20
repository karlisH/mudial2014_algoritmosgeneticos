
package ec.unl.edu.ag;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.DefaultFitnessEvaluator;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
import org.jgap.impl.SwappingMutationOperator;

import ec.unl.edu.clases.Equipo;
import ec.unl.edu.operaciones.Operaciones;


public class TestEquipoFitness {

	private ArrayList<String> respuestaVista = new ArrayList<String>();
	
    public ArrayList<String> getRespuestaVista() {
		return respuestaVista;
	}

	public void setRespuestaVista(ArrayList<String> respuestaVista) {
		this.respuestaVista = respuestaVista;
	}

	private Configuration configuracion;
    private SwappingMutationOperator operadorMutacion;
    private EquipoFitnessFunction equipoFitness = null;
    public ArrayList<Equipo> equipos = new ArrayList<Equipo>();
    public List genes = new ArrayList();
    public ArrayList<String> listaRespuesta = new ArrayList<String>();
    Operaciones operaciones = new Operaciones();
    


    private static final int MAX_ALLOWED_EVOLUTIONS = 2000;
    private Chromosome equipoCromosoma = null;

    public void inicializar(String tipoPelicula) throws Exception {
        StringTokenizer st = new StringTokenizer(tipoPelicula);

        while (st.hasMoreElements()) {
            String gen = st.nextToken();
            genes.add(gen);
        }

        equipos = operaciones.recuperarEquipos();

        configuracion = new DefaultConfiguration();
        Configuration.resetProperty(Configuration.PROPERTY_FITEVAL_INST);
        configuracion.setFitnessEvaluator(new DefaultFitnessEvaluator());
        configuracion.getGeneticOperators().clear();

        operadorMutacion = new SwappingMutationOperator(configuracion);
        configuracion.addGeneticOperator(operadorMutacion);
        configuracion.setPreservFittestIndividual(true);
        configuracion.setPopulationSize(1000);
        configuracion.setKeepPopulationSizeConstant(false);

        equipoFitness = new EquipoFitnessFunction(equipos, genes);

        configuracion.setFitnessFunction(equipoFitness);

        Gene[] equiposGenes = new Gene[4];

        equiposGenes[0] = new IntegerGene(configuracion, 0, equipos.size() - 1);
        equiposGenes[1] = new IntegerGene(configuracion, 0, equipos.size() - 1);
        equiposGenes[2] = new IntegerGene(configuracion, 0, equipos.size() - 1);
        equiposGenes[3] = new IntegerGene(configuracion, 0, equipos.size() - 1);
        
        equipoCromosoma = new Chromosome(configuracion, equiposGenes);
        
        equiposGenes[0].setAllele(0);
        equiposGenes[1].setAllele(1);
        equiposGenes[2].setAllele(2);
        equiposGenes[3].setAllele(3);
        
        configuracion.setSampleChromosome(equipoCromosoma);

    }

 

    public void pruebaSeleccionarMejoresEquipos() throws Exception {

        equipos = operaciones.recuperarEquipos();
        //System.out.println(equipos);
        Genotype poblacion = Genotype.randomInitialGenotype(configuracion);

        IChromosome mejorSolucionHastaAqui = equipoCromosoma;

        for (int i = 0; i < MAX_ALLOWED_EVOLUTIONS; i++) {
            //evolucionar poblacion
        	poblacion.evolve();
            IChromosome candidatoAMejorSolucion = poblacion.getFittestChromosome();

            if (candidatoAMejorSolucion.getFitnessValue() > mejorSolucionHastaAqui.getFitnessValue()) {
                mejorSolucionHastaAqui = candidatoAMejorSolucion;
            }
        }
        this.setRespuestaVista(retornarSolucionFinal(mejorSolucionHastaAqui, equipos));
        
    }

    public ArrayList<String> retornarSolucionFinal(IChromosome solution, ArrayList<Equipo> equipos) {
        System.out.println("#################################################################################################################");
        
        System.out.println("Valor del Fitness: " + solution.getFitnessValue());
    
        for (int i = 0; i < solution.size(); i++) {
            int index = (Integer) solution.getGene(i).getAllele();
            Equipo equipo = (Equipo) equipos.get(index);
            listaRespuesta.add(equipo.getNombre().toString());
            System.out.println(equipo.getNombre().toString());
          
        }
        //JOptionPane.showMessageDialog(null, resultado);
        //System.out.println(resultado);
        
        System.out.println("#################################################################################################################");
    
    return listaRespuesta;
    }
}
