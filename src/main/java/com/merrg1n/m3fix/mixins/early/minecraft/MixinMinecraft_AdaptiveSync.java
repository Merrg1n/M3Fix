package com.merrg1n.m3fix.mixins.early.minecraft;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = Minecraft.class)
public class MixinMinecraft_AdaptiveSync {
    @Redirect(method = "startGame", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setVSyncEnabled(Z)V", remap = false))
    void setVsync$1(boolean e) {
        Display.setSwapInterval(e ? -1 : 0);
    }

    @Redirect(method = "toggleFullscreen", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setVSyncEnabled(Z)V", remap = false))
    void setVsync$2(boolean e) {
        Display.setSwapInterval(e ? -1 : 0);
    }
//    @ModifyArg(method = "setVSyncEnabled", at=@At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setSwapInterval(I)V"))
//    private static int setSwapInterval(int value){
//        System.out.println("setSwapInterval Triggered!");
//        return value == 1 ? -1 : 0;
//    }
}
