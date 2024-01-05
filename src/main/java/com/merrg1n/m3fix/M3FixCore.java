package com.merrg1n.m3fix;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.merrg1n.m3fix.mixins.Mixins;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.minecraft.launchwrapper.Launch;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({ "optifine" })
public class M3FixCore implements IFMLLoadingPlugin, IEarlyMixinLoader {
    static {
        Config.synchronizeConfiguration(new File(Launch.minecraftHome, "config/m3fix.cfg"));
    }

    private String[] transformerClasses;

    @Override
    public String getMixinConfig() {
        return "mixins.m3fix.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        return Mixins.getEarlyMixins(loadedCoreMods);
    }


    @Override
    public String[] getASMTransformerClass() {
        if (transformerClasses == null) {
            transformerClasses = new String[]{};
        }
        return transformerClasses;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
