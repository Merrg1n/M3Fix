package com.merrg1n.m3fix.mixins.late.manametal;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import project.studio.manametalmod.event.EventGUI;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

@Mixin(value = EventGUI.class, remap = false)
public abstract class MixinEventGUI {
    @Shadow
    private static Color getAverageColor(BufferedImage image) {
        return null;
    }

    @Unique
    private static Map<String, Color> m3fix$cache = new HashMap<>();

    @Inject(method = "getColorFromItemTexture", at = @At("HEAD"), cancellable = true)
    private static void beforeGetColor(ItemStack item, CallbackInfoReturnable<Color> cir) {
        IIcon icon = item.getIconIndex();
        if (icon != null) {
            String name = icon.getIconName();
            if (m3fix$cache.containsKey(name)) {
                cir.setReturnValue(m3fix$cache.get(name));
                cir.cancel();
            }
        } else {
            cir.setReturnValue(new Color(0, 0, 0));
            cir.cancel();
        }
    }


    @Redirect(
        method = "getColorFromItemTexture",
        at = @At(
            value = "INVOKE",
            target = "Lproject/studio/manametalmod/event/EventGUI;getAverageColor(Ljava/awt/image/BufferedImage;)Ljava/awt/Color;",
            remap = false
        )
    )
    private static Color cacheAverageColor(BufferedImage imageColor, @Local(name = "IconName") String icon) {
        Color tmp = getAverageColor(imageColor);
        m3fix$cache.put(icon, tmp);
        return tmp;
    }
}
