package com.vaporvee.enoughmemory;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;

public class EnoughMemoryClient implements ClientModInitializer {
	public static EMConfig config;
	@Override
	public void onInitializeClient() {
		AutoConfig.register(EMConfig.class, Toml4jConfigSerializer::new);
		config = AutoConfig.getConfigHolder(EMConfig.class).getConfig();
		float allocatedMemoryInGB = Runtime.getRuntime().totalMemory() / 1073741824f; // Hardcoded value for GB
        EnoughMemory.logger.info(String.format("Allocated Memory: %.1f GB", allocatedMemoryInGB));
		if(config.minMemory > allocatedMemoryInGB){
			System.setProperty("java.awt.headless", "false"); // Hacky stupid thing but it works I guess...
			ScreenEvents.BEFORE_INIT.register(EnoughMemoryClient::beforeWindowInit);
		}
	}

	public static String[] getWarningMessage(){
		return new String[]{config.errorTitle, config.errorDescription.replace("{minMemory}", String.valueOf(config.minMemory))};
	};

	private static void beforeWindowInit(MinecraftClient minecraftClient, Screen screen, int i, int i1) {
		EMWindow.createWindow(minecraftClient, screen);
	}
}