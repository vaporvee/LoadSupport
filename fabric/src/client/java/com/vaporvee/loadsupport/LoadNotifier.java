package com.vaporvee.loadsupport;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;

public class LoadNotifier {
    static boolean wasPlayed = false;
    public static void notifySound(MinecraftClient client){
        if(LoadSupportClient.config.startSound && !wasPlayed) {
            wasPlayed = true;
            client.getSoundManager().play(
                    PositionedSoundInstance.master(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1.0F)  // Use any sound event you like
            );
        }
    }
}
