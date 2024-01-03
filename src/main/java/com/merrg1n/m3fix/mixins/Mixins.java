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
    // Disable update checkers
    COFH_CORE_UPDATE_CHECK(new Builder("Yeet COFH Core Update Check").setPhase(Phase.EARLY).setSide(Side.BOTH)
        .addMixinClasses("cofhcore.MixinCoFHCoreUpdateCheck").setApplyIf(() -> Config.removeUpdateChecks)
        .addTargetedMod(TargetedMod.COFH_CORE)),
    JOURNEYMAP_UPDATE_CHECK(new Builder("Yeet Journeymap Update Check").setPhase(Phase.LATE).setSide(Side.CLIENT)
        .addMixinClasses("journeymap.MixinVersionCheck").setApplyIf(() -> Config.removeUpdateChecks)
        .addTargetedMod(TargetedMod.JOURNEYMAP)),

    // Journeymap
    FIX_JOURNEYMAP_KEYBINDS(new Builder("Fix Journeymap Keybinds").setPhase(Phase.LATE).setSide(Side.CLIENT)
        .addMixinClasses("journeymap.MixinConstants").setApplyIf(() -> true)
        .addTargetedMod(TargetedMod.JOURNEYMAP)),
    FIX_JOURNEYMAP_ILLEGAL_FILE_PATH_CHARACTER(new Builder("Fix Journeymap Illegal File Path Character")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("journeymap.MixinWorldData")
        .setApplyIf(() -> Config.fixJourneymapFilePath).addTargetedMod(TargetedMod.JOURNEYMAP)),

    FIX_JOURNEYMAP_JUMPY_SCROLLING(new Builder("Fix Journeymap jumpy scrolling in the waypoint manager")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("journeymap.MixinWaypointManager")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.JOURNEYMAP)),

    // Vanilla
    ENABLE_ADAPTIVE_VSYNC(new Builder("Enable Adaptive vsync")
        .setPhase(Phase.EARLY).setSide(Side.CLIENT)
        .addMixinClasses("minecraft.MixinMinecraft_AdaptiveSync", "minecraft.MixinGameSettings_AdaptiveSync")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.VANILLA)),

    // CoFH

    COFH_REMOVE_TE_CACHE(
        new Builder("Remove CoFH tile entity cache").addMixinClasses("cofhcore.MixinWorld_CoFH_TE_Cache")
            .setSide(Side.BOTH).setApplyIf(() -> true)
            .addTargetedMod(TargetedMod.COFH_CORE).setPhase(Phase.EARLY)),

    // Muyacore

    // Manametal

    REMOVE_M3_LOAD_SCHEMATIC_NPE(new Builder("remove NullPointerException printStack during m3 loading schematic")
        .setPhase(Phase.EARLY).setSide(Side.BOTH).addMixinClasses("manametal.MixinSchematic")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.MANAMETAL)),

    REMOVE_M3_REGISTER_DUPED_ITEM(new Builder("remove duped item message during m3 init")
        .setPhase(Phase.EARLY).setSide(Side.BOTH).addMixinClasses("manametal.MixinCuisineCore")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.MANAMETAL)),

    REMOVE_M3_FOG_EVENT(new Builder("remove m3 fog event to improve performance")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("manametal.MixinEventFog")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.MANAMETAL)),

    OPTIMIZE_M3_GETCOLOR(new Builder("cache color to improve performance")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("manametal.MixinEventGUI")
        .setApplyIf(() -> true).addTargetedMod(TargetedMod.MANAMETAL)),
    // Waila

    FIX_WAILA_OPENCOMPUTER_COPY_ITEMSTACK(new Builder("Fix Waila cause Opencomputer robot and microcontroller no use copy")
        .setPhase(Phase.LATE).setSide(Side.CLIENT).addMixinClasses("waila.MixinRayTracing")
        .setApplyIf(() -> Loader.isModLoaded(TargetedMod.OPENCOMPUTER.modName)).addTargetedMod(TargetedMod.WAILA)
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
        this.applyIf = builder.applyIf == null ? builder.applyIf :  (() -> true);
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
