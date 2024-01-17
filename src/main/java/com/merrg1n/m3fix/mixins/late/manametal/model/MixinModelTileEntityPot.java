package com.merrg1n.m3fix.mixins.late.manametal.model;

import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.model.ModelTileEntityPot;

@Mixin(value = ModelTileEntityPot.class, remap = false)
public class MixinModelTileEntityPot {
    @Shadow
    public ModelRenderer shape8;

    @Shadow
    public ModelRenderer shape9;

    @Inject(method = "<init>", at = @At("TAIL"))
    void modifyModel(CallbackInfo ci) {
        shape8.rotationPointY += 0.01f;
        shape9.rotationPointY += 0.01f;
    }

}
