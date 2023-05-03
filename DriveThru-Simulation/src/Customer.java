public class Customer {
    private float arrivalTime;
    private float serviceTime;
    private float serviceTime_start_order_taker;
    private float serviceTime_start_cashier;
    private float serviceTime_start_order_handoff;
    private float serviceTime_end_order_taker;
    private float serviceTime_end_cashier;
    private float serviceTime_end_order_handoff;
    private float departureTime;
    private float waitTime;
    private int ID;
    private boolean is_being_served;
    private boolean has_ordered;
    private boolean has_paid;
    private boolean has_collected;

    public Customer(float arrivalTime, float serviceTime, float departureTime, int ID) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.departureTime = departureTime;
        this.ID = ID;
        calculateWaitTime();
        this.is_being_served = false;
        this.has_ordered = false;
        this.has_paid = false;
        this.has_collected = false;
    }

    public float getArrivalTime() {
        return arrivalTime;
    }

    public float getServiceTime() {
        return serviceTime;
    }

    public float getDepartureTime() {
        return departureTime;
    }

    public void setArrivalTime(float arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setServiceTime(float serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setDepartureTime(float departureTime) {
        this.departureTime = departureTime;
    }

    public float getWaitTime() {
        return waitTime;
    }

    public boolean getIsBeingServed() {
        return is_being_served;
    }

    public boolean getHasOrdered() {
        return has_ordered;
    }

    public boolean getHasPaid() {
        return has_paid;
    }

    public boolean getHasCollected() {
        return has_collected;
    }

    public void calculateWaitTime() {
        this.waitTime = this.departureTime - this.arrivalTime - this.serviceTime;
    }

    public void setIsBeingServed(boolean is_being_served) {
        this.is_being_served = is_being_served;
    }

    public void setHasOrdered(boolean has_ordered) {
        this.has_ordered = has_ordered;
    }

    public void setHasPaid(boolean has_paid) {
        this.has_paid = has_paid;
    }

    public void setHasCollected(boolean has_collected) {
        this.has_collected = has_collected;
    }

    public float getServiceTime_start_order_taker() {
        return serviceTime_start_order_taker;
    }

    public float getServiceTime_start_cashier() {
        return serviceTime_start_cashier;
    }

    public float getServiceTime_start_order_handoff() {
        return serviceTime_start_order_handoff;
    }

    public float getServiceTime_end_order_taker() {
        return serviceTime_end_order_taker;
    }

    public float getServiceTime_end_cashier() {
        return serviceTime_end_cashier;
    }

    public float getServiceTime_end_order_handoff() {
        return serviceTime_end_order_handoff;
    }

    public void setServiceTime_start_order_taker(float serviceTime_start_order_taker) {
        this.serviceTime_start_order_taker = serviceTime_start_order_taker;
    }

    public void setServiceTime_start_cashier(float serviceTime_start_cashier) {
        this.serviceTime_start_cashier = serviceTime_start_cashier;
    }

    public void setServiceTime_start_order_handoff(float serviceTime_start_order_handoff) {
        this.serviceTime_start_order_handoff = serviceTime_start_order_handoff;
    }

    public void setServiceTime_end_order_taker(float serviceTime_end_order_taker) {
        this.serviceTime_end_order_taker = serviceTime_end_order_taker;
    }

    public void setServiceTime_end_cashier(float serviceTime_end_cashier) {
        this.serviceTime_end_cashier = serviceTime_end_cashier;
    }

    public void setServiceTime_end_order_handoff(float serviceTime_end_order_handoff) {
        this.serviceTime_end_order_handoff = serviceTime_end_order_handoff;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void calculateServiceTime()
    {
        this.serviceTime = (this.serviceTime_end_order_taker - this.serviceTime_start_order_taker) + (this.serviceTime_end_cashier - this.serviceTime_start_cashier) + (this.serviceTime_end_order_handoff - this.serviceTime_start_order_handoff);
    }

    



}
