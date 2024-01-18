package com.merrg1n.m3fix.mixins.late.manametal.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import project.studio.manametalmod.tileentity.TileEntityMagicUpdate;

@Mixin(value = TileEntityMagicUpdate.class, remap = false)
public abstract class MixinTileEntityMagicUpdate extends TileEntity {
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 2, yCoord + 3, zCoord + 2);
    }
}
