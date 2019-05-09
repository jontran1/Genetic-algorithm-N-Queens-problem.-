import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int size = 4;
        int populationSize = 10;
        List<Chromosome> population = new ArrayList<>(populationSize);
        for (int i = 0 ; i < 5; i ++){
            population.add(new Chromosome(size));
        }
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




    }




}
