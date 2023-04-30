import org.apache.commons.math3.distribution.PoissonDistribution;
import java.util.Queue;
import java.util.LinkedList;

public class Simulation {
    private int simulationTime;
    private int arrivalRate;
    private PoissonDistribution poisson;
    private Queue<Customer> line_in_drive_thru;
    private int currentTime; 

    public Simulation(int simulationTime, int arrivalRate) {
        this.simulationTime = simulationTime;
        this.arrivalRate = arrivalRate;
        this.poisson = new PoissonDistribution(arrivalRate);
        this.line_in_drive_thru = new LinkedList<Customer>();
        this.currentTime = 0;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public int getArrivalRate() {
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
        line_in_drive_thru.add(customer);
    }

    public void startSimulation()
    {
        float inter_arrival_time = calculateInterArrivalTime();
        

    }


}
