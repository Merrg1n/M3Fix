package com.merrg1n.m3fix.mixins.late.manametal;

import com.merrg1n.m3fix.M3Fix;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.core.Icommodity;
import project.studio.manametalmod.entity.nbt.NbtNpcData;
import project.studio.manametalmod.npc.NpcStoreType;

import java.util.List;
import java.util.Random;

@Mixin(value = NbtNpcData.class, remap = false)
public abstract class MixinNbtNpcData {
    @Shadow
    public List<Icommodity> storesList = null;

    @Shadow
    public boolean custom_store = false;

    @Shadow
    public long store_seed = 0;

    @Shadow
    public NpcStoreType type = NpcStoreType.other1;

    @SideOnly(Side.CLIENT)
    @Inject(method = "setRandomNpc", at = @At(value = "HEAD"), cancellable = true)
    void clientNoUseRandom(CallbackInfo ci){
        if (Thread.currentThread().getName().equals("Client thread")){
            ci.cancel();
        }
    }

    @Inject(method = "setRandomNpc", at = @At(value = "INVOKE_ASSIGN", target = "Ljava/util/Random;nextLong()J", remap = false, shift = At.Shift.AFTER))
    void preCachedNpcStoreList(CallbackInfo ci){
        this.custom_store = true;
        this.storesList = NpcStoreType.getItems(this.type, new Random(this.store_seed));
    }
}
