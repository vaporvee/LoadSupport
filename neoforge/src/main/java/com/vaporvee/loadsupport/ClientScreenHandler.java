package com.vaporvee.loadsupport;

import net.minecraft.client.gui.screens.Screen;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.common.NeoForge;

public class ClientScreenHandler {
    public static void register() {
        NeoForge.EVENT_BUS.addListener(ClientScreenHandler::onScreenInit);
    }

    private static void onScreenInit(ScreenEvent.Init.Post event) {
        Screen screen = event.getScreen();
        ScreenEventHandler.eventTrigger(screen);
    }
}
