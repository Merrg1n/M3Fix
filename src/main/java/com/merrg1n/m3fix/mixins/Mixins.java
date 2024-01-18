package com.merrg1n.m3fix.mixins;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;


import com.merrg1n.m3fix.Config;
import com.merrg1n.m3fix.M3Fix;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.FMLLaunchHandler;

public enum Mixins {

    // Vanilla
    ENABLE_ADAPTIVE_VSYNC(new Builder("Enable Adaptive vsync")
        .setPhase(Phase.EARLY).setSide(Side.CLIENT)
        .addMixinClasses("minecraft.MixinMinecraft_AdaptiveSync", "minecraft.MixinGameSettings_AdaptiveSync")
        .setApplyIf(() -> Config.adaptive_vsync).addTargetedMod(TargetedMod.VANILLA)),

    FIX_KEY_OOBE(new Builder("Fix keycode out of range when get key name")
        .setPhase(Phase.EARLY).setSide(Side.CLIENT)
        .addMixinClasses("minecraft.MixinGameSettings_FixKeyOOBE")
        .setApplyIf(() -> Config.fix_key_oobe).addTargetedMod(TargetedMod.VANILLA)),

    // Muyacore

    // Manametal

    REMOVE_M3_LOAD_SCHEMATIC_NPE(new Builder("Remove NullPointerException printStack during m3 loading schematic")
        .setPhase(Phase.LATE).setSide(Side.BOTH).addMixinClasses("manametal.MixinSchematic")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.MANAMETAL)),

    REMOVE_M3_REGISTER_DUPED_ITEM(new Builder("Remove duped item message during m3 init")
        .setPhase(Phase.LATE).setSide(Side.BOTH).addMixinClasses("manametal.MixinCuisineCore")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.MANAMETAL)),
    OPTIMIZE_M3_NPCSTORE(new Builder("Optimize m3 NPC store")
        .setPhase(Phase.LATE).setSide(Side.BOTH).addMixinClasses("manametal.MixinNbtNpcData")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.MANAMETAL)),

    OPTIMIZE_M3_RNG(new Builder("Optimize M3 random number generator")
        .setPhase(Phase.LATE).setSide(Side.BOTH)
        .addMixinClasses(
            "manametal.rng.MixinLapudaBlock",
            "manametal.rng.MixinLightningBolt",
            "manametal.rng.MixinMMM",
            "manametal.rng.MixinModelBookshelf",
            "manametal.rng.MixinRenderDragonM3",
            "manametal.rng.MixinRenderEXPballMana",
            "manametal.rng.MixinRenderItemHolyDevice",
            "manametal.rng.MixinRenderLightningBoltPower",
            "manametal.rng.MixinRenderModelDarkPerfusion",
            "manametal.rng.MixinRenderThunderEffect",
            "manametal.rng.MixinRenderTimemachine"
        )
        .setApplyIf(() -> Config.optimize_m3_rng).addTargetedMod(TargetedMod.MANAMETAL)),

    OPTIMIZE_M3_SPHERE_RENDER(new Builder("Optimize m3 sphere render")
        .setPhase(Phase.LATE).setSide(Side.CLIENT)
        .addMixinClasses("manametal.render.MixinSphereDraw")
        .addTargetedMod(TargetedMod.MANAMETAL)),

    REMOVE_M3_FOG_EVENT(new Builder("Remove m3 fog event to improve performance")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("manametal.MixinEventFog")
        .setApplyIf(() -> Config.disable_m3_fog)
        .addTargetedMod(TargetedMod.MANAMETAL)),

    OPTIMIZE_M3_GETCOLOR(new Builder("Cache color to improve performance")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("manametal.MixinEventGUI")
        .addTargetedMod(TargetedMod.MANAMETAL)),

    FIX_M3_LEAVES(new Builder("Fix m3 leave block doesn't follow the graphic setting")
        .setPhase(Phase.LATE).setSide(Side.CLIENT)
        .addMixinClasses(
            "manametal.render.MixinBlockTreeLeavesFruits", "manametal.render.MixinBlockTreeLeavesSpecial", "manametal.render.MixinBlockTreeLeaves")
        .addTargetedMod(TargetedMod.MANAMETAL)),
    REDUCE_M3_ZFIGHTING(new Builder("Reduce m3 models z-fighting.")
        .setPhase(Phase.LATE).setSide(Side.CLIENT)
        .addMixinClasses(
            "manametal.model.MixinModelRuneStone",
            "manametal.model.MixinLapudaBlocks_C7",
            "manametal.model.MixinLapudaBlocks_C8",
            "manametal.model.MixinModelTable",
            "manametal.model.MixinModelGrindstone",
            "manametal.model.MixinModelTileEntityPot",
            "manametal.model.MixinModelWeaponStar"
        )
        .addTargetedMod(TargetedMod.MANAMETAL)),

    FIX_M3_TE_RENDER_BOX(new Builder("Fix some m3 tileentity render bounding box.")
        .setPhase(Phase.LATE).setSide(Side.CLIENT)
        .addMixinClasses(
            "manametal.model.MixinTileEntityMagicObjectRotate",
            "manametal.model.MixinTileEntityGilded",
            "manametal.model.MixinTileEntityPaganDoor",
            "manametal.model.MixinTileEntityDarkEnchanting",
            "manametal.model.MixinTileEntityMagicUpdate",
            "manametal.model.MixinTileEntityTimemachine",
            "manametal.model.MixinTileEntityTotemSpring",
            "manametal.model.MixinTileEntityWeaponStar"
        )
        .addTargetedMod(TargetedMod.MANAMETAL)),

    ADD_MORE_NEIGTNH_M3(new Builder("Add more nei gtnh version support of manametalmod")
        .setPhase(Phase.LATE).setSide(Side.CLIENT)
        .addMixinClasses("manametal.nei.MixinNEIGTNHCore", "manametal.nei.MixinNEITileEntityTileBase")
        .addTargetedMod(TargetedMod.MANAMETAL)),

    // Waila

    FIX_WAILA_OPENCOMPUTER_COPY_ITEMSTACK(new Builder("Fix waila unused copy on opencomputer's robot and microcontroller")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("waila.MixinRayTracing")
        .addTargetedMod(TargetedMod.WAILA).addTargetedMod(TargetedMod.OPENCOMPUTER)
        .setApplyIf(() -> Config.disable_waila_oc_copyitemstack)
    );

    private final List<String> mixinClasses;
    private final List<TargetedMod> targetedMods;
    private final List<TargetedMod> excludedMods;
    private final Supplier<Boolean> applyIf;
    private final Phase phase;
    private final Side side;

    Mixins(Builder builder) {
        this.mixinClasses = builder.mixinClasses;
        this.targetedMods = builder.targetedMods;
        this.excludedMods = builder.excludedMods;
        this.applyIf = builder.applyIf != null ? builder.applyIf : (() -> true);
        this.phase = builder.phase;
        this.side = builder.side;
        if (this.mixinClasses.isEmpty()) {
            throw new RuntimeException("No mixin class specified for Mixin : " + this.name());
        }
        if (this.targetedMods.isEmpty()) {
            throw new RuntimeException("No targeted mods specified for Mixin : " + this.name());
        }
        if (this.phase == null) {
            throw new RuntimeException("No Phase specified for Mixin : " + this.name());
        }
        if (this.side == null) {
            throw new RuntimeException("No Side function specified for Mixin : " + this.name());
        }
    }

    public static List<String> getEarlyMixins(Set<String> loadedCoreMods) {
        final List<String> mixins = new ArrayList<>();
        final List<String> notLoading = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.phase == Phase.EARLY) {
                if (mixin.shouldLoad(loadedCoreMods, Collections.emptySet())) {
                    mixins.addAll(mixin.mixinClasses);
                } else {
                    notLoading.addAll(mixin.mixinClasses);
                }
            }
        }
        M3Fix.LOG.info("Not loading the following EARLY mixins: {}", notLoading.toString());
        return mixins;
    }

    public static List<String> getLateMixins(Set<String> loadedMods) {
        // NOTE: Any targetmod here needs a modid, not a coremod id
        final List<String> mixins = new ArrayList<>();
        final List<String> notLoading = new ArrayList<>();
        for (Mixins mixin : Mixins.values()) {
            if (mixin.phase == Phase.LATE) {
                if (mixin.shouldLoad(Collections.emptySet(), loadedMods)) {
                    mixins.addAll(mixin.mixinClasses);
                } else {
                    notLoading.addAll(mixin.mixinClasses);
                }
            }
        }
        M3Fix.LOG.info("Not loading the following LATE mixins: {}", notLoading.toString());
        return mixins;
    }

    private boolean shouldLoadSide() {
        return side == Side.BOTH || (side == Side.SERVER && FMLLaunchHandler.side().isServer())
            || (side == Side.CLIENT && FMLLaunchHandler.side().isClient());
    }

    private boolean allModsLoaded(List<TargetedMod> targetedMods, Set<String> loadedCoreMods, Set<String> loadedMods) {
        if (targetedMods.isEmpty()) return false;

        for (TargetedMod target : targetedMods) {
            if (target == TargetedMod.VANILLA) continue;

            // Check coremod first
            if (!loadedCoreMods.isEmpty() && target.coreModClass != null
                && !loadedCoreMods.contains(target.coreModClass))
                return false;
            else if (!loadedMods.isEmpty() && target.modId != null && !loadedMods.contains(target.modId)) return false;
        }

        return true;
    }

    private boolean noModsLoaded(List<TargetedMod> targetedMods, Set<String> loadedCoreMods, Set<String> loadedMods) {
        if (targetedMods.isEmpty()) return true;

        for (TargetedMod target : targetedMods) {
            if (target == TargetedMod.VANILLA) continue;

            // Check coremod first
            if (!loadedCoreMods.isEmpty() && target.coreModClass != null
                && loadedCoreMods.contains(target.coreModClass))
                return false;
            else if (!loadedMods.isEmpty() && target.modId != null && loadedMods.contains(target.modId)) return false;
        }

        return true;
    }

    private boolean shouldLoad(Set<String> loadedCoreMods, Set<String> loadedMods) {
        return (shouldLoadSide() && applyIf.get()
            && allModsLoaded(targetedMods, loadedCoreMods, loadedMods)
            && noModsLoaded(excludedMods, loadedCoreMods, loadedMods));
    }

    private static class Builder {

        private final String name;
        private final List<String> mixinClasses = new ArrayList<>();
        private final List<TargetedMod> targetedMods = new ArrayList<>();
        private final List<TargetedMod> excludedMods = new ArrayList<>();
        private Supplier<Boolean> applyIf = null;
        private Phase phase = null;
        private Side side = null;

        public Builder(String name) {
            this.name = name;
        }

        public Builder addMixinClasses(String... mixinClasses) {
            this.mixinClasses.addAll(Arrays.asList(mixinClasses));
            return this;
        }

        public Builder setPhase(Phase phase) {
            if (this.phase != null) {
                throw new RuntimeException("Trying to define Phase twice for " + this.name);
            }
            this.phase = phase;
            return this;
        }

        public Builder setSide(Side side) {
            if (this.side != null) {
                throw new RuntimeException("Trying to define Side twice for " + this.name);
            }
            this.side = side;
            return this;
        }

        public Builder setApplyIf(Supplier<Boolean> applyIf) {
            this.applyIf = applyIf;
            return this;
        }

        public Builder addTargetedMod(TargetedMod mod) {
            this.targetedMods.add(mod);
            return this;
        }

        public Builder addExcludedMod(TargetedMod mod) {
            this.excludedMods.add(mod);
            return this;
        }
    }

    private enum Side {
        BOTH,
        CLIENT,
        SERVER
    }

    private enum Phase {
        EARLY,
        LATE,
    }
}
