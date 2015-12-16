
package cc2mc;

/**
 *
 * @author Zeno410
 */
public class ClimateControlModifier extends MystcraftSymbolPlugin {

    private final String name;
    private final String tag;

    public ClimateControlModifier(String name,String tag) {
        this.name = name;
        this.tag = tag;
    }

    @Override
    public void registerLogic(AgeControllerAdapter controller, long seed) {
        controller.setModifier(tag, this);
    }

    @Override
    public String identifier() {
        return name;
    }

}