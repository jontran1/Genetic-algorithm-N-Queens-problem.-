import java.util.*;


public class Chromosome implements Comparable <Chromosome> {
    private final List<Integer> sequence;
    private final int size;
    private Random random = new Random();


    /**
     * A new chromosome will be created with a permutation sequence
     * from 0,1,2....n-1.
     * @param size
     */
    Chromosome(int size){
        this.size = size;
        this.sequence = new ArrayList<>(size);
        generateSequence();
    }

    /**
     * A new chromosome will be created and its sequence will be
     * set to the list sequence argument passed as a parameter.
     * This is for testing.
     * @param sequence
     */
    Chromosome(List<Integer> sequence) {
        this.sequence = sequence;
        this.size = sequence.size();
    }

    /**
     * Returns the size of the chromosome's sequence
     * @return int size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns a reference to the list object sequence.
     * @return ArrayList sequence
     */
    public List<Integer> getSequence() {
        return sequence;
    }

    /**
     * This is called by the constructor to generate a permutation
     * sequence of the numbers 0,1,2....n-1
     */
    private void generateSequence(){
        for(int i = 0; i <getSize(); i++){
            sequence.add(i);
        }
        java.util.Collections.shuffle(sequence);
    }

    /**
     * Returns the index of the sequence
     * @param i
     * @return ArrayList sequence index
     * @throws IndexOutOfBoundsException
     */
    public int get(int i)throws IndexOutOfBoundsException{
        return sequence.get(i);
    }

    /**
     * Returns a string representation of the sequence
     * @return sequence string
     */
    @Override
    public String toString() {
        return sequence.toString();
    }
    public Object[] toArray(){
        return sequence.toArray();
    }

    /**
     * Uses parent 1 and parent 2's sequence to create a new child chromosome
     * that shares the attribute of both parents.
     * The child's sequence is always a permutation of the numbers
     * 0,1,2,....n-1
     * @param p1
     * @param p2
     * @return new Chromosome child
     */
    public static Chromosome createChild(Chromosome p1, Chromosome p2){
        List<Integer> childSequence = crossOver(p1,p2);
        return new Chromosome(childSequence);
    }

    /**
     * A static factory method for creating new Chromosomes.
     * @param size
     * @return new Chromosome with size = argument passed
     */
    public static Chromosome createChromosome(int size){
        return new Chromosome(size);
    }

    /**
     * A static factory method for testing purposes you can pass any List object and
     * a new chromosome will be created with its sequence set to the List argument passed.
     * @param sequence
     * @return new Chromosome
     */
    public static Chromosome createChromosome(List<Integer> sequence){
        return new Chromosome(sequence);
    }


    /**Testing mutation function don't use.
     * @param chromosomeSequence
     * @return
     */
    public static void mutateChromosomeSequence(List<Integer> chromosomeSequence){
        Random random = new Random();
        int i = random.nextInt(chromosomeSequence.size());
        int j = random.nextInt(chromosomeSequence.size());
        List<Integer> child;
        while (j == i){
            j = random.nextInt(chromosomeSequence.size());
        }
        Collections.swap(chromosomeSequence,i,j);
    }

    /**
     * Two random variables are generated. One mutation has an 80 percent chance and
     * the other has a 40 percent chance. If mutation is successful then mutationHelper function is called.
     * @param childSequence
     */
    private static void mutation(List<Integer> childSequence){
        Random random = new Random();
        int prob1 = random.nextInt(100);
        int prob2 = random.nextInt(100);

        if(prob1 <= 80){
            mutationHelper(childSequence);
        }
        if(prob2 <= 40){
            mutationHelper(childSequence);
        }

    }

    /**
     * Two indexes are randomly selected and swapped.
     * @param childSequence
     */
    private static void mutationHelper(List<Integer> childSequence){
        Random random = new Random();
        int i = random.nextInt(childSequence.size()/2);
        int j = i + random.nextInt(childSequence.size()-i);
        Collections.swap(childSequence,i,j);
    }
    /**
     * Calculates the chromosomes fitness level.
     * @param chromosome
     * @return
     */
    public static int getChromosomeFitness(Chromosome chromosome){
        int t1 = 0, t2 = 0;
        int size = chromosome.getSize();
        int f1 [] = new int[size];
        int f2 [] = new int[size];
        for(int i = 0; i < size; i++){
            f1[i] = (chromosome.get(i)-i);
            f2[i] = ((size)-chromosome.get(i)-i);
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

    /**
     * A static helper function is used in creating the child's sequence.
     * A new sequence will be generated uses both's parents attributes.
     * @param p1
     * @param p2
     * @return New List object containing attributes from both parents.
     */
    public static List<Integer> crossOver(Chromosome p1, Chromosome p2){
        Random random = new Random();
        int i = random.nextInt(p1.getSize());
        int j = random.nextInt(p1.getSize());
        List<Integer> child;
        while (j == i){
            j = random.nextInt(p1.getSize());
        }

        List<Integer> subList;
        if(i < j){
            subList = p1.getSequence().subList(i,j+1);
            child = createChildSequence(subList, p2.getSequence(), i);
        }else {
            subList = p1.getSequence().subList(j,i+1);
            child = createChildSequence(subList, p2.getSequence(), j);
        }
        mutation(child);
        return child;
    }

    /**
     * A static helper function is used in creating the child's sequence.
     * The subList is the randomly chosen attribute from parent 1. Parent 2's
     * attributes will be combined with the sublist to create the new child's sequence.
     * If an element of parent 2's sequence is contained in the sublist then it wont be used.
     * This is to maintain that the sequence will always be a permutation of the numbers 0,1,2,...n-1.
     * @param subList
     * @param p2
     * @param i
     * @return new List object containing the child's sequence.
     */
    private static List<Integer> createChildSequence(List<Integer> subList, List<Integer> p2, int i){
        List<Integer> child = new ArrayList<>(p2.size());
        for (int k = 0; k < p2.size(); k++){
            if (k == i){
                child.addAll(subList);
            }
            if (!subList.contains(p2.get(k))){
                child.add(p2.get(k));
            }
        }
        return child;
    }

    /**
     * Sorting the chromosomes by fitness level.
     * @param chromosome
     * @return
     */
    @Override
    public int compareTo(Chromosome chromosome) {
        int fitness = getChromosomeFitness(chromosome);
        return getChromosomeFitness(this) - fitness;
    }
}
