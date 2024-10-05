package com.vaporvee.loadsupport;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.sounds.SoundEvents;

public class LoadNotifier {
    static boolean wasPlayed = false;
    public static void notifySound(Minecraft client){
        if(LoadSupport.config.startSound && !wasPlayed) {
            wasPlayed = true;
            client.getSoundManager().play(
                    SimpleSoundInstance.forUI(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1.0F)  // Use any sound event you like
            );
        }
    }
}
