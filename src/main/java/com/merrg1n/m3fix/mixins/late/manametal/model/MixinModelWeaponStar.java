package com.merrg1n.m3fix.mixins.late.manametal.model;

import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.Lapuda.ModelWeaponStar;

@Mixin(value = ModelWeaponStar.class, remap = false)
public class MixinModelWeaponStar {
    @Shadow
    public ModelRenderer shape11_core;

    @Inject(method = "<init>", at = @At("TAIL"))
    void modifyModel(CallbackInfo ci) {
        shape11_core.rotationPointY += 0.01f;
    }
}
