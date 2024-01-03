package com.merrg1n.m3fix.mixins.early.minecraft;

import net.minecraft.client.settings.GameSettings;
import org.lwjgl.opengl.Display;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameSettings.class)
public class MixinGameSettings_AdaptiveSync {
    @Redirect(method = "setOptionValue", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/Display;setVSyncEnabled(Z)V", remap = false))
    void setVsync(boolean e) {
        Display.setSwapInterval(e ? -1 : 0);
    }
}
