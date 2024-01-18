package com.merrg1n.m3fix.mixins.late.manametal.model;

import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.furniture.model.LapudaBlocks_C8;

@Mixin(value = LapudaBlocks_C8.class)
public class MixinLapudaBlocks_C8 {
    @Inject(
        method = "render",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/model/ModelRenderer;render(F)V",
            ordinal = 3
        )
    )
    void modifyModel(CallbackInfo ci) {
        GL11.glTranslatef(0, -3f, 0);
    }
}
