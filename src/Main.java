import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int populationSize = 1000;
        int chromosomeSize = 10;
        int selectionFactor = 5;
        runSimulation(chromosomeSize, populationSize, selectionFactor);

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

    /**
     * Will randomly generate a number based on the population size, selection factor.
     * Has a high probability of selecting a relatively low number to the population size.
     * @param populationSize
     * @param selectionFactor
     * @return
     */
    public static int selectionOperator(int populationSize, int selectionFactor){
        double rand = Math.random();
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

    /**
     * A random population will be generated and then sorted by fitness. The lower the fitness the better.
     * An empty new population will created.
     * The selection operator will randomly select chromosomes from the current population. Because the population is already
     * sorted by fitness. The selection operator has a high chance of selecting very fit chromosomes for breeding.
     * Once two chromosomes are selected. They will create two child via crossover and mutation functions.
     * The two parents and two child are then added to the next population and this process repeats until the next
     * population size grows to the max populationSize. Once that is finished, the next population is then sorted by fitness and
     * it becomes the new current population. The entire process is repeated until the chromosome at population index 0 returns a
     * fitness level of 0, thus indicating that a solution was found.
     * @param chromosomeSize
     * @param populationSize
     * @param selectionFactor
     */
    public static void runSimulation(int chromosomeSize, int populationSize, int selectionFactor){
        int randomIndex1;
        int randomIndex2;
        Chromosome parent1;
        Chromosome parent2;
        Chromosome child1;
        Chromosome child2;
        List<Chromosome> population = generatePopulation(populationSize, chromosomeSize);
        Collections.sort(population);
        int generation = 0;
        while (Chromosome.getChromosomeFitness(population.get(0)) != 0){
            List<Chromosome> nextPopulation = new ArrayList<>(populationSize);

            while (nextPopulation.size() < populationSize){
                randomIndex1 = selectionOperator(populationSize,selectionFactor);
                randomIndex2 = selectionOperator(populationSize,selectionFactor);

                while (randomIndex2 == randomIndex1){
                    randomIndex1 = selectionOperator(populationSize,selectionFactor);
                    randomIndex2 = selectionOperator(populationSize,selectionFactor);
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
            generation++;

        }
        System.out.println("Population :" + population);
        System.out.println(getBoard(chromosomeSize,chromosomeSize,population.get(0).getSequence()));
        System.out.println("Fitness level: " + Chromosome.getChromosomeFitness(population.get(0)));
        System.out.println("It took " + generation + " generations");

    }



}
