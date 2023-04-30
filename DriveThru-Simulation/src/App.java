import java.util.Scanner;
import org.apache.commons.math3.distribution.PoissonDistribution;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);


        System.out.println("Welcome to the KFC Drive Thru Simulation!");
        System.out.println("This is a discrete event simulation of a KFC Drive Thru.");
        System.out.println("First, we will set the simulation parameters.");

        System.out.println("How long do you want to run the simulation? Please enter time in seconds.");
        int simulationTime = input.nextInt();

        System.out.println("Please enter the average arrival rate of customers per minute.");
        int arrivalRate = input.nextInt();

        PoissonDistribution poisson = new PoissonDistribution(1 / arrivalRate);
        
        

    }
}
