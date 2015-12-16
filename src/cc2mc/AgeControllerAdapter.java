
package cc2mc;

import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
abstract public class AgeControllerAdapter {

    abstract public int dimension();
    abstract public void setModifier(String tag, MystcraftSymbolPlugin plugin);
    abstract public ModifierFacade popModifier(String tag);
    abstract public BiomeGenBase popBiome();
    abstract public boolean profiling();

}
