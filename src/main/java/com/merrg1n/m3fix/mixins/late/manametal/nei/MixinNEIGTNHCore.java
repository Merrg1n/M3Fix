package com.merrg1n.m3fix.mixins.late.manametal.nei;

import codechicken.nei.api.API;
import codechicken.nei.recipe.GuiRecipeTab;
import codechicken.nei.recipe.HandlerInfo;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.MMM;
import project.studio.manametalmod.ManaMetalMod;
import project.studio.manametalmod.blocks.BlockTileEntityBase;
import project.studio.manametalmod.itemAndBlockCraft.ItemCraft10;
import project.studio.manametalmod.nei.NEIGemData;
import project.studio.manametalmod.nei.NEIManaChest;
import project.studio.manametalmod.nei.NEIManaSpinningWheel;
import project.studio.manametalmod.nei.NEITileEntityTileBase;
import project.studio.manametalmod.nei.gtnh.NEIGTNHCore;
import project.studio.manametalmod.produce.textile.TextileCore;
import project.studio.manametalmod.utils.TileEntityCore;

@Mixin(value = NEIGTNHCore.class, remap = false)
public class MixinNEIGTNHCore {
    @Inject(method = "init", at = @At("HEAD"))
    private static void init(CallbackInfo ci) {
        String nameid = NEIManaChest.class.getName();
        ItemStack baseitem = NEIManaChest.chest[0];
        GuiRecipeTab.handlerMap.put(nameid, (new HandlerInfo.Builder(nameid, "ManaMetalMod", MMM.getMODID())).setDisplayStack(baseitem).build());
        API.addRecipeCatalyst(baseitem, nameid);

        for (int s = 1; s < NEIManaChest.chest.length; ++s) {
            API.addRecipeCatalyst(NEIManaChest.chest[s], nameid, s + 1);
        }
        NEIGTNHCore.setinfo(112, NEIManaSpinningWheel.class, TextileCore.BlockSpinningWheels);
        NEIGTNHCore.setinfo(NEIGemData.class, ItemCraft10.BlockTileEntityGemCrafts);

        for (int s = 0; s < TileEntityCore.tiles.size(); s++) {
            BlockTileEntityBase block = TileEntityCore.tiles.get(s);
            nameid = "M3_" + block.TileName;
            baseitem = MMM.item(block);
            GuiRecipeTab.handlerMap.put(nameid, (new HandlerInfo.Builder(nameid, "ManaMetalMod", MMM.getMODID())).setDisplayStack(baseitem).build());
            API.addRecipeCatalyst(baseitem, nameid);
        }

    }


}
