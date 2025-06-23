package com.vaporvee.loadsupport;

import net.fabricmc.api.ModInitializer;

public class LoadSupport implements ModInitializer {
    
    @Override
    public void onInitialize() {
        boolean initiated = CommonClass.init();
        if(initiated) {
            ClientScreenHandler.register();
        }
    }
}
