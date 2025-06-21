package com.vaporvee.loadsupport;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = Constants.MOD_ID)
public class Config implements ConfigData {
    boolean startSound = true;
    float minMemory = 4.0f;
    String errorTitle = "Error: Not enough Java memory!";
    String errorDescription = "Please allocate at least {minMemory} GB of Java memory to your Minecraft Instance! You have currently {currentMemory} GB allocated.";
}
