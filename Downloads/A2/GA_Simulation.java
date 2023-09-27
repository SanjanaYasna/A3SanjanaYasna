import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class GA_Simulation {
   /* int n = 100; //just a reminder of what maps out ot what
    int k = 15;
    int r = 100;
    int c_0 = 8;
    int c_max = 20;
    double m = 0.01;*/
    int n;
    int k;
    int r;
    int c_0;
    int c_max;
    double m;
    int g;

    public ArrayList<Individual> members; //arraylist of current members of generation (100 total)
    public ArrayList<Individual> generationParents; //arraylist for top 15 most fit membesr after members arraylist gest sorted
    public List<Integer> randomparents; //creates random indices for which parent pairs with which parent from generationParents

    //GA_SIMLUATION INIT CONSTRUCTOR
    private GA_Simulation(int n, int k, int r, int c_0, int c_max, double m, int g){
        this.n = n;
        this.k = k;
        this.r = r;
        this.c_0 = c_0;
        this.c_max = c_max;
        this.m = m;
        this.g = g;
    }

    //INITIALIZES POPULATION OF DESIRED SIZE N AND CREATES MEMBER ARRAYLIST OF N INDIVIDUALS (FROM INDIVIDUAL CLASS)
    private void init(){
        members = new ArrayList<Individual>(n);
        for (int i = 0; i<this.n; i++){
            Individual individual = new Individual(8, 4);
            members.add(individual);
        }
    }

    //RANKS MEMBER FITNESS FROM GETFITNESS METHOD FROM INDIVIDUAL
    public void rankPopulation(ArrayList<Individual> pop) {
      // sort population by fitness
      Comparator<Individual> ranker = new Comparator<>() {
        // this order will sort higher scores at the front
        public int compare(Individual c1, Individual c2) {
          return (int)Math.signum(c2.getFitness() -c1.getFitness());
        }
      };
      pop.sort(ranker); 
    }

    //PRINTS OUT DESIRED METRICS FOR EACH ROUND
    public void describeGeneration(){
      System.out.println("Fitness of first/top individual is: " + members.get(0).getFitness());
      System.out.println("Fitness of the " + k + "th individual is: " + members.get(k-1).getFitness());
      System.out.println("Fitness of the last/least fit individual is: " +members.get(n-1).getFitness());
    }

    //TAKES TWO INDIVIDUAL PARENTS FROM GENERATIONPARENTS ARRAYLIST(TOP 15 MOST FIT MEMBERS) AND PAIRS THEM UP RANDOMLY TO BREED USING INDIVIDUAL CONSTRUCTOR
    public void evolve(){
      generationParents = new ArrayList<Individual>(members.subList(0, k));
     ArrayList<Individual> newMembers = new ArrayList<Individual>(n); //to eventually set equal to arraylist of members
      for (int i = 0; i < n; i++){ //have 2 individuals breed n times to create n offspring for next generation
        List<Integer> pairing =  ThreadLocalRandom.current().ints(0, k).limit(2).boxed().toList();
       //create filler individual arraylists that get from teh members arraylist
        ArrayList<Individual> indv1 = new ArrayList<Individual>();
        ArrayList<Individual> indv2 = new ArrayList<Individual>();
        indv1.add(generationParents.get(pairing.get(0)));
        indv2.add(generationParents.get(pairing.get(1)));
        Individual offspring = new Individual(indv1, indv2, m, c_max);
        newMembers.add(offspring);
      }
      members = newMembers;
      //members now ponts to this new set of members/offspring 
    }

    //RUNS SIMULATION R TIMES AS SPECIFIED
    public void run(GA_Simulation s){
      s.init();
      s.rankPopulation(s.members);
      s.describeGeneration();
      for (int i = 0; i <r; i ++){
        s.evolve();
        s.rankPopulation(s.members);
        s.describeGeneration();
      }

    }

    public static void main(String[] args){
      GA_Simulation simulation = new GA_Simulation(100, 15, 100, 8, 20, 0.01, 4);
      simulation.run(simulation);

       /* I played around with this a lot, so I'll spare you a bunch of commented test cases and just show you my most interesting case: 
      GA_Simulation simulation1 = new GA_Simulation(100, 3, 5, 8, 20, 0.01, 4);
      simulation.run(simulation1);
      Even with only 10 rounds, if you are more selective about the kind of individual that can breed, you get perfect palindromes very fast.
      Mutations also mess things up a lot, ofc */
      //GA_Simulation simulation1 = new GA_Simulation(100, 2, 5, 8, 40, 0.1, 4);
     // simulation.run(simulation1);
    }
}
