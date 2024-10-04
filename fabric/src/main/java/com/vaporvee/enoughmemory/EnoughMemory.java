package com.vaporvee.enoughmemory;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnoughMemory implements ModInitializer {
	public static final String MOD_ID = "enoughmemory";

	public static final Logger logger = LoggerFactory.getLogger("EnoughMemory");

	@Override
	public void onInitialize() {
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
			logger.info(MOD_ID + " is a client mod only!");
			return;
		}
		logger.info("Loading EnoughMemory mod.");
	}
}