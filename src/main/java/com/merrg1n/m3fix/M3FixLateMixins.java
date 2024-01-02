package com.merrg1n.m3fix;

import java.util.List;
import java.util.Set;

import com.gtnewhorizon.gtnhmixins.ILateMixinLoader;
import com.gtnewhorizon.gtnhmixins.LateMixin;
import com.merrg1n.m3fix.mixins.Mixins;

@LateMixin
public class M3FixLateMixins implements ILateMixinLoader {

    @Override
    public String getMixinConfig() {
        return "mixins.m3fix.late.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedMods) {
        return Mixins.getLateMixins(loadedMods);
    }
}
