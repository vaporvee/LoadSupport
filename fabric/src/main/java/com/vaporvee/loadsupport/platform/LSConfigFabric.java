package com.vaporvee.loadsupport.platform;

import com.vaporvee.loadsupport.CommonClass;
import com.vaporvee.loadsupport.Constants;
import com.vaporvee.loadsupport.LSConfig;
import com.vaporvee.loadsupport.platform.services.IConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

@Config(name = Constants.MOD_ID)
public class LSConfigFabric implements IConfig {
    public static LSConfig config;
    @Override
    public void InitConfig() {
        AutoConfig.register(LSConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(LSConfig.class).getConfig();
        CommonClass.checkConfig(config);
    }
}
