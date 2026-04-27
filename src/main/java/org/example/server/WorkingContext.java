package org.example.server;

public class WorkingContext {
    private static boolean isInitialized = false;
    public static int processId;
    public static volatile boolean isShutdown = false;

    public static void init(int processId) {

    }
}
