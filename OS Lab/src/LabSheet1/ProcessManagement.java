
package LabSheet1;

import java.io.*;

public class ProcessManagement {

    // Task 1: Create N child processes (Windows compatible)
    public static void task1_createProcesses(int n) throws Exception {
        System.out.println("=== Task 1: Process Creation ===");
        for (int i = 0; i < n; i++) {
            // Run "echo" command through Windows CMD
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "echo Child " + (i + 1) + " is running!");
            Process p = pb.start();
            System.out.println("Started Child Process: PID=" + p.pid() + " | Parent PID=" + ProcessHandle.current().pid());

            // Read output of the child
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
        }
        System.out.println("All child processes completed.\n");
    }

    // Task 2: Execute system commands (Windows)
    public static void task2_execCommands(String[] commands) throws Exception {
        System.out.println("=== Task 2: Execute Commands ===");
        for (String cmd : commands) {
            System.out.println("Executing command: " + cmd);
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", cmd);
            pb.redirectErrorStream(true);
            Process p = pb.start();

            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            p.waitFor();
            System.out.println("Command '" + cmd + "' completed.\n");
        }
    }

    // Task 3: Simulate Zombie and Orphan-like behavior (conceptually)
    public static void task3_simulateProcesses() throws Exception {
        System.out.println("=== Task 3: Simulating Zombie/Orphan ===");

        new Thread(() -> {
            try {
                Process zombie = new ProcessBuilder("cmd.exe", "/c", "timeout /t 2").start();
                System.out.println("Zombie simulated: Child PID=" + zombie.pid() + " (Parent didn’t wait)");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Thread orphanParent = new Thread(() -> {
            try {
                Process orphan = new ProcessBuilder("cmd.exe", "/c", "timeout /t 5").start();
                System.out.println("Orphan simulated: Child PID=" + orphan.pid());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        orphanParent.start();
        System.out.println("Parent thread exiting early (Orphan created)\n");
    }

    // Task 4: Inspect process info (Windows version)
    public static void task4_processInfo(long pid) throws Exception {
        System.out.println("=== Task 4: Inspect Process Info ===");
        System.out.println("Process ID: " + pid);
        ProcessHandle process = ProcessHandle.of(pid).orElse(null);
        if (process != null) {
            ProcessHandle.Info info = process.info();
            System.out.println("Command: " + info.command().orElse("N/A"));
            System.out.println("Start Time: " + info.startInstant().orElse(null));
            System.out.println("CPU Duration: " + info.totalCpuDuration().orElse(null));
            System.out.println("User: " + System.getProperty("user.name"));
        } else {
            System.out.println("Process not found!");
        }
    }

    // Task 5: Simulate process priority (Thread priority)
    public static void task5_prioritySimulation() {
        System.out.println("=== Task 5: Priority Simulation ===");

        Runnable cpuTask = () -> {
            long start = System.currentTimeMillis();
            long sum = 0;
            for (long i = 0; i < 1e7; i++) sum += i;
            long end = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " completed in " + (end - start) + "ms");
        };

        Thread low = new Thread(cpuTask, "Low Priority");
        Thread high = new Thread(cpuTask, "High Priority");

        low.setPriority(Thread.MIN_PRIORITY);   // Low = 1
        high.setPriority(Thread.MAX_PRIORITY);  // High = 10

        low.start();
        high.start();
    }

    public static void main(String[] args) throws Exception {
        task1_createProcesses(3);
        task2_execCommands(new String[]{"date /t", "time /t", "whoami"});
        task3_simulateProcesses();
        task4_processInfo(ProcessHandle.current().pid());
        task5_prioritySimulation();
    }
}
