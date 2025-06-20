package com.vaporvee.loadsupport;

public class Allocated {
    public static float memoryInGB;
    public static void init(){
        memoryInGB = Runtime.getRuntime().maxMemory() / Constants.GIGABYTE;
        memoryInGB = Math.round(Allocated.memoryInGB * 10) / 10f;
    }
    public static void printAllocated() {
        Constants.LOG.info(String.format("Allocated Memory: %.1f GB", memoryInGB));
    }
}
