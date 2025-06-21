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
    public static void checkConfig(Config config) {
        Constants.LOG.info("Config loaded!");
        if (config != null) {
            if(config.minMemory > Allocated.memoryInGB){
                System.setProperty("java.awt.headless", "false");
                Constants.LOG.error("Not enough memory! Allocated memory in GB is {} but set in config is {}",
                        Allocated.memoryInGB, config.minMemory);
                Allocated.createErrorWindow();
            }
        } else {
            Constants.LOG.warn("Load config is null!");
        }
    }
}