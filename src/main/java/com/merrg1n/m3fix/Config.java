package com.merrg1n.m3fix;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean removeUpdateChecks;
    public static boolean fixJourneymapKeybinds;
    public static boolean fixJourneymapJumpyScrolling;
    public static boolean fixJourneymapFilePath;
    public static boolean fixOptifineChunkLoadingCrash;

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
        removeUpdateChecks = config.get(Category.FIXES.toString(), "removeUpdateChecks", true, "Remove old/stale/outdated update checks.").getBoolean();


        fixJourneymapKeybinds = config.get(Config.Category.FIXES.toString(), "fixJourneymapKeybinds", true, "Prevent unbinded keybinds from triggering when pressing certain keys").getBoolean();
        fixJourneymapJumpyScrolling = config.get(Config.Category.FIXES.toString(), "fixJourneymapJumpyScrolling", true, "Fix jumpy scrolling in the waypoint manager screen").getBoolean();
        fixJourneymapFilePath = config.get(Config.Category.FIXES.toString(), "fixJourneymapFilePath", true, "Prevents journeymap from using illegal character in file paths").getBoolean();
        fixOptifineChunkLoadingCrash = config.get(Config.Category.FIXES.toString(), "fixOptifineChunkLoadingCrash", true, "Forces the chunk loading option from optifine to default since other values can crash the game").getBoolean();

        if (config.hasChanged()) {
            config.save();
        }
    }
}
