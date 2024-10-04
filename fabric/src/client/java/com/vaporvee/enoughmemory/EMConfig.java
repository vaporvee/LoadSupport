package com.vaporvee.enoughmemory;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = EnoughMemory.MOD_ID)
public class EMConfig implements ConfigData {
    float minMemory = 4.0f;
    String errorTitle = "Error: Not enough Java memory!";
    String errorDescription = "Please allocate at least {minMemory} GB of Java memory to your Minecraft Instance!";
}
