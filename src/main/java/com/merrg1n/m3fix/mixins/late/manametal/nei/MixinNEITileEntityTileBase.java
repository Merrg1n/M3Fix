package com.merrg1n.m3fix.mixins.late.manametal.nei;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.nei.NEITileEntityTileBase;
import codechicken.nei.recipe.TemplateRecipeHandler;

import java.awt.Rectangle;


@Mixin(value = NEITileEntityTileBase.class, remap = false)
public abstract class MixinNEITileEntityTileBase extends TemplateRecipeHandler {
    @Shadow
    public String TileName;

    // trick inject point...
    // loadTransferRects call by TemplateRecipeHandler.<init>, so TileName will be null;
    @Inject(method = "setData", at = @At("TAIL"))
    public void loadTransferRects(CallbackInfo ci) {
        this.transferRects.add(new TemplateRecipeHandler.RecipeTransferRect(new Rectangle(53, 15, 25, 13), TileName + "_Crafting"));
    }


    @Override
    public String getOverlayIdentifier() {
        return "M3_" + TileName;
    }
}
