package com.solvd.lawyers.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {

    private static final Logger LOGGER = LogManager.getLogger(Connection.class);

    public void create() {
        LOGGER.info("Created");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    public void read() {
        LOGGER.info("Reading");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    public void update() {
        LOGGER.info("Update");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }

    public void delete() {
        LOGGER.info("Deleted");
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            LOGGER.error(e);
        }
    }
}
