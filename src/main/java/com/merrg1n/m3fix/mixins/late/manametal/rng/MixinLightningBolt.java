package com.merrg1n.m3fix.mixins.late.manametal.rng;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.fx.LightningHandler;

import java.util.Random;

@Mixin(value = LightningHandler.LightningBolt.class, remap = false)
public class MixinLightningBolt {
    @Redirect(
        method = "<init>(Lnet/minecraft/world/World;Lproject/studio/manametalmod/core/Vector3;Lproject/studio/manametalmod/core/Vector3;FJIIF)V",
        at = @At(
            value = "NEW",
            target = "(J)Ljava/util/Random;",
            remap = false
        )
    )
    private static Random useXSTR(long seed) {
        return new XSTR(seed);
    }
}
