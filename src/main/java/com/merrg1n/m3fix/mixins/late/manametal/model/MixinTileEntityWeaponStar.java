package com.merrg1n.m3fix.mixins.late.manametal.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import project.studio.manametalmod.Lapuda.TileEntityWeaponStar;

@Mixin(value = TileEntityWeaponStar.class, remap = false)
public abstract class MixinTileEntityWeaponStar extends TileEntity {
    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.getBoundingBox(xCoord, yCoord, zCoord, xCoord + 1, yCoord + 5.5, zCoord + 1);
    }
}
