package com.vaporvee.loadsupport;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class LoadSupport {

    public LoadSupport(IEventBus eventBus) {
        boolean initiated = CommonClass.init();
        if (initiated) {
            ClientScreenHandler.register();
        }
    }
}