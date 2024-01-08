package com.merrg1n.m3fix.mixins.late.manametal.rng;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.renderer.RenderEXPballMana;

import java.util.Random;

@Mixin(value = RenderEXPballMana.class, remap = false)
public class MixinRenderEXPballMana {
    @Redirect(method = "<init>", at = @At(value = "NEW", target = "()Ljava/util/Random;", remap = false))
    private static Random useXSTR() {
        return new XSTR();
    }
}
