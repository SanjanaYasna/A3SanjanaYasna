import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class GA_Simulation {
   /* int n = 100;
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

  //  public ArrayList<ArrayList<Character>> members;
    public ArrayList<Individual> members;
    public ArrayList<Individual> generationParents;
    public List<Integer> randomparents;

    private GA_Simulation(int n, int k, int r, int c_0, int c_max, double m){
        this.n = n;
        this.k = k;
        this.r = r;
        this.c_0 = c_0;
        this.c_max = c_max;
        this.m = m;
    }

    private void init(){
        members = new ArrayList<Individual>(n);
        for (int i = 0; i<this.n; i++){
            Individual individual = new Individual(8);
            members.add(individual);
        }
    }

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

    public void describeGeneration(){
      System.out.println("Fitness of first member is: " + members.get(1).getFitness());
      System.out.println("Fitness of the 15th member is: " + members.get(14).getFitness());
      System.out.println("Fitness of the last member is: " +members.get(99).getFitness());
    }


   /*Collections.sort(members, new Comparator<ArrayList<Character>>() {
      @Override
      public int compare(ArrayList<String> one, ArrayList<String> two) {
        return one.get(1).compareTo(two.get(1));
    }
});
    }*/

    public void evolve(){
      generationParents = new ArrayList<Individual>(members.subList(0, k));
     ArrayList<Individual> newMembers = new ArrayList<Individual>(n); //to eventually set equal to arraylist of members
      for (int i = 0; i < n; i++){ //have 2 individuals breed enough times to create n offspring for next generation
        List<Integer> pairing =  ThreadLocalRandom.current().ints(0, 15).limit(2).boxed().toList();
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
        GA_Simulation simulation = new GA_Simulation(100, 15, 100, 8, 20, 0.01);
        simulation.init(); //yep, makes members
        simulation.rankPopulation(simulation.members);
        //System.out.println(simulation.members); //yep, sticks to 100 of those, and it seems to be pretty correct!
        simulation.describeGeneration(); //mumbers descend, so looks good

        simulation.evolve();
        simulation.describeGeneration();
    }
}
