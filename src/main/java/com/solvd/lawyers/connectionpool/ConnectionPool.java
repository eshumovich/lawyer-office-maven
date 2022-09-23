package com.solvd.lawyers.connectionpool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    private final BlockingQueue<Connection> connections;
    private static ConnectionPool INSTANCE;

    private ConnectionPool(int size) {
        connections = new LinkedBlockingQueue<>(size);
        for (int i = 0; i < size; i++) {
            connections.add(new Connection());
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
        connections.add(connection);
        LOGGER.info("Size of connections " + connections.size());
    }
}
