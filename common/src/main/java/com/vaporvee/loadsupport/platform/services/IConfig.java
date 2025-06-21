package com.vaporvee.loadsupport.platform.services;

import com.vaporvee.loadsupport.Config;

public interface IConfig {
    /**
     * Initializes config on available platforms
     */
    void InitConfig();

    /**
     * Gets the populated config class with local config data when loaded correctly
     * @return pupulated Config object
     */
    Config getConfig();
}
