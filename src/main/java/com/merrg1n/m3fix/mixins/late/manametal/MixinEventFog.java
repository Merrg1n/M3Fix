package com.merrg1n.m3fix.mixins.late.manametal;

import net.minecraftforge.client.event.EntityViewRenderEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import project.studio.manametalmod.event.EventFog;

@Mixin(value = EventFog.class, remap = false)
public class MixinEventFog {
    @Inject(method = "onGetFogColour", at = @At("HEAD"), cancellable = true)
    void onGetFogColour(EntityViewRenderEvent.FogColors event, CallbackInfo ci){
        ci.cancel();
    }

    @Inject(method = "onRenderFog", at=@At("HEAD"), cancellable = true)
    void onRenderFog(EntityViewRenderEvent.RenderFogEvent event, CallbackInfo ci){
        ci.cancel();
    }
}
