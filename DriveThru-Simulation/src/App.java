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

        eventList = SortByTimeStamp(eventList);
        
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

        System.out.println("Simulation is Complete!");
        System.out.println("Here are arrival times, service times, and departure times for each customer.");
        System.out.println("     Customer ID\t Arrival Time\t Service Time\t Departure Time\t Waiting Time\t");
        LinkedList <Customer> customerList = simulation.getCustomerList();

        for (int i = 0; i < customerList.size(); i++)
        {
            if (customerList.get(i).getArrivalTime() < 10)
            {
                if (customerList.get(i).getServiceTime() == 0)
                {
                    customerList.get(i).calculateServiceTime(); //Some people do not get to receive order, so this function is never called, so I will call it here
                }

                if (customerList.get(i).getWaitTime() == 0)
                {
                    customerList.get(i).calculateWaitTime(); //Some people do not get to receive order, so this function is never called, so I will call it here
                }

                if (customerList.get(i).getWaitTime() <= 0)
                {
                    System.out.println("\t" + customerList.get(i).getID() + "\t\t    " + customerList.get(i).getArrivalTime() + "\t\t" + customerList.get(i).getServiceTime() + "\t\t" + customerList.get(i).getDepartureTime() + "\t\t" + customerList.get(i).getWaitTime() + " (Simulation ended before departure could happen)");
                }
                else
                {
                    System.out.println("\t" + customerList.get(i).getID() + "\t\t    " + customerList.get(i).getArrivalTime() + "\t\t" + customerList.get(i).getServiceTime() + "\t\t" + customerList.get(i).getDepartureTime() + "\t\t" + customerList.get(i).getWaitTime());
                }
            }
            else
            {
                if (customerList.get(i).getServiceTime() == 0)
                {
                    customerList.get(i).calculateServiceTime(); //Some people do not get to receive order, so this function is never called, so I will call it here
                }

                if (customerList.get(i).getWaitTime() == 0)
                {
                    customerList.get(i).calculateWaitTime(); //Some people do not get to receive order, so this function is never called, so I will call it here
                }

                if (customerList.get(i).getWaitTime() <= 0)
                {
                    System.out.println("\t" + customerList.get(i).getID() + "\t\t" + customerList.get(i).getArrivalTime() + "\t\t       " + customerList.get(i).getServiceTime() + "\t\t" + customerList.get(i).getDepartureTime() + "\t\t" + customerList.get(i).getWaitTime() + " (Simulation ended before departure could happen)");
                }

                else
                {
                    System.out.println("\t" + customerList.get(i).getID() + "\t\t" + customerList.get(i).getArrivalTime() + "\t\t       " + customerList.get(i).getServiceTime() + "\t\t" + customerList.get(i).getDepartureTime() + "\t\t" + customerList.get(i).getWaitTime());
                }
            }
        }
            
    }

    public static LinkedList<Event> SortByTimeStamp(LinkedList<Event> eventList)
    {
        for (int i = 0; i < eventList.size(); i++)
        {
            for (int j = 0; j < eventList.size() - 1; j++)
            {
                if (eventList.get(j).getTimestamp() > eventList.get(j + 1).getTimestamp())
                {
                    Event temp = eventList.get(j);
                    eventList.set(j, eventList.get(j + 1));
                    eventList.set(j + 1, temp);
                }
            }
            eventList.get(i).setEventID(i + 1);
        }

        return eventList;
    }
}
