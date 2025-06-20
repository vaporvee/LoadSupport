package com.vaporvee.loadsupport;

import net.fabricmc.api.ModInitializer;

public class LoadSupport implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();
    }
}
