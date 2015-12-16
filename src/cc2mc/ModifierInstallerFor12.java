
package cc2mc;
import climateControl.utils.Accessor;
import com.google.common.collect.HashBiMap;
import com.xcompwiz.mystcraft.api.impl.InternalAPI;
import com.xcompwiz.mystcraft.api.symbol.IAgeSymbol;
import com.xcompwiz.mystcraft.api.symbol.ModifierUtils;
import com.xcompwiz.mystcraft.api.world.AgeDirector;
import com.xcompwiz.mystcraft.api.world.logic.Modifier;
import com.xcompwiz.mystcraft.symbol.SymbolBase;
import com.xcompwiz.mystcraft.symbol.SymbolProfiler;
import com.xcompwiz.mystcraft.world.AgeController;
import java.util.WeakHashMap;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

/**
 *
 * @author Zeno410
 */
public class ModifierInstallerFor12 extends ModifierInstaller{

    final String [] blankPoem  = new String [1];

    // weak hash map because controllers get deleted when unloading the server
    private WeakHashMap<AgeDirector,AgeControllerAdapter> ageControllerFacades =
            new WeakHashMap<AgeDirector,AgeControllerAdapter>();
    private HashBiMap<Modifier,ModifierFacade> modifiers= HashBiMap.create();
    private HashBiMap<Modifier,MystcraftSymbolPlugin> modifierPlugins= HashBiMap.create();

    public ModifierInstallerFor12() {
        blankPoem [0] = "";
    }

    @Override
    public void registerSymbol(MystcraftSymbolPlugin symbol, int rank, String[] words) {
        registerSymbol(new SymbolWrapper(symbol),rank,words);
    }

    public void registerSymbol(SymbolBase symbol, int rank, String [] words) {
        IAgeSymbol iAgeSymbol = (IAgeSymbol)symbol;
        InternalAPI.symbol.registerSymbol(iAgeSymbol, "cc2mc");
        symbol.setWords(words);
        InternalAPI.symbol.setSymbolCardRank(iAgeSymbol, rank);
        InternalAPI.symbolValues.setSymbolTradeItem(iAgeSymbol, new ItemStack(Items.emerald, 1));
    }

    public AgeControllerAdapter ageControllerFacade(AgeDirector iAgeController) {
        AgeControllerAdapter result = ageControllerFacades.get(iAgeController);
        if (result == null) {
            result = new AgeControllerAdapterV12(iAgeController);
            ageControllerFacades.put(iAgeController, result);
        }
        return result;

    }

    private class SymbolWrapper extends SymbolBase {
        final MystcraftSymbolPlugin plugin;
        SymbolWrapper( MystcraftSymbolPlugin plugin){
            super(plugin.identifier());
            this.plugin= plugin;
        }

        public void registerLogic(AgeDirector arg0, long arg1) {
            if (arg0 instanceof SymbolProfiler) return;
            AgeControllerAdapter facade = ageControllerFacade(arg0);
            plugin.registerLogic(facade, arg1);
        }

    }

    class AgeControllerAdapterV12 extends AgeControllerAdapter {

        private final AgeDirector ageController;

        public AgeControllerAdapterV12(AgeDirector controller) {
            ageController = (AgeDirector)controller;

        }

        public int dimension() {
            Accessor<AgeController,World> worldGetter = new Accessor<AgeController,World>("world");
            int dimension = worldGetter.get((AgeController)ageController).provider.dimensionId;
            return dimension;
        }

        @Override
        public void setModifier(String tag, MystcraftSymbolPlugin plugin) {
            Modifier modifier = modifierPlugins.inverse().get(plugin);
            if (modifier == null) {
                modifier = new Modifier(plugin);
                modifierPlugins.put(modifier, plugin);
            }
            ageController.setModifier(tag, modifier);
        }

        @Override
        public ModifierFacade popModifier(String tag) {
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

}

