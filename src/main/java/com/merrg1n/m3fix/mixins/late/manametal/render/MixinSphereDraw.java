package com.merrg1n.m3fix.mixins.late.manametal.render;

import com.merrg1n.m3fix.utils.RenderSphere;
import org.lwjgl.util.glu.Sphere;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import project.studio.manametalmod.atlantis.RenderOceanEnergy;
import project.studio.manametalmod.renderer.RenderDarkPower;
import project.studio.manametalmod.renderer.RenderMagicBallBlackHole;

@Mixin(value = {RenderDarkPower.class, RenderOceanEnergy.class, RenderMagicBallBlackHole.class}, remap = false)
public class MixinSphereDraw {

    @Redirect(
        method = "renderSphere",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/util/glu/Sphere;draw(FII)V",
            remap = false
        )
    )
    void draw(Sphere instance, float radius, int slices, int stacks) {
        RenderSphere.render(radius);
    }
}
