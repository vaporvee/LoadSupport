package com.vaporvee.loadsupport;

import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;

public class ClientScreenHandler {
    public static void register() {
        ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
            ScreenEventHandler.eventTrigger(screen);
        });
    }
}