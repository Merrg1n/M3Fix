package com.merrg1n.m3fix;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean adaptive_vsync;

    public static boolean fix_key_oobe;
    public static boolean disable_m3_fog;

    public static boolean disable_waila_oc_copyitemstack;

    public static boolean optimize_m3_rng;


    enum Category {

        ASM,
        DEBUG,
        FIXES,
        OVERALL,
        SPEEDUPS,
        TWEAKS;

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }

    public static void synchronizeConfiguration(File configFile) {
        Configuration config = new Configuration(configFile);
//        removeUpdateChecks = config.get(Category.FIXES.toString(), "removeUpdateChecks", true, "Remove old/stale/outdated update checks.").getBoolean();
//
//        fixJourneymapFilePath = config.get(Config.Category.FIXES.toString(), "fixJourneymapFilePath", true, "Prevents journeymap from using illegal character in file paths").getBoolean();

        adaptive_vsync = config.get(Category.TWEAKS.toString(), "AdaptiveVsync", true, "Use adaptive vsync instead of normal vsync.").getBoolean();
        fix_key_oobe = config.get(Category.FIXES.toString(), "FixKeyOOBE", true, "Fix keycode out of range when get key name.").getBoolean();


        disable_m3_fog = config.get(Category.TWEAKS.toString(), "DisableM3Fog", true, "Disable Manametal fog event to improve performance.").getBoolean();
        optimize_m3_rng = config.get(Category.TWEAKS.toString(), "OptimizeM3RNG", true, "Replace M3 random number generator to an optimized version.").getBoolean();

        disable_waila_oc_copyitemstack = config.get(Category.TWEAKS.toString(), "DisableWailaOCCopy", false, "Disable Waila getPickBlock cause no use copyItemStack in opencomputer.").getBoolean();

        if (config.hasChanged()) {
            config.save();
        }
    }
}
