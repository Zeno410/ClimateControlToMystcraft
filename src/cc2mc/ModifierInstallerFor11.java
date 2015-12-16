
package cc2mc;
//import com.xcompwiz.mystcraft.data.;

import com.xcompwiz.mystcraft.core.InternalAPI;
import com.xcompwiz.mystcraft.symbol.SymbolBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 *
 * @author Zeno410
 */
public class ModifierInstallerFor11 extends ModifierInstallerFor10{

    public void registerSymbol(SymbolBase symbol, int rank, String [] words) {
        InternalAPI.symbol.registerSymbol(symbol);
        symbol.setWords(words);
        symbol.setRarity(0.9F);
        InternalAPI.symbolValues.setSymbolTradeItem(symbol, new ItemStack(Items.emerald, 1));
    }
}
