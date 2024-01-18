package com.merrg1n.m3fix.mixins.late.manametal.model;

import net.minecraft.client.model.ModelRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.model.ModelGrindstone;

@Mixin(value = ModelGrindstone.class, remap = false)
public class MixinModelGrindstone {
    @Shadow
    public ModelRenderer shape1_1;

    @Shadow
    public ModelRenderer rock;

    @Shadow
    public ModelRenderer rock_2;

    @Shadow
    public ModelRenderer rock_3;

    @Shadow
    public ModelRenderer rock_5;

    @Shadow
    public ModelRenderer rock_6;

    @Inject(method = "<init>", at = @At("TAIL"))
    void modifyModel(CallbackInfo ci) {
        shape1_1.rotationPointX += 0.01f;
        rock.rotationPointX += 0.01f;
        rock_2.rotationPointX -= 0.01f;
        rock_3.rotationPointX += 0.02f;
        rock_5.rotationPointX -= 0.01f;
        rock_6.rotationPointX -= 0.02f;
    }
}
