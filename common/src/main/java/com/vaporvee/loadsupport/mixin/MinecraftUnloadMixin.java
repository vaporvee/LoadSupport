package com.vaporvee.loadsupport.mixin;

import com.vaporvee.loadsupport.modules.Allocated;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftUnloadMixin {
    @Inject(method = "run", at = @At("HEAD"), cancellable = true)
    private void onRunHead(CallbackInfo ci) {
        if (!Allocated.enoughMemory) {
            long window = Minecraft.getInstance().getWindow().getWindow();
            GLFW.glfwHideWindow(window);
            while (Allocated.isWindowOpen()) {
                try { Thread.sleep(100); } catch (InterruptedException ignored) {}
            }
            ci.cancel();
        }
    }
}
