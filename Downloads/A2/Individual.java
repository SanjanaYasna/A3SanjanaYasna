import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;

public class Individual{
    public  ArrayList<Character> chromosome = new ArrayList<Character>(); //individual's chromosomes
    //population's chromosomes, 100 intitially
    public ArrayList<ArrayList<Character>> memberChromosomes = new ArrayList<ArrayList<Character>>(100); 
    public  ArrayList<Character> offspringChromosome;
    public ArrayList<ArrayList<Character>> offspringChromosomes = new ArrayList<ArrayList<Character>>(); 


    //INDIVIDUAL INITIAL CONSTRUCTOR
    //generate an individual's 8 character chromosome
    Individual(int c_0, int g){
      chromosome = new ArrayList<Character>(c_0);
      for (int i = 0; i < c_0; i++){
        Character ch = randomLetter(g);
        chromosome.add(ch);
      }
    }
    //INDIVIDUAL TWO PARENT CONSTRUCTOR
    //to make offspring
    Individual(ArrayList<Individual> parent1, ArrayList<Individual> parent2, double mutationRate, int c_max){
      int randomLength1 = ThreadLocalRandom.current().nextInt(1, parent1.toString().length());
      int randomLength2 = ThreadLocalRandom.current().nextInt(1, parent2.toString().length());
      for (int i = 1; i < randomLength1; i++){
        Character ch = parent1.toString().charAt(i);
        chromosome.add(ch);
      }
      for (int i = 1; i < randomLength2; i++){
        chromosome.add(parent2.toString().charAt(i));
      }
      //truncating (well, not quite but ya get what i mean) if over c_max 
      if (chromosome.size() > c_max){
        chromosome = new ArrayList<Character>(chromosome.subList(0, c_max));
      }
      //mutations
      for (int i = 0; i < chromosome.size(); i ++){
        if (ThreadLocalRandom.current().nextDouble() < mutationRate){
          chromosome.set(i, randomLetter(4));
        }
      }
    }

    //COMPUTE FITNESS
    public int getFitness(){
      ArrayList<Character> input = chromosome; 
      int leftPointer = 0;
      int rightPointer = input.size() -1;
      int fitness = 0; //assuming fitness starts at 0;
      if (input.size() % 2 != 0){
        fitness ++;  //if chromosome size odd, then by default middle letter maps onto itself and fitness +1
      }
      while (leftPointer < rightPointer){
        if (input.get(leftPointer) == input.get(rightPointer)){
          fitness ++;
        }
        leftPointer ++;
        rightPointer--;
      }
      return fitness;
    }


     /** Expresses the individual's chromosome 
     *  as a String, for display purposes */
    public String toString() {
      StringBuilder builder = new StringBuilder(chromosome.size());
      for(Character ch: chromosome) {
        builder.append(ch);
      } 
      return builder.toString();
      //return chromosome.stream().map(e->e.toString()).collect(Collectors.joining());
    }
      //GENERATES RANDOM CHARRACTERS FOR MAKING INDIVIDUAL CHROMOSOME SEQUENCE
    private Character randomLetter(int num_dna_letters) {
        return Character.valueOf((char)(65+ThreadLocalRandom.current().nextInt(num_dna_letters)));
    }



}