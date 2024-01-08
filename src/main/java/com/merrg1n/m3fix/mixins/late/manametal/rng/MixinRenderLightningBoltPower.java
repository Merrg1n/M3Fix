package com.merrg1n.m3fix.mixins.late.manametal.rng;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.renderer.RenderLightningBoltPower;

import java.util.Random;

@Mixin(value = RenderLightningBoltPower.class, remap = false)
public class MixinRenderLightningBoltPower {
    @Unique
    private static Random m3fix$rng = new XSTR();
    @Redirect(
        method = "doRender(Lproject/studio/manametalmod/magic/EntityLightningBoltPower;DDDFF)V",
        at = @At(
            value = "NEW",
            target = "(J)Ljava/util/Random;",
            remap = false
        )
    )
    private static Random useXSTR(long seed) {
        m3fix$rng.setSeed(seed);
        return m3fix$rng;
    }
}
