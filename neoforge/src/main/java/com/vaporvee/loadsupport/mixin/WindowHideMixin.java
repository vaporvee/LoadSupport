package com.vaporvee.loadsupport.mixin;

import com.mojang.blaze3d.platform.Window;
import com.vaporvee.loadsupport.Allocated;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Window.class)
public class WindowHideMixin {
    @Inject(
            method = "takeOverWindow",
            at = @At("RETURN")
    )
    private void onTakeOverWindow(CallbackInfoReturnable<Long> cir) {
        if (!Allocated.enoughMemory) {
            long w = cir.getReturnValue();
            GLFW.glfwHideWindow(w);
        }
    }
}
