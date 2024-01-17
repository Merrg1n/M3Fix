package com.merrg1n.m3fix.mixins.late.manametal.model;

import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.model.ModelTable;

@Mixin(value = ModelTable.class, remap = false)
public class MixinModelTable {
    @Shadow
    private ModelRenderer Shape1;

    @Shadow
    private ModelRenderer Shape2;

    @Shadow
    private ModelRenderer Shape3;

    @Shadow
    private ModelRenderer Shape4;

    @Inject(method = "<init>", at = @At("TAIL"))
    void modifyModel(CallbackInfo ci) {
        Shape1.rotationPointX -= 0.01f;
        Shape1.rotationPointZ -= 0.01f;

        Shape2.rotationPointX += 0.01f;
        Shape2.rotationPointZ += 0.01f;

        Shape3.rotationPointX -= 0.01f;
        Shape3.rotationPointZ += 0.01f;

        Shape4.rotationPointX += 0.01f;
        Shape4.rotationPointZ -= 0.01f;
    }
}
