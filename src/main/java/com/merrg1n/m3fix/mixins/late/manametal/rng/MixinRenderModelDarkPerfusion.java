package com.merrg1n.m3fix.mixins.late.manametal.rng;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.renderer.RenderModelDarkPerfusion;

import java.util.Random;

@Mixin(value = RenderModelDarkPerfusion.class)
public class MixinRenderModelDarkPerfusion {
    @Unique
    private static Random m3fix$rng = new XSTR();
    @Redirect(method = "renderTileEntityAt", at = @At(value = "NEW", target = "()Ljava/util/Random;", remap = false))
    private static Random useXSTR() {
        return m3fix$rng;
    }
}
