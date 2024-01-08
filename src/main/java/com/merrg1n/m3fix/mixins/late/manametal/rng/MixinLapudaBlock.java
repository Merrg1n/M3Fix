package com.merrg1n.m3fix.mixins.late.manametal.rng;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.furniture.model.LapudaBlocks_C2;
import project.studio.manametalmod.furniture.model.LapudaBlocks_C3;
import project.studio.manametalmod.furniture.model.LapudaBlocks_C8;

import java.util.Random;

@Mixin(value = {LapudaBlocks_C2.class, LapudaBlocks_C3.class, LapudaBlocks_C8.class}, remap = false)
public class MixinLapudaBlock {
    @Unique
    private static Random m3fix$rng = new XSTR();

    @Redirect(method = "render", at = @At(value = "NEW", target = "()Ljava/util/Random;", remap = false))
    private static Random useXSTR(){
        return m3fix$rng;
    }
}
