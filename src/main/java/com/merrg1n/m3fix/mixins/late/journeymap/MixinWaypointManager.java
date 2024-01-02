package com.merrg1n.m3fix.mixins.late.journeymap;

import net.minecraft.util.MathHelper;

import org.lwjgl.input.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import journeymap.client.ui.component.JmUI;
import journeymap.client.ui.component.ScrollListPane;
import journeymap.client.ui.waypoint.WaypointManager;

@Mixin(WaypointManager.class)
public abstract class MixinWaypointManager extends JmUI {

    @Shadow(remap = false)
    protected ScrollListPane<?> itemScrollPane;

    @Shadow(remap = false)
    protected int rowHeight;


    /**
     * @author eigenraven
     * @reason Reversed clamping (was: {@code if(delta > 1) delta = -1;} and vice versa)
     */
    @Overwrite(remap = false)
    public void handleMouseInput() {
        super.handleMouseInput();
        int delta = Mouse.getEventDWheel();
        if (delta != 0) {
            this.itemScrollPane.scrollBy(-delta * this.rowHeight);
        }
    }

    /* Forced to have constructor matching super */
    private MixinWaypointManager(String title) {
        super(title);
    }
}
