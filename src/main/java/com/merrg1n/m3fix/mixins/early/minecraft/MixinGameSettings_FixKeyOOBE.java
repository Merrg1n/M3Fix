package com.merrg1n.m3fix.mixins.early.minecraft;

import net.minecraft.client.settings.GameSettings;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameSettings.class)
public class MixinGameSettings_FixKeyOOBE {
    @Redirect(
        method = "getKeyDisplayString",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/input/Keyboard;getKeyName(I)Ljava/lang/String;",
            remap = false
        )
    )
    private static String getKeyName(int key) {
        try {
            return Keyboard.getKeyName(key);
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Key_" + key;
        }
    }
}
