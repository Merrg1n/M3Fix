package com.merrg1n.m3fix.mixins.late.waila;

import com.merrg1n.m3fix.M3Fix;
import li.cil.oc.common.block.Microcontroller;
import li.cil.oc.common.block.RobotProxy;
import mcp.mobius.waila.overlay.RayTracing;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = RayTracing.class)
public class MixinRayTracing {

    @Redirect(
        method = "getIdentifierItems",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/Block;getPickBlock(Lnet/minecraft/util/MovingObjectPosition;Lnet/minecraft/world/World;III)Lnet/minecraft/item/ItemStack;"
        ),
        remap = false
    )
    public ItemStack getPickBlock(Block instance, MovingObjectPosition movingObjectPosition, World world, int x, int y, int z) {
        if (instance instanceof RobotProxy || instance instanceof Microcontroller) {
            M3Fix.LOG.debug("waila is trying to get opencomputer block!");
            return null;
        }
        return instance.getPickBlock(movingObjectPosition, world, x, y, z);
    }
}
