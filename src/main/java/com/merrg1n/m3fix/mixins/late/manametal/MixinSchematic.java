package com.merrg1n.m3fix.mixins.late.manametal;

import com.llamalad7.mixinextras.sugar.Local;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import project.studio.manametalmod.MMM;
import project.studio.manametalmod.blueprint.Schematic;

import java.io.InputStream;

@Mixin(value = Schematic.class)
public class MixinSchematic {

    @Inject(
        method = "loadSchematicFromJar",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/nbt/CompressedStreamTools;readCompressed(Ljava/io/InputStream;)Lnet/minecraft/nbt/NBTTagCompound;"
        ),
        cancellable = true
    )
    void readCompressedChecked(String name, CallbackInfoReturnable<Schematic> cir, @Local InputStream is){
        if (is == null) {
            MMM.Logg("cannot load schematic from jar , name:", name);
            cir.setReturnValue(null);
            cir.cancel();
        }
    }

}
