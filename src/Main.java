import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int size = 4;
        int populationSize = 10;
        List<Chromosome> population = new ArrayList<>(populationSize);
        for (int i = 0 ; i < 5; i ++){
            population.add(Chromosome.createChromosome(size));
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
        Chromosome p1 = Chromosome.createChromosome(l1.size());
        Chromosome p2 = Chromosome.createChromosome(l2.size());
        p1.setSequence(l1);
        p2.setSequence(l2);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(crossOver(p1,p2));
        System.out.println(Chromosome.createChild(crossOver(p1,p2)));


    }

    public static List<Integer> crossOver(Chromosome p1, Chromosome p2){
        Random random = new Random();
        int i = random.nextInt(p1.getSize());
        int j;
        List<Integer> child;

        while (true){
            j = random.nextInt(p1.getSize());
            if (j!=i)
                break;
        }
        i=2;
        j=5;
        List<Integer> subSet;
        if(i < j){
            subSet = getSubSet(i, j, p1.getSequence());
            child = createChildSequence(subSet, p2.getSequence(), i);
        }else {
            subSet = getSubSet(j, i, p1.getSequence());
            child = createChildSequence(subSet, p2.getSequence(), j);
        }
        return child;
    }

    private static List<Integer> createChildSequence(List<Integer> subSet, List<Integer> p2, int i){
        List<Integer> child = new ArrayList<>(p2.size());
        int k = 0;
        for (int j = 0;  j < p2.size(); j++){
            if (k == i){
                child.addAll(subSet);
            }
            if (!subSet.contains(p2.get(j))){
                child.add(p2.get(j));
                k++;
            }
        }
        return child;
    }
    private static List<Integer> getSubSet(int i, int j, List list){
        return list.subList(i,j+1);
    }


    public static int getFitness(Chromosome chromosome){
        int t1 = 0, t2 = 0;
        int size = chromosome.getSize();
        int f1 [] = new int[size];
        int f2 [] = new int[size];
        for(int i = 0; i < size; i++){
            f1[i] = (chromosome.get(i)-i);
            f2[i] = ((size+1)-chromosome.get(i)-i);
        }
        Arrays.sort(f1);
        Arrays.sort(f2);
        for (int i = 1; i < size; i++){
            if(f1[i] == f1[i-1]){
                t1 = t1 + 1;
            }
            if (f2[i] == f2[i-1]){
                t2 = t2 + 1;
            }
        }
        return t1 + t2;
    }


}
