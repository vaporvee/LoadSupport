package com.vaporvee.loadsupport;

import me.shedaniel.autoconfig.ConfigData;

@me.shedaniel.autoconfig.annotation.Config(name = Constants.MOD_ID)
public class Config implements ConfigData {
    public String loadFinishSound = "minecraft:ui.toast.challenge_complete";
    public float minMemory = 4.0f;
    public String errorTitle = "Error: Not enough Java memory!";
    public String errorMinMemory = "Please allocate at least {minMemory} GB of Java memory to your Minecraft instance!";
    public String errorCurrentMemory = "You have currently {currentMemory} GB allocated.";
    public String memoryInfoLink = "https://github.com/vaporvee/LoadSupport/wiki/How-to-allocate-more-memory-to-your-Minecraft-instance";
}
