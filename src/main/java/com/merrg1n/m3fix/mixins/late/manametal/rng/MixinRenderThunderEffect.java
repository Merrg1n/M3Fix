package com.merrg1n.m3fix.mixins.late.manametal.rng;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.fx.thunder.RenderThunderEffect;

import java.util.Random;

@Mixin(value = RenderThunderEffect.class, remap = false)
public class MixinRenderThunderEffect {
    @Unique
    private static Random m3fix$rng = new XSTR();
    @Redirect(method = "render", at = @At(value = "NEW", target = "(J)Ljava/util/Random;", remap = false))
    private static Random useXSTR(long seed){
        m3fix$rng.setSeed(seed);
        return m3fix$rng;
    }
}
