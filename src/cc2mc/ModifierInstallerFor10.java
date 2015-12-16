
package cc2mc;
import com.xcompwiz.mystcraft.core.InternalAPI;
import com.xcompwiz.mystcraft.symbol.SymbolBase;
import com.xcompwiz.mystcraft.world.IAgeController;
import java.util.WeakHashMap;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Zeno410
 */
public class ModifierInstallerFor10 extends ModifierInstaller{

    final String [] blankPoem  = new String [1];
    
    // weak hash map because controllers get deleted when unloading the server
    WeakHashMap<IAgeController,AgeControllerAdapter> ageControllerFacades =
            new WeakHashMap<IAgeController,AgeControllerAdapter>();

    public ModifierInstallerFor10() {
        blankPoem [0] = "";
    }

    @Override
    public void registerSymbol(MystcraftSymbolPlugin symbol, int rank, String[] words) {
        registerSymbol(new SymbolWrapper(symbol),rank,words);
    }

    public void registerSymbol(SymbolBase symbol, int rank, String [] words) {
        InternalAPI.symbol.registerSymbol(symbol);
        symbol.setWords(words);
        symbol.setRarity(0.9F);
        InternalAPI.symbolValues.setSymbolTradeItem(symbol, new ItemStack(Items.emerald, 1));
    }

    public AgeControllerAdapter ageControllerFacade(IAgeController iAgeController) {
        AgeControllerAdapter result = ageControllerFacades.get(iAgeController);
        if (result == null) {
            result = new AgeControllerAdapterV10(iAgeController);
            ageControllerFacades.put(iAgeController, result);
        }
        return result;

    }

    private class SymbolWrapper extends SymbolBase {
        final MystcraftSymbolPlugin plugin;
        SymbolWrapper( MystcraftSymbolPlugin plugin){
            this.plugin= plugin;
        }

        @Override
        public void registerLogic(IAgeController arg0, long arg1) {
            plugin.registerLogic(ageControllerFacade(arg0), arg1);
        }

        @Override
        public String identifier() {
            return plugin.identifier();
        }
    }

}
