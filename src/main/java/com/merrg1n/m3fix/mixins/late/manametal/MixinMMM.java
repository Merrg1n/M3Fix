package com.merrg1n.m3fix.mixins.late.manametal;

import com.mitchej123.hodgepodge.XSTR;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.MMM;

import java.util.Random;

@Mixin(value = MMM.class, remap = false)
public class MixinMMM {
    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "()Ljava/util/Random;", remap = false))
    private static Random useXSTR(){
        return new XSTR();
    }

}
