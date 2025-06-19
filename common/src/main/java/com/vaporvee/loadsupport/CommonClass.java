package com.vaporvee.loadsupport;

import com.vaporvee.loadsupport.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;

public class CommonClass {
    public static void init() {
        // Constants.LOG.info("Hello from Common init on {}! we are currently in a {} environment!", Services.PLATFORM.getPlatformName(), Services.PLATFORM.getEnvironmentName());
        // Constants.LOG.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));
        // if (Services.PLATFORM.isModLoaded("loadsupport"))
        if (Services.PLATFORM.isEnvServer()) {
            Constants.LOG.info(Constants.MOD_ID + " is a client mod only!");
            return;
        }
        Constants.LOG.info("Loading Load Support mod.");
    }
}