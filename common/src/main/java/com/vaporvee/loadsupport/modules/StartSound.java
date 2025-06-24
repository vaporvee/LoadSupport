package com.vaporvee.loadsupport.modules;

import com.vaporvee.loadsupport.CommonClass;
import com.vaporvee.loadsupport.Constants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class StartSound {
    private static boolean startedOnce = false;

    public static void play() {
        if (startedOnce) return;
        startedOnce = true;
        ResourceLocation rl = ResourceLocation.tryParse(CommonClass.config.loadFinishSound);
        if(rl == null) {
            Constants.LOG.error("Cannot find load sound resource: \"{}\"", CommonClass.config.loadFinishSound);
            return;
        }
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvent.createVariableRangeEvent(rl), 1.0F));
    }
}
