import org.apache.commons.math3.distribution.PoissonDistribution;
import java.util.Queue;
import java.util.LinkedList;

public class Simulation {
    private float simulationTime;
    private float arrivalRate;
    private PoissonDistribution poisson;
    private Queue<Customer> line_for_order_taker;
    private Queue<Customer> line_for_cashier;
    private Queue<Customer> line_for_order_handoff;
    private float currentTime;
    private int assign_customer_id; 
    private LinkedList<Server> servers;
    private LinkedList<Event> eventList;
    private int eventID;
    private int orderID;
    private LinkedList<Order> orderList;

    public Simulation(float simulationTime, float arrivalRate, LinkedList<Server> servers) {
        this.simulationTime = simulationTime;
        this.arrivalRate = arrivalRate;
        this.poisson = new PoissonDistribution(arrivalRate);
        this.line_for_order_taker = new LinkedList<Customer>();
        this.line_for_cashier = new LinkedList<Customer>();
        this.line_for_order_handoff = new LinkedList<Customer>();
        this.eventList = new LinkedList<Event>();
        this.assign_customer_id = 0;
        this.currentTime = 0;
        this.servers = servers;
        this.eventID = 1;
        this.orderList = new LinkedList<Order>();
    }

    public float getSimulationTime() {
        return simulationTime;
    }

    public float getArrivalRate() {
        return arrivalRate;
    }

    public PoissonDistribution getPoisson() {
        return poisson;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public void setArrivalRate(int arrivalRate) {
        this.arrivalRate = arrivalRate;
    }

    public void setPoisson(PoissonDistribution poisson) {
        this.poisson = poisson;
    }

    public float calculateInterArrivalTime() {
        int interArrivalTime = poisson.sample();
        return interArrivalTime;
    }

    public void AddCarToLine(Customer customer) {
        line_for_order_taker.add(customer);
    }

    public float getChefServiceTime() {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getRole().equals("Chef")) {
                return servers.get(i).getAverage_service_time();
            }
        }

        return -1;
    }

    public void confirmOrder(int orderID, int customerID)
    {
        currentTime = currentTime + 10; //Lets assume just confirming and letting the chef start to cook takes 10 seconds
        Order order = new Order(orderID, customerID, currentTime + getChefServiceTime());
    }

    public boolean isOrderReady(int customerID)
    {
        for (int i = 0; i < orderList.size(); i++)
        {
            if (orderList.get(i).getCustomerID() == customerID)
            {
                if (currentTime >= orderList.get(i).getFinishTime())
                {
                    return true;
                }
            }
        }

        return false;
    }

    public float calculateTimeLeftForOrder(int customerID)
    {
        for (int i = 0; i < orderList.size(); i++)
        {
            if (orderList.get(i).getCustomerID() == customerID)
            {
                return orderList.get(i).getFinishTime() - currentTime;
            }
        }

        return -1;
    }

    public void AssignServer() {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).getIsBusy() == false)
            {
                if (servers.get(i).getRole().equals("Order Taker"))
                {
                    if (line_for_order_taker.size() > 0)
                    {
                        Customer customer_to_order = line_for_order_taker.remove();
                        customer_to_order.setServiceTime_start_order_taker(currentTime);
                        recordEvent(customer_to_order.getID(), eventID++, currentTime, customer_to_order.getID(), servers.get(i).getId(), "Server starts taking order from customer");
                        servers.get(i).setIsBusy(true);
                        currentTime = currentTime + servers.get(i).getAverage_service_time();
                        customer_to_order.setServiceTime_end_order_taker(currentTime);
                        recordEvent(customer_to_order.getID(), eventID++, currentTime, customer_to_order.getID(), servers.get(i).getId(), "Server finishes taking order from customer");
                        customer_to_order.setHasOrdered(true);
                        servers.get(i).setIsBusy(false);
                        line_for_cashier.add(customer_to_order);

                        confirmOrder(orderID++, customer_to_order.getID());
                        recordEvent(customer_to_order.getID(), eventID++, currentTime, customer_to_order.getID(), servers.get(i).getId(), "Chef confirms order and starts cooking");
                    }
                }

                if (servers.get(i).getRole().equals("Cashier"))
                {
                    if (line_for_cashier.size() > 0)
                    {
                        Customer customer_to_cashier = line_for_cashier.remove();
                        customer_to_cashier.setServiceTime_start_cashier(currentTime);
                        servers.get(i).setIsBusy(true);
                        currentTime = currentTime + servers.get(i).getAverage_service_time();
                        customer_to_cashier.setServiceTime_end_cashier(currentTime);
                        customer_to_cashier.setHasPaid(true);
                        servers.get(i).setIsBusy(false);
                        line_for_order_handoff.add(customer_to_cashier);
                    }
                }

                if (servers.get(i).getRole().equals("Order Handoff"))
                {
                    if (line_for_order_handoff.size() > 0)
                    {
                        //First, chef needs to complete making the food
                        Customer customer_to_order_handoff = line_for_order_handoff.remove();
                        customer_to_order_handoff.setServiceTime_start_order_handoff(currentTime);
                        servers.get(i).setIsBusy(true);

                        if (!isOrderReady(customer_to_order_handoff.getID()))
                        {
                            currentTime = currentTime + calculateTimeLeftForOrder(customer_to_order_handoff.getID()); 
                        }

                        else
                        {
                            float order_timestamp = 0;
                            for (int j = 0; j < orderList.size(); j++)
                            {
                                if (orderList.get(j).getCustomerID() == customer_to_order_handoff.getID())
                                {
                                    order_timestamp = orderList.get(j).getFinishTime();
                                }
                            }
                            recordEvent(customer_to_order_handoff.getID(), eventID++, order_timestamp, customer_to_order_handoff.getID(), servers.get(i).getId(), "Chef has finished preparing the food");
                        }

                        currentTime = currentTime + servers.get(i).getAverage_service_time(); //this is time to just hand off the food
                        customer_to_order_handoff.setServiceTime_end_order_handoff(currentTime);
                        customer_to_order_handoff.setHasCollected(true);
                        servers.get(i).setIsBusy(false);
                        customer_to_order_handoff.setDepartureTime(currentTime);
                        customer_to_order_handoff.calculateServiceTime();
                        customer_to_order_handoff.calculateWaitTime();
                        recordEvent(customer_to_order_handoff.getID(), eventID++, currentTime, customer_to_order_handoff.getID(), servers.get(i).getId(), "Customer has collected the food and left the drive thru");
                    }
                }
            }
        }
    }

    public void recordEvent(int caseID, int eventID, float timestamp, int customerID, int serverID, String activity)
    {
        Event event = new Event(caseID, eventID, timestamp, customerID, serverID, activity);
        eventList.add(event);
    }

    public LinkedList<Event> startSimulation()
    {
        float inter_arrival_time = calculateInterArrivalTime();
        float customer_arrival_time = currentTime + inter_arrival_time;

        while (currentTime < simulationTime)
        {
            if (currentTime >= customer_arrival_time)
            {
                Customer customer = new Customer(currentTime, 0, 0, assign_customer_id);
                //current time here is arrival time of customer, record this later
                AddCarToLine(customer);
                recordEvent(customer.getID(), eventID++, currentTime, customer.getID(), 0, "Customer arrives at drive thru");
                assign_customer_id++;
                inter_arrival_time = calculateInterArrivalTime();
                customer_arrival_time = currentTime + inter_arrival_time;
            }
            
            AssignServer();

            currentTime = currentTime + 1;
        }

        return eventList;

    }

    
}
