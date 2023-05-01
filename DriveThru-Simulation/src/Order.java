public class Order {
    private int orderID;
    private int customerID;
    private float finishTime;

    public Order(int orderID, int customerID, float finishTime) {
        this.orderID = orderID;
        this.finishTime = finishTime;
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public float getFinishTime() {
        return finishTime;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
