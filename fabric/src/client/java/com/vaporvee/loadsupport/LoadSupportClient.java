package com.vaporvee.loadsupport;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.AccessibilityOnboardingScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;

public class LoadSupportClient implements ClientModInitializer {
	public static LSConfig config;
	private static float allocatedMemoryInGB;
	@Override
	public void onInitializeClient() {
		AutoConfig.register(LSConfig.class, Toml4jConfigSerializer::new);
		config = AutoConfig.getConfigHolder(LSConfig.class).getConfig();
		allocatedMemoryInGB = Runtime.getRuntime().totalMemory() / 1073741824f; // Hardcoded value for GB
		LoadSupport.logger.info(String.format("Allocated Memory: %.1f GB", allocatedMemoryInGB));
		ScreenEvents.BEFORE_INIT.register(LoadSupportClient::beforeWindowInit);
	}

	public static String[] getWarningMessage(){
		return new String[]{config.errorTitle, config.errorDescription
				.replace("{minMemory}", String.valueOf(config.minMemory))
				.replace("{currentMemory}", String.format("%.1f", allocatedMemoryInGB))};
	};
	private static void beforeWindowInit(MinecraftClient minecraftClient, Screen screen, int i, int i1) {
		if(config.minMemory > allocatedMemoryInGB){
			System.setProperty("java.awt.headless", "false"); // Hacky stupid thing but it works I guess...
			LSWindow.createWindow(minecraftClient, screen);
		}
		if (screen instanceof TitleScreen || screen instanceof AccessibilityOnboardingScreen){
			LoadNotifier.notifySound(minecraftClient);
		}
	}
}