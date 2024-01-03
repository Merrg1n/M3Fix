package com.merrg1n.m3fix.mixins.early.manametal;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.produce.cuisine.CuisineCore;

import java.util.HashSet;
import java.util.Set;

@Mixin(value = CuisineCore.class, remap = false)
public class MixinCuisineCore {
    @Unique
    private static Set<String> m3fix$registered = new HashSet<>();

    @Redirect(
        method = "init",
        at = @At(
            value = "INVOKE",
            target = "Lcpw/mods/fml/common/registry/GameRegistry;registerItem(Lnet/minecraft/item/Item;Ljava/lang/String;)V"
        )
    )
    private static void registerDeduped(Item item, String s){
        if (!m3fix$registered.contains(s)){
            GameRegistry.registerItem(item, s);
            m3fix$registered.add(s);
        }
    }
}
