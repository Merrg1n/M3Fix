package com.merrg1n.m3fix.mixins.late.manametal;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import project.studio.manametalmod.blocks.BlockTreeLeavesSpecial;

@Mixin(value = BlockTreeLeavesSpecial.class, remap = false)
public abstract class MixinBlockTreeLeavesSpecial extends BlockLeaves {
    /**
     * @author Merrg1n
     * @reason fix m3 leaves
     */
    @SideOnly(Side.CLIENT)
    @Overwrite
    public boolean shouldSideBeRendered(IBlockAccess a0, int a1, int a2, int a3, int a4) {
        return super.shouldSideBeRendered(a0, a1, a2, a3, a4);
    }

    /**
     * @author Merrg1n
     * @reason fix m3 leaves
     */
    @Overwrite
    public boolean isOpaqueCube() {
        return super.isOpaqueCube();
    }
}
