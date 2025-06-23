package com.vaporvee.loadsupport.modules;

import com.vaporvee.loadsupport.CommonClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

public class StartSound {
    private static boolean startedOnce = false;

    public static void play() {
        if (!startedOnce && CommonClass.config.startSound) {
            startedOnce = true;
            Minecraft.getInstance().getSoundManager().play(
                    SimpleSoundInstance.forUI(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1.0F)
            );
        }
    }
}