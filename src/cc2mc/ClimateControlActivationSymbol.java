
package cc2mc;

/**
 *
 * @author Zeno410
 */
public class ClimateControlActivationSymbol extends MystcraftSymbolPlugin {
    private final String name;
    private final ClimateControlSettingsManager manager;

    public ClimateControlActivationSymbol(String name,
            ClimateControlSettingsManager manager) {
        this.name = name;
        this.manager = manager;
    }

    @Override
    public void registerLogic(AgeControllerAdapter controller, long seed) {
        manager.set(controller.dimension());
    }

    @Override
    public String identifier() {
        return name;
    }

}
