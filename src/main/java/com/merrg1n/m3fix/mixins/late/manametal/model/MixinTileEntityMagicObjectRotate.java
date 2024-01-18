package com.merrg1n.m3fix.mixins.late.manametal.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import project.studio.manametalmod.tileentity.TileEntityMagicObjectRotate;

@Mixin(value = TileEntityMagicObjectRotate.class, remap = false)
public abstract class MixinTileEntityMagicObjectRotate extends TileEntity {
    @Shadow
    public int type;

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        switch (type) {
            case 0:
            case 1:
                return AxisAlignedBB.getBoundingBox(xCoord - 0.5, yCoord, zCoord - 0.5, xCoord + 1.5, yCoord + 3, zCoord + 1.5);
            case 2:
                return AxisAlignedBB.getBoundingBox(xCoord - 0.7, yCoord, zCoord - 0.7, xCoord + 1.7, yCoord + 2, zCoord + 1.7);
            case 3:
                return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 6, zCoord + 1);
            default:
                return super.getRenderBoundingBox();
        }
    }
}
