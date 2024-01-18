package com.merrg1n.m3fix.mixins.late.manametal.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import project.studio.manametalmod.pagan.TileEntityPaganDoor;

@Mixin(value = TileEntityPaganDoor.class, remap = false)
public abstract class MixinTileEntityPaganDoor extends TileEntity {
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord - 0.6, yCoord, zCoord, xCoord + 1.6, yCoord + 2.7, zCoord + 1);
    }
}
