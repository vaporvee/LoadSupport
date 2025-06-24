package com.vaporvee.loadsupport;

import com.vaporvee.loadsupport.modules.StartSound;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.AccessibilityOnboardingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;

public class ScreenEventHandler {
    public static void eventTrigger(Screen screen) {
        if (screen instanceof TitleScreen || screen instanceof AccessibilityOnboardingScreen) {
            StartSound.play();
        }
    }
}
