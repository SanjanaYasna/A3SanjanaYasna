import java.util.concurrent.ThreadLocalRandom;
import java.util.ArrayList;
import java.util.Arrays;

public class Individual{
    public  ArrayList<Character> chromosome = new ArrayList<Character>(); //individual's chromosomes
    //population's chromosomes, 100 intitially
    public ArrayList<ArrayList<Character>> memberChromosomes = new ArrayList<ArrayList<Character>>(100); 
    public  ArrayList<Character> offspringChromosome;
    public ArrayList<ArrayList<Character>> offspringChromosomes = new ArrayList<ArrayList<Character>>(); 

    //generate an individual's 8 character chromosome
    Individual(int size){
      chromosome = new ArrayList<Character>(size);
      for (int i = 0; i < size; i++){
        Character ch = randomLetter(4);
        chromosome.add(ch);
      }

     // System.out.println(chromosome);
    }
    //generate initialize population of cergtani size, and each member of population has 8 chromosome letters
    //misread instructions, this isnt' needed
   /*  private Individual(int population){
      for (int member = 0; member < population; member++){
        memberChromosomes.add(generateChromosome(8));
      }
      System.out.println(memberChromosomes);
    }*/

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
    //make offspring
//Test
    Individual(ArrayList<Individual> parent1, ArrayList<Individual> parent2, double mutationRate, int c_max){
      int randomLength1 = ThreadLocalRandom.current().nextInt(1, parent1.toString().length());
      int randomLength2 = ThreadLocalRandom.current().nextInt(1, parent2.toString().length());
      System.out.println(randomLength1);
      System.out.println(randomLength2);
      for (int i = 1; i < randomLength1; i++){
        Character ch = parent1.toString().charAt(i);
        chromosome.add(ch);
      }
      for (int i = 1; i < randomLength2; i++){
        chromosome.add(parent2.toString().charAt(i));
      }
      if (chromosome.size() > c_max){
        chromosome = new ArrayList<Character>(chromosome.subList(0, c_max));
      }
      for (int i = 0; i < chromosome.size(); i ++){
        if (ThreadLocalRandom.current().nextDouble() < mutationRate){
          chromosome.set(i, randomLetter(4));
        }
      }
      System.out.println(chromosome);
    
    }

      /*for (int i = 0; i < randomLength1; i++){
        Character ch = parent1.chromosome.get(i);
        chromosome.add(ch);
      }
      for (int i = 0; i < randomLength2; i++){
        chromosome.add(parent2.get(i));
      }
      if (chromosome.size() > c_max){
        chromosome = new ArrayList<Character>(chromosome.subList(0, c_max));
      }
      for (int i = 0; i < chromosome.size(); i ++){
        if (ThreadLocalRandom.current().nextDouble() < mutationRate){
          chromosome.set(i, randomLetter(4));
        }
      }
      System.out.println(chromosome);
    }

    private Individual(ArrayList<Character> parent1, ArrayList<Character> parent2, double mutationRate, int c_max){
      int randomLength1 = ThreadLocalRandom.current().nextInt(1, parent1.size());
      int randomLength2 = ThreadLocalRandom.current().nextInt(1, parent2.size());

      for (int i = 0; i < randomLength1; i++){
        Character ch = parent1.get(i);
        chromosome.add(ch);
      }
      for (int i = 0; i < randomLength2; i++){
        chromosome.add(parent2.get(i));
      }
      if (chromosome.size() > c_max){
        chromosome = new ArrayList<Character>(chromosome.subList(0, c_max));
      }
      for (int i = 0; i < chromosome.size(); i ++){
        if (ThreadLocalRandom.current().nextDouble() < mutationRate){
          chromosome.set(i, randomLetter(4));
        }
      }
      System.out.println(chromosome);
    }*/

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

    private Character randomLetter(int num_dna_letters) {
        return Character.valueOf((char)(65+ThreadLocalRandom.current().nextInt(num_dna_letters)));
    }

    public static void main(String[] args){
        //System.out.println(individual.generateChromosome(10)); generateChromosome looks normal
       //System.out.println(individual.chromosome.toString()); chromosome  does display with toString, but you can also just print individual.chromosome
       /*ArrayList<Character> parent1 = new ArrayList<Character>(
            Arrays.asList('A',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D',
                          'B',
                          'C',
                          'A',
                          'D'
                          ));
      ArrayList<Character> parent2 = new ArrayList<Character>(
            Arrays.asList('D',
                          'B',
                          'B',
                          'D',
                          'C'));

      Individual individual = new Individual(Individual parent1.chromsome, Individual parent2, 0.01, 20); //yep, stops at 20 
      System.out.println(individual.chromosome.size()); //yep, always less than or equal to 20
       //individual.Individual(individual.memberChromosomes.get(1), individual.memberChromosomes.get(2));
      System.out.println(parent1);
      System.out.println(individual.getFitness());

      */
      Individual indv1 = new Individual(8);
      Individual indv2 = new Individual(8);
      
    }


}