package com.vaporvee.loadsupport;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadSupport implements ModInitializer {
	public static final String MOD_ID = "loadsupport";

	public static final Logger logger = LoggerFactory.getLogger("Load Support");

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
			logger.info(MOD_ID + " is a client mod only!");
			return;
		}
		logger.info("Loading Load Support mod.");
	}
}