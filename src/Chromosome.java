import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class Chromosome {
    private List<Integer> sequence;
    private int size;

    private Chromosome(int size){
        this.size = size;
        sequence = new ArrayList<>(size);
        generateSequence();
    }
    private Chromosome(int size, List sequence){
        this.size = size;
        this.sequence = sequence;
    }

    public void setSequence(List<Integer> sequence) {
        this.size = sequence.size();
        this.sequence = sequence;
    }

    public int getSize() {
        return size;
    }

    public List<Integer> getSequence() {
        return sequence;
    }

    private void generateSequence(){
        for(int i = 0; i < size; i++){
            sequence.add(i);
        }
        java.util.Collections.shuffle(sequence);
    }

    public int get(int i)throws IndexOutOfBoundsException{
        return sequence.get(i);
    }

    @Override
    public String toString() {
        return sequence.toString();
    }

    public static Chromosome createChild(List<Integer> sequence){
        return new Chromosome(sequence.size(), sequence);
    }

    public static Chromosome createChromosome(int size){
        return new Chromosome(size);
    }

}
