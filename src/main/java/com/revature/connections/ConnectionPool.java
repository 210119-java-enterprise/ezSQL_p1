package com.revature.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple implementation of a Connection Pool
 * This is yet to be integrated into the project: Fixes necessary for core functionality
 * take precedent before implementing the Pool.
 *
 * Credits: https://www.baeldung.com/java-connection-pooling
 * Authored by Baeldung.
 */
public class ConnectionPool implements SkeletonPool{
    private String url;
    private String user;
    private String password;
    private List<Connection> connectionPool;
    private List<Connection> usedConnections = new ArrayList<>();
    private static int INITIAL_POOL_SIZE = 10;

    /**
     * STANDARD CONSTRUCTORS
     */
    public ConnectionPool(String url, String user, String password, List<Connection> connectionPool) {
        this.url = url;
        this.user = user;
        this.password = password;
        this.connectionPool = connectionPool;
    }

    public ConnectionPool() {
        super();
    }

    /**
     * Actual functionality for the pooling.
     * Creating will allow the creation of the pool with an initial size, adding hte user to the pool, and returning
     * the connectionpool created from it. ReleaseConnection puts the released connection into usedConnections,
     * and CreateConnection returns a DriverManager made GetConnection call.
     */
    public static ConnectionPool create(
            String url, String user,
            String password) throws SQLException {

        List<Connection> pool = new ArrayList<>(INITIAL_POOL_SIZE);
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            pool.add(createConnection(url, user, password));
        }
        return new ConnectionPool(url, user, password, pool);
    }

    @Override
    public boolean releaseConnection(Connection connection) {
        connectionPool.add(connection);
        return usedConnections.remove(connection);
    }

    private static Connection createConnection(
            String url, String user, String password)
            throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * STANDARD GETTERS
     */
    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public Connection getConnection() {
        Connection connection = connectionPool
                .remove(connectionPool.size() - 1);
        usedConnections.add(connection);
        return connection;
    }

    public int getSize() {
        return connectionPool.size() + usedConnections.size();
    }

}
