package com.vaporvee.loadsupport.platform;

import com.vaporvee.loadsupport.CommonClass;
import com.vaporvee.loadsupport.Config;
import com.vaporvee.loadsupport.platform.services.IConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.neoforged.neoforge.client.event.ScreenEvent;

public class LSConfigNeoForge implements IConfig {
    public static Config config;
    @Override
    public void InitConfig() {
        AutoConfig.register(Config.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Config.class).getConfig();
        CommonClass.checkConfig(config);

    }
    @Override
    public Config getConfig() {
        return config;
    }
}
