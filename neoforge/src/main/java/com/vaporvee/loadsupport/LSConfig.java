package com.vaporvee.loadsupport;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = LoadSupport.MODID)
public class LSConfig implements ConfigData {
    boolean startSound = true;
    float minMemory = 4.0f;
    String errorTitle = "Error: Not enough Java memory!";
    String errorDescription = "Please allocate at least {minMemory} GB of Java memory to your Minecraft Instance! You have currently {currentMemory} GB allocated.";
}
