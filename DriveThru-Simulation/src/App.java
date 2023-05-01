import java.util.Scanner;
import org.apache.commons.math3.distribution.PoissonDistribution;
import java.util.LinkedList;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner input = new Scanner(System.in);
        LinkedList<Server> servers = new LinkedList<Server>();

        System.out.println("Welcome to the KFC Drive Thru Simulation!");
        System.out.println("This is a discrete event simulation of a KFC Drive Thru.");
        System.out.println("First, we will set the simulation parameters.");

        System.out.println("How long do you want to run the simulation? Please enter time in seconds.");
        float simulationTime = input.nextInt();

        System.out.println("Please enter the average arrival rate of customers per second (Estimated guess: 0.03-0.08).");
        float arrivalRate = input.nextFloat();

        //System.out.println("Please enter the number of servers");
        //int numberOfServers = input.nextInt();
        int numberOfServers = 4;


        // for (int i = 0; i < numberOfServers; i++)
        // {
        //     Server server = new Server(i, "KFC Employee + i", false);
        //     servers.add(server);
        // }

        Server server1 = new Server(1, "Mudassir", false, "Order Taker", 25);
        Server server2 = new Server(2, "Noor", false, "Chef", 180);
        Server server3 = new Server(3, "Hamza", false, "Cashier", 30);
        Server server4 = new Server(4, "Ali", false, "Order Handoff", 15);

        servers.add(server1);
        servers.add(server2);
        servers.add(server3);
        servers.add(server4);

        Simulation simulation = new Simulation(simulationTime, arrivalRate, servers);

        LinkedList<Event> eventList;
        eventList = simulation.startSimulation();
        
        System.out.println("     Event ID\t Case ID\t Time Stamp\t Customer ID\t    Server ID\t            Activity\t");
        for (int i = 0; i < eventList.size(); i++)
        {
            if (eventList.get(i).getTimestamp() < 10)
            {
                System.out.println("\t" + eventList.get(i).getEventID() + "\t     " + eventList.get(i).getCaseID() + "\t            " + eventList.get(i).getTimestamp() + "\t               " + eventList.get(i).getCustomerID() + "\t\t" + eventList.get(i).getServerID() + "\t\t" + eventList.get(i).getActivity());
            }
            else
            {
                System.out.println("\t" + eventList.get(i).getEventID() + "\t     " + eventList.get(i).getCaseID() + "\t            " + eventList.get(i).getTimestamp() + "\t       " + eventList.get(i).getCustomerID() + "\t\t" + eventList.get(i).getServerID() + "\t\t" + eventList.get(i).getActivity());
            }
        }

               

    }
}
