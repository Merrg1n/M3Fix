package com.merrg1n.m3fix.mixins.late.manametal.rng;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.renderer.RenderDragonM3;

import java.util.Random;

@Mixin(value = RenderDragonM3.class, remap = false)
public class MixinRenderDragonM3 {

    @Unique
    private static Random m3fix$rng = new XSTR();

    @Redirect(
        method = "renderEquippedItems(Lproject/studio/manametalmod/ender/EntityDragonClone;F)V",
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
