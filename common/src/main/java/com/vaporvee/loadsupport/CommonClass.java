package com.vaporvee.loadsupport;

import com.vaporvee.loadsupport.modules.Allocated;
import com.vaporvee.loadsupport.platform.Services;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import org.lwjgl.glfw.GLFW;

public class CommonClass {
    public static boolean init() {
        if (Services.PLATFORM.isEnvServer()) {
            Constants.LOG.info(Constants.MOD_ID + " is a client mod only!");
            return false;
        }
        InitConfig();
        Allocated.init();
        return true;
    }
    public static Config config;
    public static long window;

    private static void InitConfig() {
        AutoConfig.register(Config.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(Config.class).getConfig();
    }

    public static void HideWindow() {
        GLFW.glfwHideWindow(window);
    }
}