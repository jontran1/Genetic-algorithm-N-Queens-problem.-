import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int populationSize = 1000;
        int chromosomeSize = 8;
        int selectionFactor = 5;
        double rand = Math.random();
        int randomIndex1 = selectionOperator(populationSize,rand,selectionFactor);
        rand = Math.random();
        int randomIndex2 = selectionOperator(populationSize,rand,selectionFactor);
        Chromosome parent1;
        Chromosome parent2;
        Chromosome child1;
        Chromosome child2;
        List<Chromosome> population = generatePopulation(populationSize, chromosomeSize);
        Collections.sort(population);
        while (Chromosome.getChromosomeFitness(population.get(0)) != 0){
            List<Chromosome> nextPopulation = new ArrayList<>(populationSize);
            while (nextPopulation.size() < populationSize){
                while (randomIndex2 == randomIndex1){
                    rand = Math.random();
                    randomIndex2 = selectionOperator(populationSize,rand,selectionFactor);

                }
                parent1 = population.get(randomIndex1);
                parent2 = population.get(randomIndex2);
                child1 = Chromosome.createChild(parent1, parent2);
                child2 = Chromosome.createChild(parent2, parent1);
                nextPopulation.add(parent1);
                nextPopulation.add(parent2);
                nextPopulation.add(child1);
                nextPopulation.add(child2);

            }
            population = nextPopulation;
            Collections.sort(population);
            System.out.println(Chromosome.getChromosomeFitness(population.get(0)));

        }
        System.out.println(population);
        System.out.println(Chromosome.getChromosomeFitness(population.get(0)));
        System.out.println(getBoard(chromosomeSize,chromosomeSize,population.get(0).getSequence()));



//
//        List<Integer> l1 = new ArrayList<>();
//        List<Integer> l2 = new ArrayList<>();
//        l1.add(5);l2.add(1);
//        l1.add(2);l2.add(8);
//        l1.add(3);l2.add(6);
//        l1.add(1);l2.add(4);
//        l1.add(6);l2.add(7);
//        l1.add(4);l2.add(5);
//        l1.add(8);l2.add(3);
//        l1.add(7);l2.add(2);
//        Chromosome p1 = Chromosome.createChromosome(l1);
//        Chromosome p2 = Chromosome.createChromosome(l2);
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(Chromosome.createChild(p1,p2));
//
//        List<Integer> l3 = new ArrayList<>();
//        l3.add(4);
//        l3.add(7);
//        l3.add(3);
//        l3.add(0);
//        l3.add(6);
//        l3.add(1);
//        l3.add(5);
//        l3.add(2);
//        Chromosome c1 = new Chromosome(l3);
//
//        System.out.println(Chromosome.getChromosomeFitness(c1));



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

    public static int selectionOperator(int populationSize, double rand, int selectionFactor){
        return (int) (populationSize * Math.pow(rand, selectionFactor));
    }

    public static String getBoard(int rows, int columns, List chessBoard){
        char[][] board = new char[rows][columns];
        for (int i = 0; i < chessBoard.size(); i ++){
            board[(int)chessBoard.get(i)][i] = 'Q';
        }
        StringBuilder stringBuilderBoard = new StringBuilder(rows * columns);
        for (int i = 0; i < rows; i++ ){
            stringBuilderBoard.append("{");
            for (int j = 0; j < columns ; j++){
                if(board[i][j] == 'Q'){
                    stringBuilderBoard.append(board[i][j]);
                }else {
                    stringBuilderBoard.append(" ");
                }
                if(j != columns-1){
                    stringBuilderBoard.append(",");
                }
            }
            stringBuilderBoard.append("}");
            stringBuilderBoard.append("\n");
        }
        return stringBuilderBoard.toString();
    }



}
