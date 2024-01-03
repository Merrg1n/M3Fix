package com.merrg1n.m3fix.mixins;

import cpw.mods.fml.common.Mod;

public enum TargetedMod {

    ARCHAICFIX("ArchaicFix", "org.embeddedt.archaicfix.ArchaicCore", "archaicfix"),
    BAUBLES("Baubles", null, "Baubles"),
    COFH_CORE("CoFHCore", "cofh.asm.LoadingPlugin", "CoFHCore"),
    FASTCRAFT("FastCraft", "fastcraft.Tweaker"),
    HARVESTCRAFT("harvestcraft", null, "harvestcraft"),
    JOURNEYMAP("JourneyMap", null, "journeymap"),
    LWJGL3IFY("lwjgl3ify", "me.eigenraven.lwjgl3ify.core.Lwjgl3ifyCoremod", "lwjgl3ify"),
    NOTENOUGHITEMS("NotEnoughItems", "codechicken.nei.asm.NEICorePlugin", "NotEnoughItems"),
    OPTIFINE("Optifine", "optifine.OptiFineForgeTweaker", "Optifine"),
    WAILA("Waila", null, "waila"),

    OPENCOMPUTER("OpenComputers", "li.cil.oc.common.launch.TransformerLoader", "OpenComputers"),
    MUYACORE("Muya Mod", "tw.pearki.mcmod.muya.asm.MuyaASMCore", "Muya"),
    MANAMETAL("ManaMetalMod",null,"manametalmod"),
    VANILLA("Minecraft", null);
    // NOTE: This doesn't work - late mods need a modid, not a coremod class

    /** The "name" in the {@link Mod @Mod} annotation */
    public final String modName;
    /** Class that implements the IFMLLoadingPlugin interface */
    public final String coreModClass;
    /** The "modid" in the {@link Mod @Mod} annotation */
    public final String modId;

    TargetedMod(String modName, String coreModClass) {
        this(modName, coreModClass, null);
    }

    TargetedMod(String modName, String coreModClass, String modId) {
        this.modName = modName;
        this.coreModClass = coreModClass;
        this.modId = modId;
    }

    @Override
    public String toString() {
        return "TargetedMod{modName='" + modName + "', coreModClass='" + coreModClass + "', modId='" + modId + "'}";
    }
}
