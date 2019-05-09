import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Chromosome> population = generatePopulation(4, 4);
        System.out.println(population);

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l1.add(5);l2.add(1);
        l1.add(2);l2.add(8);
        l1.add(3);l2.add(6);
        l1.add(1);l2.add(4);
        l1.add(6);l2.add(7);
        l1.add(4);l2.add(5);
        l1.add(8);l2.add(3);
        l1.add(7);l2.add(2);
        Chromosome p1 = Chromosome.createChromosome(l1);
        Chromosome p2 = Chromosome.createChromosome(l2);
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(Chromosome.createChild(p1,p2));

        List<Integer> l3 = new ArrayList<>();
        l3.add(4);
        l3.add(7);
        l3.add(3);
        l3.add(0);
        l3.add(6);
        l3.add(1);
        l3.add(5);
        l3.add(2);
        Chromosome c1 = new Chromosome(l3);

        System.out.println(Chromosome.getChromosomeFitness(c1));

    }

    /**
     * Returns a new population of a specific population size and a
     * specific chromosome size.
     * @param populationSize
     * @param chromosomeSize
     * @return
     */
    public static List<Chromosome> generatePopulation (int populationSize, int chromosomeSize){
        List<Chromosome> population = new ArrayList<>(populationSize);
        for (int i = 0; i < populationSize; i ++){
            population.add(new Chromosome(chromosomeSize));
        }
        return population;
    }




}
