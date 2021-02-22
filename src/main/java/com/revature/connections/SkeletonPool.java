package com.revature.connections;

import java.sql.Connection;

/**
 * Interface that will define the pattern for the connection pool that I use.
 * Single interface for use of shaping the ConnectionPool
 *
 * Credits: https://www.baeldung.com/java-connection-pooling
 * Authored by Baeldung
 */
public interface SkeletonPool {
    Connection getConnection();
    boolean releaseConnection(Connection connection);
    String getUrl();
    String getUser();
    String getPassword();
}
