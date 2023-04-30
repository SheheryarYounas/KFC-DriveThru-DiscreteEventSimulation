public class Customer {
    private int arrivalTime;
    private int serviceTime;
    private int departureTime;
    private int waitTime;

    public Customer(int arrivalTime, int serviceTime, int departureTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.departureTime = departureTime;
        calculateWaitTime();
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getDepartureTime() {
        return departureTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setDepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public void calculateWaitTime() {
        this.waitTime = this.departureTime - this.arrivalTime - this.serviceTime;
    }

}
