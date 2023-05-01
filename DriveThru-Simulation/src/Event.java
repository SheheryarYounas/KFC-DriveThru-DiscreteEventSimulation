public class Event {
    private int caseID;
    private int eventID;
    private float timestamp;
    private int customerID;
    private int serverID;
    private String activity;

    public Event(int caseID, int eventID, float timestamp, int customerID, int serverID, String activity) {
        this.caseID = caseID;
        this.eventID = eventID;
        this.timestamp = timestamp;
        this.customerID = customerID;
        this.serverID = serverID;
        this.activity = activity;
    }

    public int getCaseID() {
        return caseID;
    }

    public int getEventID() {
        return eventID;
    }

    public float getTimestamp() {
        return timestamp;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getServerID() {
        return serverID;
    }

    public String getActivity() {
        return activity;
    }

    public void setCaseID(int caseID) {
        this.caseID = caseID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setTimestamp(float timestamp) {
        this.timestamp = timestamp;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void setServerID(int serverID) {
        this.serverID = serverID;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

}
