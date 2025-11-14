import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    private String url;
    private String username;
    private String password;

    private int maxPoolSize = 10;
    private List<Connection> availableConnections = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();

    public ConnectionPool(String url, String username, String password, int maxPoolSize) throws Exception {
        this.url = url;
        this.username = username;
        this.password = password;
        this.maxPoolSize = maxPoolSize;

        for (int i = 0; i < maxPoolSize; i++) {
            availableConnections.add(createConnection());
        }
    }

    private Connection createConnection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    public synchronized Connection getConnection() throws Exception {
        while (availableConnections.isEmpty()) {
            if (usedConnections.size() < maxPoolSize) {
                availableConnections.add(createConnection());
                break;
            }
            wait();
        }

        Connection con = availableConnections.remove(availableConnections.size() - 1);
        usedConnections.add(con);
        return con;
    }

    public synchronized void releaseConnection(Connection con) {
        usedConnections.remove(con);
        availableConnections.add(con);
        notifyAll();
    }

    public int getAvailableCount() {
        return availableConnections.size();
    }

    public int getUsedCount() {
        return usedConnections.size();
    }
}

