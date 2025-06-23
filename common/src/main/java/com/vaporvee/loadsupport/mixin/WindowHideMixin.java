package com.vaporvee.loadsupport.mixin;

import com.mojang.blaze3d.platform.DisplayData;
import com.mojang.blaze3d.platform.ScreenManager;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.platform.WindowEventHandler;
import com.vaporvee.loadsupport.CommonClass;
import com.vaporvee.loadsupport.modules.Allocated;
import com.vaporvee.loadsupport.platform.Services;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(Window.class)
public class WindowHideMixin {

    @Final
    @Shadow
    private long window;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void afterInit(WindowEventHandler eventHandler, ScreenManager screenManager, DisplayData displayData, String preferredFullscreenVideoMode, String title, CallbackInfo ci) {
        CommonClass.window = window;
        if(Objects.equals(Services.PLATFORM.getPlatformName(), "Fabric") && !Allocated.enoughMemory){ // Fabric loads early enough to fire it here
            CommonClass.HideWindow(); // Hide main Minecraft Window which gets frozen by mixin
        }
    }
}

