package LabSheet2;

import java.io.IOException;
import java.util.logging.*;

public class SystemSimulation {

    // Logger setup
    private static final Logger logger = Logger.getLogger(SystemSimulation.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("process_log.txt", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false); // avoid console duplication
        } catch (IOException e) {
            System.err.println("Error setting up logger: " + e.getMessage());
        }
    }

    // Simulated system process (task)
    static class SystemProcess extends Thread {
        private String taskName;

        public SystemProcess(String taskName) {
            super(taskName);
            this.taskName = taskName;
        }

        @Override
        public void run() {
            try {
                logger.info(taskName + " started");
                Thread.sleep(2000); // Simulate process execution
                logger.info(taskName + " ended");
            } catch (InterruptedException e) {
                logger.warning(taskName + " interrupted");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("System Starting...");
        logger.info("System Boot Sequence Initiated");

        // Create simulated processes
        SystemProcess p1 = new SystemProcess("Process-1");
        SystemProcess p2 = new SystemProcess("Process-2");

        // Start processes concurrently
        p1.start();
        p2.start();

        // Wait for both to finish
        try {
            p1.join();
            p2.join();
        } catch (InterruptedException e) {
            logger.warning("Main system thread interrupted");
        }

        System.out.println("System Shutdown.");
        logger.info("System Shutdown Completed");
    }
}
