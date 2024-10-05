package com.vaporvee.loadsupport;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.minecraft.client.gui.screens.AccessibilityOnboardingScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(LoadSupport.MODID)
public class LoadSupport
{
    public static LSConfig config;
    private static float allocatedMemoryInGB;
    // Define mod id in a common place for everything to reference
    public static final String MODID = "loadsupport";
    // Directly reference a slf4j logger
    static final Logger logger = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public LoadSupport(IEventBus modEventBus, ModContainer modContainer)
    {
        AutoConfig.register(LSConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(LSConfig.class).getConfig();
        allocatedMemoryInGB = Runtime.getRuntime().totalMemory() / 1073741824f; // Hardcoded value for GB
        LoadSupport.logger.info(String.format("Allocated Memory: %.1f GB", allocatedMemoryInGB));
        ScreenEvents.BEFORE_INIT.register(LoadSupport::beforeWindowInit);
    }

    private static void beforeWindowInit(Minecraft minecraft, Screen screen, int i, int i1) {
        if(config.minMemory > allocatedMemoryInGB){
            System.setProperty("java.awt.headless", "false"); // Hacky stupid thing but it works I guess...
            LSWindow.createWindow(minecraft, screen);
        }
        if (screen instanceof TitleScreen || screen instanceof AccessibilityOnboardingScreen){
            LoadNotifier.notifySound(minecraft);
        }
    }

    public static String[] getWarningMessage(){
        return new String[]{config.errorTitle, config.errorDescription
                .replace("{minMemory}", String.valueOf(config.minMemory))
                .replace("{currentMemory}", String.format("%.1f", allocatedMemoryInGB))};
    };
}
