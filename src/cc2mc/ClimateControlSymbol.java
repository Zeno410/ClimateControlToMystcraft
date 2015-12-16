

package cc2mc;

import com.xcompwiz.mystcraft.symbol.SymbolProfiler;

/**
 *
 * @author Zeno410
 */
public class ClimateControlSymbol extends MystcraftSymbolPlugin {

    private final String name;
    private final ClimateControlSettingsManager manager;
    private final ClimateControlModifierParameterizer modifier;

    public ClimateControlSymbol(String name, 
            ClimateControlSettingsManager manager,
            ClimateControlModifierParameterizer modifier) {
        this.name = name;
        this.manager = manager;
        this.modifier = modifier;
    }

    @Override
    public void registerLogic(AgeControllerAdapter controller, long seed) {
        ModifierInstaller.logger.info(name);
        if (controller.profiling()) return;
        manager.add(modifier.parameterized(controller));
        //controller.
        // haven't yet figured a way to instert logic
    }

    @Override
    public String identifier() {
        return name;
    }

}
