package com.vaporvee.loadsupport;

import com.vaporvee.loadsupport.platform.Services;

public class CommonClass {
    public static void init() {
        if (Services.PLATFORM.isEnvServer()) {
            Constants.LOG.info(Constants.MOD_ID + " is a client mod only!");
            return;
        }
        Constants.LOG.info("Loading Load Support mod.");
        Allocated.init();
        Allocated.printAllocated();
        Services.CONFIG.InitConfig();
    }
    public static void checkConfig(LSConfig config) {
        Constants.LOG.info("Load config test!");
        if (config != null) {
            if(config.minMemory > Allocated.memoryInGB){
                System.setProperty("java.awt.headless", "false");
                Constants.LOG.info("Not enough memory! Allocated memory in GB is {} but set in config is {}",
                        Allocated.memoryInGB, config.minMemory);
                //create jframe window
            }
        } else {
            Constants.LOG.info("Load config is null!");
        }
    }
}