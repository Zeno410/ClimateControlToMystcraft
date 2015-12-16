
package cc2mc;

import climateControl.utils.Accessor;
import com.google.common.collect.HashBiMap;
import com.xcompwiz.mystcraft.symbol.Modifier;
import com.xcompwiz.mystcraft.symbol.ModifierUtils;
import com.xcompwiz.mystcraft.world.AgeController;
import com.xcompwiz.mystcraft.world.IAgeController;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
public class AgeControllerAdapterV10 extends AgeControllerAdapter {
    private HashBiMap<Modifier,ModifierFacade> modifiers= HashBiMap.create();
    private HashBiMap<Modifier,MystcraftSymbolPlugin> modifierPlugins= HashBiMap.create();

    private final AgeController ageController;

    public AgeControllerAdapterV10(Object controller) {
        if (controller instanceof AgeController){
            ageController = (AgeController) controller;
        } else {
            ageController = null;
        }
    }

    public int dimension() {
        if (ageController == null) return 0;
        Accessor<AgeController,World> worldGetter = new Accessor<AgeController,World>("world");
        int dimension = worldGetter.get(ageController).provider.dimensionId;
        return dimension;
    }

    @Override
    public void setModifier(String tag, MystcraftSymbolPlugin plugin) {
        if (ageController == null) return;
            Modifier modifier = modifierPlugins.inverse().get(plugin);
            if (modifier == null) {
                modifier = new Modifier(plugin);
                modifierPlugins.put(modifier, plugin);
            }
            ageController.setModifier(tag, modifier);
    }

    @Override
    public ModifierFacade popModifier(String tag) {
        if (ageController == null) return null;
            Modifier modifier = ageController.popModifier(tag);
            Object object = modifier.asObject();
            if (object == null) return null;
            if (!(object instanceof MystcraftSymbolPlugin)) return null;
            MystcraftSymbolPlugin plugin = (MystcraftSymbolPlugin)object;
            ModifierFacade facade = modifiers.get(plugin);
            if (facade == null) {
                facade= new ModifierFacade() {};
                modifiers.put(modifier, facade);
            }
            return facade;
    }

    @Override
    public BiomeGenBase popBiome() {
        if (ageController == null) return null;
        BiomeGenBase result = ModifierUtils.popBiome(ageController);
        return result;
    }

    @Override
    public boolean profiling() {
        return ageController == null;
    }

}
