package com.merrg1n.m3fix.mixins.late.manametal.model;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import project.studio.manametalmod.dark_magic.TileEntityDarkEnchanting;

@Mixin(value = TileEntityDarkEnchanting.class, remap = false)
public abstract class MixinTileEntityDarkEnchanting extends TileEntity {
    @Inject(method = "getRenderBoundingBox", at = @At("HEAD"), cancellable = true)
    void getRenderBoundingBox(CallbackInfoReturnable<AxisAlignedBB> cir) {

        cir.setReturnValue(AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord, zCoord - 1, xCoord + 2, yCoord + 1, zCoord + 2));
        cir.cancel();

    }
}
