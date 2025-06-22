package com.vaporvee.loadsupport;


import com.vaporvee.loadsupport.platform.Services;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class LoadSupport {

    public LoadSupport(IEventBus eventBus) {
        CommonClass.init();
    }
}