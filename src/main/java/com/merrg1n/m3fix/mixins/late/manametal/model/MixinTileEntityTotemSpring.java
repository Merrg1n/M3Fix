package com.merrg1n.m3fix.mixins.late.manametal.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import project.studio.manametalmod.totem.TileEntityTotemSpring;

@Mixin(value = TileEntityTotemSpring.class, remap = false)
public abstract class MixinTileEntityTotemSpring extends TileEntity {
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 2, zCoord + 1);
    }
}
