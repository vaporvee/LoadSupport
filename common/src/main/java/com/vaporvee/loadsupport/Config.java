package com.vaporvee.loadsupport;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = Constants.MOD_ID)
public class Config implements ConfigData {
    boolean startSound = true;
    float minMemory = 4.0f;
    String errorTitle = "Error: Not enough Java memory!";
    String errorMinMemory = "Please allocate at least {minMemory} GB of Java memory to your Minecraft instance!";
    String errorCurrentMemory = "You have currently {currentMemory} GB allocated.";
    String memoryInfoLink = "https://github.com/vaporvee/LoadSupport/wiki/How-to-allocate-more-memory-to-your-Minecraft-instance";
}
