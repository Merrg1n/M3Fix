package com.merrg1n.m3fix.mixins.late.manametal.model;

import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.model.ModelRuneStone;

@Mixin(value = ModelRuneStone.class, remap = false)
public class MixinModelRuneStone {

    @Shadow
    public ModelRenderer main_1;

    @Shadow
    public ModelRenderer rock_1;

    @Shadow
    public ModelRenderer rock_3;

    @Shadow
    public ModelRenderer rock_5;

    @Shadow
    public ModelRenderer rock_7;

    @Shadow
    public ModelRenderer rock_10;

    @Inject(method = "<init>", at = @At("TAIL"))
    void modifyModel(CallbackInfo ci) {
        main_1.rotationPointX += 0.01f;
        main_1.rotationPointZ += 0.01f;

        rock_1.rotationPointZ -= 0.01f;
        rock_3.rotationPointZ -= 0.01f;
        rock_5.rotationPointZ -= 0.01f;
        rock_7.rotationPointZ -= 0.01f;
        rock_10.rotationPointZ -= 0.01f;
    }
}
