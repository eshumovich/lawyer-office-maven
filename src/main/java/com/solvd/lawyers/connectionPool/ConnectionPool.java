package com.solvd.lawyers.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool INSTANCE;

    private final BlockingQueue<Connection> connections;
    private List<Connection> usedConnections = new ArrayList<>();
    private List<Connection> availableConnections = new ArrayList<>();


    private ConnectionPool(int size) {
        connections = new LinkedBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            availableConnections.add(new Connection());
        }
    }

    public static synchronized ConnectionPool getInstance(int size) {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionPool(size);
        }
        return INSTANCE;
    }

    public synchronized Connection getConnection() {
        try {
            return connections.poll(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public void releaseConnection(Connection connection) {
        usedConnections.remove(connection);
        LOGGER.info("Size of givenAwayConnections " + usedConnections.size());

        availableConnections.add(connection);
        LOGGER.info("Size of freeConnections " + availableConnections.size());
    }
}
