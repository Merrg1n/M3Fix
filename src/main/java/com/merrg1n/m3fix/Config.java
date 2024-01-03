package com.merrg1n.m3fix;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean removeUpdateChecks;
    public static boolean fixJourneymapFilePath;


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

        fixJourneymapFilePath = config.get(Config.Category.FIXES.toString(), "fixJourneymapFilePath", true, "Prevents journeymap from using illegal character in file paths").getBoolean();

        if (config.hasChanged()) {
            config.save();
        }
    }
}
