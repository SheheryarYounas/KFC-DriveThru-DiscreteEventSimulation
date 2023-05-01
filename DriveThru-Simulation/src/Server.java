public class Server {

    private int id;
    private String name;
    private boolean isBusy;
    private String role;
    private float average_service_time;

    public Server(int id, String name, boolean isBusy, String role, float average_service_time) {
        this.id = id;
        this.name = name;
        this.isBusy = isBusy;
        this.role = role;
        this.average_service_time = average_service_time;
    }

    public float getAverage_service_time() {
        return average_service_time;
    }

    public void setAverage_service_time(float average_service_time) {
        this.average_service_time = average_service_time;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean getIsBusy() {
        return isBusy;
    }

    public String getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIsBusy(boolean isBusy) {
        this.isBusy = isBusy;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
